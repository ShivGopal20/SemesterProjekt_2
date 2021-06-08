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
import jssc.SerialPort;

import java.lang.*;

public class FrontPageController extends Sensor{
    @FXML
    public LineChart<?, ?> ecgGraph;
    @FXML
    public CategoryAxis x;
    @FXML
    public NumberAxis y;
    @FXML
    public TextArea ecgText;

    int v = 0;
    XYChart.Series ecgValues = new XYChart.Series();

    public void StartMeasurement(){
        v = 1;
        int[] mV = {0, 10, 15, 20, 15, 10, 0, 0, -10, 100, -30, 0, 0, 5, 10, 20, 25, 30, 20, 10, 5, 0};
        int[] ms = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21};
        ecgText.setText("ECG & time \n-----------");

        for (int t = 0; t < mV.length; t++) {
            ecgValues.getData().add(new XYChart.Data(String.valueOf(mV[t]), ms[t]));
            ecgText.appendText("\n"+ms[t]+"ms  ,  "+mV[t]+"mV");
            String NewEcgText = ecgText.getText();
            ecgText.setText(NewEcgText);
        }
    }

    public void test(){
        Sensor test1234 = new Sensor();

       // test1234.maaling();

    }

    public void ShowGraph(){
        if (v==0){
            String AlertTitle = "Error";
            String AlertNote = "There is no ECG data available";
            AlertPopUp(AlertTitle,AlertNote);
        }
        else{
            ecgGraph.getData().add(ecgValues);
        }
    }

    public void StopMeasurement() {
    }

    public void SaveData() {
        if (v==0){
            String AlertTitle = "Error";
            String AlertNote = "There is no ECG data to save";
            AlertPopUp(AlertTitle,AlertNote);
        }
        else {

        }
    }
    public void ShowPulse() {

    }
    public void AlertPopUp(String AlertTitle, String AlertNote){
        Stage AlertBox = new Stage();

        AlertBox.initModality(Modality.APPLICATION_MODAL);
        AlertBox.setTitle(AlertTitle);
        AlertBox.setMinWidth(200);

        Label Note = new Label();
        Note.setText(AlertNote);

        Button Close = new Button("OK");
        Close.setOnAction(e->AlertBox.close());

        VBox AlertBoxLayout = new VBox(10);
        AlertBoxLayout.getChildren().addAll(Note,Close);
        AlertBoxLayout.setAlignment(Pos.CENTER);

        Scene AlertScene = new Scene(AlertBoxLayout);
        AlertBox.setScene(AlertScene);
        AlertBox.show();
    }

    public void Clear(){
        ecgText.clear();
        ecgGraph.getData().clear();
        v = 0;
    }
}
