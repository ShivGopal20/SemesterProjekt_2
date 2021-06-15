package sample;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainMenuController extends Thread {
    @FXML
    public LineChart<Number,Number> ecgGraph;
    @FXML
    public NumberAxis x;
    @FXML
    public NumberAxis y;
    @FXML
    public TextArea ecgText;
    @FXML
    private TextField CPR_Nummer;

    XYChart.Series ecgValues = new XYChart.Series();
    Measurements measurements = new Measurements();
    DB_MySQL database = new DB_MySQL();
    String CprString;
    String CprTilSQL = "0";

    public void ECGstarter() {
        measurements.DataProcessing();
        ShowValues();
        ShowGraph();
        ShowPulse();
        SaveData();
    }

    public void ShowValues() {
        ecgText.setText("ECG & time \n-----------");

        for (int counter = 0; counter < measurements.ArrayData.length; counter++) {
                ecgValues.getData().add(new XYChart.Data(counter,Integer.parseInt(measurements.ArrayData[counter])));
                ecgText.appendText("\n" + counter + "ms  ,  " + measurements.ArrayData[counter] + "mV");
                String NewEcgText = ecgText.getText();
                ecgText.setText(NewEcgText);

        }
    }

    public void ShowGraph() {ecgGraph.getData().add(ecgValues);
    }


    public void SaveData() {
        for (int counter = 0; counter < measurements.ArrayData.length; counter++){
        database.ECG_Inserter(Integer.parseInt(measurements.ArrayData[counter]), CprTilSQL);}
    }

    public void ShowPulse() {
    }

    public void Clear() {
        ecgText.clear();
        ecgGraph.getData().clear();
    }

    public void CPR_Tester() {
        try {
            CprString = String.valueOf(CPR_Nummer.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (CprString.length() == 10) {
            CprTilSQL = CprString;
            AlertPopUp("CPR", "CPR is verified");
        }

        else {
            AlertPopUp("CPR Error", "CPR shall be 10 digits");
        }
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
}
