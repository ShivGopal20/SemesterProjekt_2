package sample;

import jssc.SerialPort;
import jssc.SerialPortException;

public class Arduino_Data extends Thread {
    String input;
    SerialPort sPort = new SerialPort("/dev/cu.usbmodem14101");//Change port path

    public void PortOpener(){
        try {
            //installing Serial
            sPort.openPort();
            sPort.setParams(57600, 8, 1, 0);
            sPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
            sPort.setDTR(true);
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }
     public String ArduinoData() {
         try {
             if (sPort.getInputBufferBytesCount() > 0) {
                 input = sPort.readString();
             } else {
                 Thread.sleep(1000);
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
                 sPort.closePort();

             } catch (SerialPortException e) {
                 e.printStackTrace();
             }
         }

    }
