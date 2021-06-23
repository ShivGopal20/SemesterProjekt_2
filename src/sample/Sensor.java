package sample;

import jssc.SerialPort;
import jssc.SerialPortException;

public class Sensor extends Thread {
    private static Sensor globalSensor = new Sensor();
    String input;
    // SerialPort connection oprettes og portnavn skal tilpasses ift den anvendte port i arduino programmet.
    SerialPort serialPort = new SerialPort("/dev/cu.usbmodem14101");//ToDo: Change port path

    public Sensor() {// Her tilkaldes JSSC SerialPort opsætningen via Port() metoden.
        Port();
    }

    public static Sensor getGlobalSensor() {

        return globalSensor;
    }

    // Metode til at danne forbindelse med Serialporten.
    public void Port() {
        try {
            //Standard SerialPort opsætning med baud rate på 57600.
            serialPort.openPort();  // Porten åbnes.
            //System.out.println("Port opener virker."); // Til Unit Testing
            serialPort.setParams(57600, 8, 1, 0);
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
            serialPort.setDTR(true);
            //System.out.println("Port opstillinger er korrekt."); // Til Unit Testing
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }
        //Metoden tjekker om der kommer data. Desuden læses inputtet som en streng.
    public String sensorData() {
        input = null;
        try {
            if (serialPort.getInputBufferBytesCount() > 0) {
                input = serialPort.readString();
            } else {
                Thread.sleep(5);
            }
        } catch (SerialPortException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return input;

    }
    public void PortCloser() {   //Metoden Lukker porten
        try {
            serialPort.closePort();

        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }
}
