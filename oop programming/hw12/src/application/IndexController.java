package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

public class IndexController implements Initializable {
    @FXML
    private Label resultRate, interestRate;

    @FXML
    private TextField duration, savingAmount;

    @FXML
    private ToggleGroup calcTypeGroup;

    @FXML
    private RadioButton simple, compound;

    @FXML
    private Slider interestRateSlider;

    @FXML
    private GridPane keyPad;

    @FXML
    private Button backSpaceButton;

    private TextField cursorSelected;

    /**
     * 계산 타입(단리, 복리)
     */
    private int calcType = 0;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // 키패드 액션 할당
        ObservableList<Node> children = (ObservableList<Node>) keyPad.getChildren();
        children.forEach(i -> {
            ((Button) i).setOnAction(e -> keyPadAction(e));
        });

        // 슬라이더 액션 할당
        interestRateSlider.valueProperty().addListener((observable, oldVal, newVal) -> {
            interestRate.setText(String.format("%.2f", newVal));
        });

        // 단리, 복리 그룹을 통해 버튼 액션 할당
        calcTypeGroup.selectedToggleProperty().addListener((ovservable, oldVal, newVal) -> {
            if (simple.isSelected()) {
                calcType = 0;
            } else {
                calcType = 1;
            }
        });

        // 기간 TextField에 non-int타입 입력 방지
        duration.textProperty().addListener((observable, oldVal, newVal) -> {
            detectString(duration, oldVal, newVal);
        });

        // 총액 TextField에 non-int타입 입력 방지
        savingAmount.textProperty().addListener((observable, oldVal, newVal) -> {
            detectString(savingAmount, oldVal, newVal);
        });

        cursorSelected = savingAmount;
        savingAmount.setOnMouseClicked(e -> keyPadAreaChange(e));
        duration.setOnMouseClicked(e -> keyPadAreaChange(e));
    }

    /**
     * 선택된 TextField에 따라 cursorSelected를 변경. 키패드가 효과를 미치는 TextField를 선택해주기 위함.
     * @param e
     */
    private void keyPadAreaChange(Event e) {
        cursorSelected = (TextField) e.getSource();
    }

    /**
     * 키패드 클릭시 실행되는 이벤트 리스너
     * @param e
     */
    private void keyPadAction(Event e) {
        String text = ((Button) e.getSource()).getText();   // 이벤트 변수를 통해 클릭 된 버튼의 텍스트값 가져옴

        if ('0' <= text.charAt(0) && text.charAt(0) <= '9') {
            // 0~9 사이의 키패드 값이 클릭되었다면 TextField에 Append
            cursorSelected.appendText(text);
        } else {
            switch (text) {
                case "AC":
                cursorSelected.clear();
                    break;

                case "<-":
                // '<-' 클릭시 백스페이스
                    if(cursorSelected.getLength() == 0) break;
                    cursorSelected.deleteText(cursorSelected.getLength() - 1, cursorSelected.getLength());
            }
        }
    }

    @FXML
    private void calculateResult(Event e) {

        int sa = Integer.parseInt(savingAmount.getText());      // Saving Amount 정수값
        int dr = Integer.parseInt(duration.getText());          // Duration 정수값
        double ir = Double.parseDouble(interestRate.getText()); // Interest Rate 실수값
        switch (calcType) {
            case 0:
                resultRate.setText(String.valueOf(sa * dr * (1 + ir/100)));
                break;

            case 1:
                resultRate.setText(String.valueOf(sa * dr * Math.pow(1 + ir/100, dr / 12.0)));
                break;
        }
    }

    /**
     * 텍스트 필드에 non-int 타입의 값이 입력되는 것을 방지한다.
     * @param textField
     * @param oldVal
     * @param newVal
     */
    private void detectString(TextField textField, String oldVal, String newVal) {

        if(newVal.length() == 0) return;    /* 아무것도 입력된 것이 없을 경우, 메소드 종료 */

        char[] charArray = newVal.toCharArray();
        for (char i : charArray) {
            if (!('0' <= i && i <= '9')) {
                // 정수 아닌 값이 입력되었을 경우, 전의 값으로 되돌아 감.
                textField.setText(oldVal);
            }
        }
    }
}