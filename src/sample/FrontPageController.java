package sample;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FrontPageController extends Thread {
    @FXML
    public LineChart<?, ?> ecgGraph;
    @FXML
    public CategoryAxis x;
    @FXML
    public NumberAxis y;
    @FXML
    public TextArea ecgText;

    int v = 0;
    boolean h = true;
    XYChart.Series ecgValues = new XYChart.Series();
    Arduino_Data b = new Arduino_Data();
    Measurements d = new Measurements();

    public void ShowValues() {
        if (v == 0) {
            String AlertTitle = "Error";
            String AlertNote = "There are no ECG data to show";
            AlertPopUp(AlertTitle, AlertNote);
        } else {
            ecgText.setText("ECG & time \n-----------");

            for (int t = 0; t < d.ArrayLængde; t++) {

                ecgValues.getData().add(new XYChart.Data(String.valueOf(d.o[t]), t));
                ecgText.appendText("\n" + t + "ms  ,  " + d.o[t] + "mV");
                String NewEcgText = ecgText.getText();
                ecgText.setText(NewEcgText);
            }
        }
    }

    public void Start() {
        v = 1;
        d.Printe();
    }


    public void ShowGraph() {
        if (v == 0) {
            String AlertTitle = "Error";
            String AlertNote = "There is no ECG data available";
            AlertPopUp(AlertTitle, AlertNote);
        } else {
            ecgGraph.getData().add(ecgValues);
        }
    }

    public void StopMeasurement() {
        h = false;
        Measurements Tester = new Measurements();
        Tester.Printe();
    }

    public void SaveData() {
        if (v == 0) {
            String AlertTitle = "Error";
            String AlertNote = "There is no ECG data to save";
            AlertPopUp(AlertTitle, AlertNote);
        } else {

        }
    }

    public void ShowPulse() {

    }

    public void AlertPopUp(String AlertTitle, String AlertNote) {
        Stage AlertBox = new Stage();

        AlertBox.initModality(Modality.APPLICATION_MODAL);
        AlertBox.setTitle(AlertTitle);
        AlertBox.setMinWidth(200);

        Label Note = new Label();
        Note.setText(AlertNote);

        Button Close = new Button("OK");
        Close.setOnAction(e -> AlertBox.close());

        VBox AlertBoxLayout = new VBox(10);
        AlertBoxLayout.getChildren().addAll(Note, Close);
        AlertBoxLayout.setAlignment(Pos.CENTER);

        Scene AlertScene = new Scene(AlertBoxLayout);
        AlertBox.setScene(AlertScene);
        AlertBox.show();
    }

    public void Clear() {
        ecgText.clear();
        ecgGraph.getData().clear();
        v = 0;
    }
}
