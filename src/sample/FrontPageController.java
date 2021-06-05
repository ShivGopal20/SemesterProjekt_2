package sample;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextArea;
import java.awt.*;
import java.lang.*;

public class FrontPageController {
    @FXML
    public LineChart<?, ?> ecgGraph;
    @FXML
    public CategoryAxis x;
    @FXML
    public NumberAxis y;
    @FXML
    public TextArea ecgText;

    public void StartMeasurement(){
        XYChart.Series ecgValues = new XYChart.Series();
        int[] mV = {0, 10, 15, 20, 15, 10, 0, 0, -10, 100, -30, 0, 0, 5, 10, 20, 25, 30, 20, 10, 5, 0};
        int[] s = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21};
        ecgText.setText("ECG & time; \n-----------");

        for (int t = 0; t < mV.length; t++) {
            ecgValues.getData().add(new XYChart.Data(String.valueOf(mV[t]), s[t]));
            ecgText.appendText("\n"+s[t]+"s  ,  "+mV[t]+"mV");
        }
        ecgGraph.getData().add(ecgValues);
    }

    public void StopMeasurement() {
    }
    public void SaveData() {
    }
    public void ShowPulse() {
    }

}
