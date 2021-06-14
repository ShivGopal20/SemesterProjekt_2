package sample;

import jssc.SerialPort;
import jssc.SerialPortException;

public class Sensor extends Thread {
    String input;
    SerialPort serialPort = new SerialPort("/dev/cu.usbmodem14101");//ToDo: Change port path

    public void PortOpener() {
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
