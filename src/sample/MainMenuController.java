package sample;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextArea;

public class MainMenuController extends Thread {
    @FXML
    public LineChart<Number,Number> ecgGraph;
    @FXML
    public NumberAxis x;
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

        for (int counter = 0; counter < measurements.ArrayData.length; counter++) {
            System.out.println("her printer vi array " + counter + " " + measurements.ArrayData[counter] );
           // String ECGvaerdi = String.valueOf(measurements.ArrayData[counter]);
                ecgValues.getData().add(new XYChart.Data(counter,Integer.parseInt(measurements.ArrayData[counter])));
                ecgText.appendText("\n" + counter + "ms  ,  " + measurements.ArrayData[counter] + "mV");
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
