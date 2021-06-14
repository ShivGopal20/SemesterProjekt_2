package sample;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextArea;

public class MainMenuController extends Thread {
    @FXML
    public LineChart<?, ?> ecgGraph;
    @FXML
    public CategoryAxis x;
    @FXML
    public NumberAxis y;
    @FXML
    public TextArea ecgText;

    XYChart.Series ecgValues = new XYChart.Series();
    Measurements measurements = new Measurements();

    public void ECGstarter() {
        Start();
        ShowValues();
        ShowGraph();
        ShowPulse();
        SaveData();
    }

    public void ShowValues() {
        ecgText.setText("ECG & time \n-----------");

        for (int t = 0; t < measurements.ArrayLemgth; t++) {

            ecgValues.getData().add(new XYChart.Data(String.valueOf(measurements.stringArray[t]), t));
            ecgText.appendText("\n" + t + "ms  ,  " + measurements.stringArray[t] + "mV");
            String NewEcgText = ecgText.getText();
            ecgText.setText(NewEcgText);
        }
    }

    public void Start() {
        measurements.Print();
    }


    public void ShowGraph() {
        ecgGraph.getData().add(ecgValues);
    }


    public void SaveData() {
    }

    public void ShowPulse() {
    }


    public void Clear() {
        ecgText.clear();
        ecgGraph.getData().clear();
    }
}
