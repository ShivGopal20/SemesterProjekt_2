package sample;

import jssc.SerialPort;
import jssc.SerialPortException;

public class Arduino_Data {
    public static void main(String[] args) {
        Sensor sensor= new Sensor();
        sensor.ArduinoData();

    }

    public static class Sensor {
        double vaerdi;

        public double ArduinoData() {
            SerialPort port = new SerialPort("/dev/cu.usbmodem14201");

            //Opsætning af serialport
            try {
                port.openPort();
                port.setParams(9600, 8, 1, 0);
                port.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
                port.setDTR(true);
            } catch (SerialPortException e) {
                e.printStackTrace();
            }

            //Tjekker byte antal, hvis det ikke er et positivt tal vent
            while (true) {
                try {
                    if (port.getInputBufferBytesCount() > 0) {
                        String input = port.readString();
                        double input2 = Double.parseDouble(input.substring(2,6));
                        System.out.println(input);
                        vaerdi = input2;
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
            // lukning af port

            try {
                port.closePort();
            } catch (SerialPortException e) {
                e.printStackTrace();
            }
            return vaerdi;
        }

    }



}
