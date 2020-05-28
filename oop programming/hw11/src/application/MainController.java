package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class MainController implements Initializable {

	private DataBase databaseController;
	
	Parent loginSuccessWindow;
	
	Parent loginFailedWindow;
	
	@FXML
	private TextArea idInput;
	
	@FXML
	private PasswordField pwInput;
	
	@FXML
	private Button loginButton;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		databaseController = new DataBase();
	    try {
	    	//initialize내에서 예외 처리
			loginSuccessWindow = FXMLLoader.load(getClass().getResource("LoginSuccess.fxml"));
			loginFailedWindow = FXMLLoader.load(getClass().getResource("LoginFail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	    loginButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				doLogin(event);
			}
	    });
	}
	
	/**
	 * DataBase와 통신하여 로그인한다.
	 * @param id
	 * @param pw
	 * @return 로그인 성공, 실패
	 */
	private boolean login(String id, String pw) {
		return databaseController.isUser(id, pw);
	}
	
	/**
	 * 로그인 성공했을 때 보여주는 화면
	 */
	private void setLoginSuccessWindow() {
	    Scene scene = new Scene(loginSuccessWindow);
	    Stage primaryStage = (Stage)loginButton.getScene().getWindow();
	    primaryStage.setScene(scene);
	    primaryStage.setTitle("success");
	}
	
	/**
	 * 로그인 실패했을 때 보여주는 화면
	 */
	private void setLoginFailedWindow() {
	    Scene scene = new Scene(loginFailedWindow);
	    Stage primaryStage = (Stage)loginButton.getScene().getWindow();
	    primaryStage.setScene(scene);
	    primaryStage.setTitle("failed");
	}
	
	/**
	 * 로그인 버튼 액션
	 * @param event
	 */
	public void doLogin(ActionEvent event) {
		if(login(idInput.getText(), pwInput.getText())) {
			setLoginSuccessWindow();
		} else {
			setLoginFailedWindow();
		}
	}
	
}
