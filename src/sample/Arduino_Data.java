package sample;

import jssc.SerialPort;
import jssc.SerialPortException;

public class Arduino_Data {

    public static void main(String[] args) {
        Sensor sensor = new Sensor();
        while (true) {
            sensor.ArduinoData();
        }

    }



    public static class Sensor {
        double vaerdi;

        public double ArduinoData() {
            SerialPort sPort = new SerialPort("/dev/cu.usbmodem14201");//Change port path

            try {
                //installing Serial
                sPort.openPort();
                sPort.setParams(57600, 8, 1, 0);
                sPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
                sPort.setDTR(true);
            } catch (SerialPortException e) {
                e.printStackTrace();
            }

            //
            while (true) {
                try {
                    if (sPort.getInputBufferBytesCount() > 0) {
                        String input = sPort.readString();
                        System.out.println(input);
                        break;
                    } else {
                        Thread.sleep(1000);
                    }
                } catch (SerialPortException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }

            try {
                sPort.closePort();
            } catch (SerialPortException e) {
                e.printStackTrace();
            }
            return vaerdi;
        }

    }


}
