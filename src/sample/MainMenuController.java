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

    //Dette er Start knappens onAction metode, hvis opgave er at starte andre metoder.
    public void ECGstarter() {
        measurements.DataProcessing();
        ShowValues();
        ShowGraph();
        SaveData();
        Sensor.getGlobalSensor().PortCloser();
    }
    //Metoden sørger for både at indsætte ECG værdier i TextArea, og i XY.Chart serien.
    public void ShowValues() {
        ecgText.setText("ECG & time \n-----------");

        for (int counter = 0; counter < measurements.ArrayData.length; counter++) {
                if (NumberChecker((measurements.ArrayData[counter]))){
                ecgValues.getData().add(new XYChart.Data(counter,Integer.parseInt(measurements.ArrayData[counter])));
                ecgText.appendText("\n" + counter + "ms  ,  " + measurements.ArrayData[counter] + "mV");
                String NewEcgText = ecgText.getText();
                ecgText.setText(NewEcgText);
                }
        }
    }
    //NumberChecker metoden sørger for at frasortere ugyldige CPR-værdier, som indeholder andet end tal.
    public boolean NumberChecker(String value) {
        String maaling = value;
        for (int i = 0; i < maaling.length(); i++) {
            if(maaling.matches("^[0-9]*$")) {
                return true;}
            else {
                return false;
            }
        }
        return false;
    }
    //Metoden sørger for at plotte XY.Chart seriens værdier i grafen.
    public void ShowGraph() {ecgGraph.getData().add(ecgValues); }

    //Denne metoder indsætter de målte værdier samt evt et CPR-nummer i SQL-databasen.
    //Her bliver der kaldt på metoder fra Database-klassen, hvor de forklares yderligere.
    public void SaveData() {
        for (int counter = 0; counter < measurements.ArrayData.length; counter++){
            if (NumberChecker(measurements.ArrayData[counter])){
        database.ECG_Inserter(Integer.parseInt(measurements.ArrayData[counter]), CprTilSQL);} }
       // System.out.println("metoden SaveData til database virker  ");//Til Unit testing
    }
    //Metoden nulstiller både Linechartens indhold og TextAreas inhold, så GUI er nulstillet.
    public void Clear() {
        ecgText.clear();
        ecgGraph.getData().clear();
    }

    //Denne metode be- eller afkræfter det indtastede CPR-nummer ud fra passende kriterier såsom:
    //længde og at den indtastede tekst udelukkende indeholder tal, hvilket gøres vha. Numberchecker().
    public void CPR_Check() {
        try {
            CprString = String.valueOf(CPR_Nummer.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (CprString.length() == 10 && NumberChecker(CprString)) {
            CprTilSQL = CprString;
            AlertPopUp("CPR", "CPR is verified");}
        else {
            AlertPopUp("CPR Error", "CPR shall be 10 digits");
        }
    }

    //Denne metoder bruges til at lave AlertPopUps, som bruges hvis man indtaster et ugyldigt CPR-nummer.
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
