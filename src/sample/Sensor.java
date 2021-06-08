package sample;

import jssc.SerialPort;
import jssc.SerialPortException;

public class Sensor{
    private SerialPort sensor = new SerialPort("/dev/cu.usbmodem14101");

    public Sensor() {
        //JSSC biblotek kommer fra  https://github.com/java-native/jssc/releases
        try {
            sensor.openPort();
            sensor.setParams(115200, 8, 1, 0);
            sensor.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
            //  sensor.purgePort(SerialPort.PURGE_TXCLEAR | SerialPort.PURGE_RXCLEAR);//prøver at slette data, hvis der er data fra forrige læsning
            sensor.setDTR(true);
        } catch (SerialPortException ex) {
            System.out.println("FEJL SERIALPORTEXCEPTION");

        }
    }

    public String maaling() { //setters her
        try {
            System.out.println(System.currentTimeMillis());
            if (sensor.getInputBufferBytesCount() > 0) {
                return sensor.readString();
                System.out.println(sensor.readString());
            } else {
                return null;
            }
        } catch (SerialPortException ex) {
            System.out.println("fejl: " + ex);
        }
        return null;
    }

}
