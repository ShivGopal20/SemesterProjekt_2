package sample;

import jssc.SerialPort;
import jssc.SerialPortException;

public class Sensor extends Thread {
    String input;
    SerialPort serialPort = new SerialPort("/dev/cu.usbmodem14101");//ToDo: Change port path

    private Sensor(){
        //PortOpener();
        System.out.println("Signals fra porten");
        Port();
    }

    public static Sensor getGlobalSensor() {

        return globalSensor;
    }

    private static final Sensor globalSensor = new Sensor();


    
    //https://www.journaldev.com/1377/java-singleton-design-pattern-best-practices-examples#:~:text=Singleton%20pattern%20restricts%20the%20instantiation,objects%2C%20caching%20and%20thread%20pool.

    public void Port() {
        try {
            //Serial Port set up
            serialPort.openPort();
            serialPort.setParams(57600, 8, 1, 0);
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
            serialPort.setDTR(true);
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }


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

    public void PortCloser() {
        try {
            serialPort.closePort();

        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

}
