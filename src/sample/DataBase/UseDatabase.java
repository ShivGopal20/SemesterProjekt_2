package sample.DataBase;

import java.sql.*;
import java.util.ArrayList;
public class UseDatabase {

    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection connection;

    public UseDatabase(Connection connection){
        this.connection=connection;
        //  System.out.println("I got the connection named..."+connection.toString());
    }




}
