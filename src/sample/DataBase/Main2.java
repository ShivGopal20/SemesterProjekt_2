package sample.DataBase;

import java.sql.Connection;

public class Main2 {
    public static void main(String[] args) {

        Database database = new Database();
        Connection mysql = database.ConnectionToMySQL("SemesterProject2","2021","ecgDatabse");
    }
}
