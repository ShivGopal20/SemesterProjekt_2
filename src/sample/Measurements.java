package sample;

public class Measurements extends Arduino_Data{

    String[] b = input.split(",");
    public void Printe(){
        System.out.println("--------------");
        for (int x = 0; x<b.length; x++){
            System.out.println("Værdi: "+b[x]);
        }
    }
}