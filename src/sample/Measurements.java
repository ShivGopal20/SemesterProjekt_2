package sample;

public class Measurements {
    Arduino_Data b = new Arduino_Data();
    int ArrayLængde;
    String[] o;

    public void Printe() {
        b.PortOpener();
        for (int x = 0; x < 10; x++) {
            String data = b.ArduinoData();
            if (data != null) {
                o = data.split(",");
                ArrayLængde = o.length;
                for (int a = 0; a < o.length; a++) {
                    o[a].replaceAll("[0-9]", "");
                    System.out.println(o[a] + " ");
                }
            }
        }
        b.PortCloser();
    }
}