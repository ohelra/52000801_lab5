package com.example.lab5.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection {


    public static Connection getConnection()
    {
        String url = "jdbc:mysql://localhost:3306/lab5-java";
        String user = "root";
        String password = "";
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, user, password);
            return conn;
        }catch (ClassNotFoundException e)
        {

        }catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        finally {
            return conn;
        }
    }

}
