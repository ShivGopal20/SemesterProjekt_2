package sample;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class FrontPageController {
    @FXML
    public LineChart<?, ?> ecgGraph;
    @FXML
    public CategoryAxis x;
    @FXML
    public NumberAxis y;

    public void StartMeasurement() {
        XYChart.Series ecgValues = new XYChart.Series();
        ecgValues.getData().add(new XYChart.Data("1", 1));
        ecgValues.getData().add(new XYChart.Data("2", 2));
        ecgValues.getData().add(new XYChart.Data("3", 3));
        ecgValues.getData().add(new XYChart.Data("4", 4));
        ecgGraph.getData().addAll(ecgValues);
        }
    public void StopMeasurement() {
    }
    public void SaveData() {
    }
    public void ShowPulse() {
    }

}
