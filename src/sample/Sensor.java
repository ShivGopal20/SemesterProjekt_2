package sample;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

import java.sql.SQLOutput;
import java.util.Arrays;

public class Sensor{
    private SerialPort sensor = new SerialPort("/dev/tty.usbmodem14201");

    public Sensor() {
        //JSSC biblotek kommer fra  https://github.com/java-native/jssc/releases
        String[] portnavne = SerialPortList.getPortNames();
        System.out.println("Serialport 1: "+ Arrays.toString(portnavne));

        try {
            sensor.setParams(9600, 8, 1, 0);

            sensor.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
            sensor.purgePort(SerialPort.PURGE_TXCLEAR | SerialPort.PURGE_RXCLEAR);//prøver at slette data, hvis der er data fra forrige læsning
            sensor.setDTR(true);
            sensor.openPort();
         //  sensor.closePort();
        } catch (SerialPortException ex) {
           ex.printStackTrace();
        }
    }

    public String maaling() {
        try {
            if (sensor.getInputBufferBytesCount() > 0) {
                return sensor.readString();
            } else {
                return null;
            }
        } catch (SerialPortException ex) {
            System.out.println("fejl: " + ex);
        }
        return null;
    }
    static SerialPort chosenPort;
    public void metode() {
       // SerialPort[] Portnames = SerialPort.
    }


}
