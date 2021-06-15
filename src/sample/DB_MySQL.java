package sample;

import java.sql.*;

public class DB_MySQL {

    static Connection MySQLConnection;
    static Statement statement;
    static String URL = "jdbc:mysql://localhost:3306/ECG_Gruppe3";
    static String User = "Group3";
    static String Password = "12345";

    public void SQLConnection(){
        try {
            MySQLConnection = DriverManager.getConnection(URL, User, Password);
            statement = MySQLConnection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void SQLConnectionRemover(){
        try {
            if (!MySQLConnection.isClosed()){
                MySQLConnection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void ECG_Inserter(int ECG) {
        try {
            SQLConnection();
            String DB_Writer = "insert into ECG_Values" + "(ECG) values(?)";
            PreparedStatement PS = MySQLConnection.prepareStatement(DB_Writer);

            PS.setDouble(1, ECG);

            PS.execute();
            SQLConnectionRemover();
        } catch (SQLException e) {
            e.printStackTrace();
            SQLConnectionRemover();
        }
    }

}
