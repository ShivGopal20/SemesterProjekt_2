package sample;

public class UniTesting extends Sensor {

    public static void main (String[] args) {


        System.out.println(" SerialPort Metoden:    "+Sensor.getGlobalSensor().serialPort);
        System.out.println(" ");
        System.out.println("Uni testSensorData :  "+ Sensor.getGlobalSensor().sensorData());
        System.out.println(" ");
    }
}
