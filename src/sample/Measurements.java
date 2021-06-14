package sample;

public class Measurements {
    Sensor sensorObject = new Sensor();
    int ArrayLength;
    String[] stringArray;
    String buffer = "";

    public void Print() {

        sensorObject.PortOpener();

        for (int x = 0; x < 10; x++) {
            buffer = buffer + sensorObject.sensorData();
            if (buffer != null && buffer.length() > 0) {
                stringArray = buffer.split(",");
                ArrayLength = stringArray.length;
                for (int a = 0; a < ArrayLength; a++) {
                    stringArray[a].replaceAll("[0-9]", "");
                }
                buffer = stringArray[ArrayLength - 1];
                stringArray[ArrayLength -1] = null;

            }
        }
        for (int x = 0; x< stringArray.length; x++) {
            System.out.println(stringArray[x]);
        }
        sensorObject.PortCloser();
    }
}