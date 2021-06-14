package sample;

public class Measurements {
    Sensor sensorObject = Sensor.getGlobalSensor();
    //singleton pattern for at have global sensor, andre ikke kan overskrive, men tilgå.
    //hensigtmæssigt i projekt?
    int ArrayLength;
    public String[] ArrayData;
    String buffer = "";
  //  Sensor sensorObject = new Sensor();
    int h = 0;
    int[] intDataArray;

    public void Print() {

        for (int x = 0; x < 10; x++) {
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

                        //for (int g = 0; g<ArrayData.length; g++)
                        //{System.out.println(ArrayData[g]);}

                        /*
                        while (h < ArrayData.length - 1 && ArrayData.length > 1) {
                            if (ArrayData.length > 1) {
                                if (h >= 500) {
                                    break;
                                }
                            }
                            h++;
                        }

                         */

                    }
                }
            }


        }

       // for (int x = 0; x < ArrayData.length; x++) {
       //     System.out.println(ArrayData[x]); }
        sensorObject.PortCloser();
    }
}

 /*
            buffer = buffer + sensorObject.sensorData();
            if (buffer != null && buffer.length() > 0) {
                ArrayData = buffer.split(",");
                ArrayLength = ArrayData.length;
                for (int a = 0; a < ArrayLength; a++) {
                    ArrayData[a].replaceAll("[0-9]", "");
                }
                buffer = ArrayData[ArrayLength - 1];
                ArrayData[ArrayLength -1] = null;
                }
             */