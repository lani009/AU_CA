package application;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TimerController implements Initializable {
    // FXML 변수 선언
    @FXML
    private Label output;

    @FXML
    private Button btnStart;

    @FXML
    private Button btnStop;
    
    @FXML
    private Button btnClear;

    // timer 작동을 지시하는 변수
    private boolean stop = false;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // btnStart, btnStop, btnClear Action을 람다식 형태로 등록

        btnStart.setOnAction((e) -> btnStartAction(e));
        btnStop.setOnAction((e) -> btnStopAction(e));
        btnClear.setOnAction((e) -> btnClearAction(e));
    }

    public void btnStartAction(ActionEvent e) {
        // 시작 버튼 클릭 시
        stop = false;

        // 작업 스레드 생성 -> 익명 객체로 생성
        Thread thread = new Thread() {

            // 스레드에서 실행할 내용 작성
            @Override
            public void run() {
                // String format 생성
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                while (!stop) {
                    // strTime변수에 현재 시각을 "HH:MM:SS"형태로 저장
                    String strTime = sdf.format(new Date());

                    // runLater 메소드를 통해 UI 변경 코드 작성
                    Platform.runLater(() -> {
                        output.setText(strTime);
                    });

                    
                    try {
                        // 0.1초 주기로 실행되기 위해 sleep
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        // 스레드 실행
        thread.start();
    }

    public void btnStopAction(ActionEvent e) {
        // 멈춤버튼 클릭시

        stop = true;
    }

    public void btnClearAction(ActionEvent e) {
        // 초기화 버튼 클릭시

        stop = true;
        output.setText("00:00:00");
    }
}