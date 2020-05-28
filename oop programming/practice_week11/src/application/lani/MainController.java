package application.lani;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class MainController implements Initializable {
	
	@FXML
	private Slider num1;
	
	@FXML
	private Slider num2;
	
	@FXML
	private Label num1Text;
	
	@FXML
	private Label num2Text;
	
	@FXML
	private ToggleGroup group;
	
	@FXML
	private RadioButton add;
	
	@FXML
	private RadioButton multi;
	
	@FXML
	private Button calc;
	
	@FXML
	private TextField result;
	
	@FXML
	private Button exit;
	
	private int calcType = 1;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		num1.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				num1Text.setText(String.format("%.2f", newValue));
				
			}
		});
		
		num2.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				num2Text.setText(String.format("%.2f", newValue));
			}
		});
		
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				if(add.isSelected()) {
					calcType = 1;
				} else {
					calcType = 2;
				}
				
			}
		});
		
	}
	
	
	public void calcAction(ActionEvent event) {
		double num1 = Double.parseDouble(num1Text.getText());
		double num2 = Double.parseDouble(num2Text.getText());
		
		if(calcType == 1) {
			result.setText(String.format("%.2f", num1 + num2));
		} else {
			result.setText(String.format("%.2f", num1 * num2));
		}
	}
	
	public void exitAction(ActionEvent event) {
		Stage root = (Stage) exit.getScene().getWindow();
		root.close();
	}
}
