package sample.DataBase;

import sample.Measurements;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Database extends Measurements {

    private static Connection connection = null;
    private static String MYSQLDriver = "jdbc:mysql://" + "localhost:3306/";
    private static String url;

    public Database() {
    }


    Connection ConnectionToMySQL(String username, String password, String Schema) {
        url = MYSQLDriver + Schema + "?serverTimezone=Europe/Amsterdam&amp";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);

            if (connection != null) {
                System.out.println("Connected to MYSQL Schema:" + Schema);
            }

        } catch (SQLException throwables) {
            System.out.println("connection problem");
        } catch (ClassNotFoundException e) {
            System.out.println(" ");
        }
        return connection;

    }


}




