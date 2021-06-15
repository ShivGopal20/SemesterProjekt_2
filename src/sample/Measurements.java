package sample;

public class Measurements {
    Sensor sensorObject = Sensor.getGlobalSensor();
    //singleton pattern for at have global sensor, andre ikke kan overskrive, men tilgå.
    //hensigtmæssigt i projekt?
    public String[] ArrayData;
    String buffer = "";

    //Nedenstående metoden bruges til at behandle dataen.
    //Her splittes inputtet op i en array, og der bruges også en buffer, så der ikke mistes data.
    public void DataProcessing() {
            String data = sensorObject.sensorData();
            if (data != null) {
                buffer = buffer + data;
                int SkilletegnPlacering = buffer.indexOf(",");
                if (SkilletegnPlacering > -1) {
                    ArrayData = buffer.split(",");
                    if (ArrayData != null && ArrayData.length > 0) {
                        if (buffer.charAt(buffer.length() - 1) != 44) {
                            buffer = ArrayData[ArrayData.length - 1];
                        } else {
                            buffer = ""; }
                    }
                }
            }
        sensorObject.PortCloser();
    }
}