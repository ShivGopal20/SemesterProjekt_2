package sample;



import com.fazecast.jSerialComm.SerialPort;

import java.io.IOException;

public class Sensor2 {


    public static void sensor2 (){
        SerialPort serialPort = SerialPort.getCommPort("/dev/tty.usbmodem14201"); // Port Name
        serialPort.setComPortParameters(9600, 8, 1, 0); // default connection settings for Arduino
       serialPort.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0); // block until bytes can be written

        if (serialPort.openPort()) {
            System.out.println("Port is open :)");
        } else {
            System.out.println("Failed to open port :(");
            return;
        }

        for (Integer i = 0; i < 5; ++i) {
            try {
                serialPort.getOutputStream().write(i.byteValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                serialPort.getOutputStream().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Sent number: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (serialPort.closePort()) {
            System.out.println("Port is closed :)");
        } else {
            System.out.println("Failed to close port :(");
            return;
        }


    }

}