package sample;

public class Measurements {
    Sensor sensorObject = Sensor.getGlobalSensor();
    public String[] ArrayData;
    String buffer = "";

    //I denne metode splittes inputtet op i en array, og der bruges en buffer, så der ikke mistes data.
    //Denne metode er adskilt fra sensorData() metoden, for at bevare SOC, Seperation og concerns.
    public void DataProcessing() {
        String data = sensorObject.sensorData();
        if (data != null) {
            buffer = buffer + data;
            int SkilletegnPlacering = buffer.indexOf(",");
            if (SkilletegnPlacering > -1) {
                ArrayData = buffer.split(",");
                if (ArrayData != null && ArrayData.length > 0) {
                    if (buffer.charAt(buffer.length() - 1) != 44) { //44 er kommas charat nummer. TODO: Det skal rettes på din PC!
                        buffer = ArrayData[ArrayData.length - 1];
                    } else {
                        buffer = "";
                    }
                }
            }
        }
        /*for(int tæller = 0; tæller < ArrayData.length; tæller++) {
            System.out.println("Værdi nr: "+tæller+" i arrayen: "+ArrayData[tæller]);
        }*/
    }
}