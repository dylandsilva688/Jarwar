package com.docubyte.test.app01.factory;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionFactory {
    private static Connection connection = null;
    static{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/doddb", "root", "Dsilva123");
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }


    public static Connection getConnection() {
        return connection;
    }


    public static void close(){
        try{
            connection.close();
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
