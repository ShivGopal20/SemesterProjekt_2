package sample;

import java.sql.*;

public class DB_MySQL {

    //Nedenfor defineres de static variabler der skal bruges i forbindelse med databasen.
    //Connection bruges til både, at danne og slukke forbindelse med databasen.
    //De 3 String værdier bruges, som argumenter til at connection kan dannes.
    static Connection MySQLConnection;
    static Statement statement;
    static String URL = "jdbc:mysql://localhost:3306/ECG_Gruppe3";
    static String User = "Group3";
    static String Password = "12345";

    //Denne metode forsøger at danne forbindelsen til databasen vha. de angivne argumenter.
    public void SQLConnection(){
        try {
            MySQLConnection = DriverManager.getConnection(URL, User, Password);
            statement = MySQLConnection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        //System.out.println("Konnektion til MySQL er verified "); //Til Unit testing
    }

    //Denne metode forsøger at slukke forbindelsen.
    public void SQLConnectionRemover(){
        try {
            if (!MySQLConnection.isClosed()){
                MySQLConnection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //System.out.println("Connection remover verified ");//Til Unit testing
    }

    //Her forsøges der at indsætte de målte ECG-værdier ind i tabellen.
    public void ECG_Inserter(int ECG,String CPR) {
        try {
            SQLConnection();
            String DB_Writer = "insert into ECG_Values" + "(ECG, CPR) values(?,?)";
            PreparedStatement PS = MySQLConnection.prepareStatement(DB_Writer);

            PS.setDouble(1, ECG);
            PS.setString(2, CPR);

            PS.execute();
            SQLConnectionRemover();
        } catch (SQLException e) {
            e.printStackTrace();
            SQLConnectionRemover();
        }
        //System.out.println("EKG inserter verified ");//Til Unit testing
    }

}
