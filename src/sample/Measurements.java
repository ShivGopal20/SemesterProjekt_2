package sample;

public class Measurements extends Sensor{

    String[] b = Nyvaerdi.split(",");
    public void Printe(){
        for (int x = 0; x<b.length; x++){
            System.out.println("Værdi: "+b[x]);
        }
    }
}
