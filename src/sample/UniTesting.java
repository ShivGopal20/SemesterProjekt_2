package sample;

public class UniTesting extends Sensor {


    public static void main (String[] args) {
        MainMenuController controller = new MainMenuController();

        System.out.println("SerialPort Navn: "+Sensor.getGlobalSensor().serialPort.getPortName());
        System.out.println("Rådata afhentes via. sensorData metode; "+ Sensor.getGlobalSensor().sensorData());

        System.out.println("");
        System.out.println("NumberChecker metoden afprøves:");
        System.out.println("Number Checker, 33: "+controller.NumberChecker("33"));
        System.out.println("Number Checker, 3?: "+controller.NumberChecker("3?"));
        System.out.println("Number Checker, aa: "+controller.NumberChecker("aa"));
        System.out.println("Number Checker, ??: "+controller.NumberChecker("??"));



    }
}
