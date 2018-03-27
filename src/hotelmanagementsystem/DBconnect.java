/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class DBconnect {

    private Connection DBConnection;
    String url = "jdbc:mysql://localhost:3306/javaapp";
     String dbName = "javaapp";
     String driver = "com.mysql.jdbc.Driver";
     String userName = "root";
     String password = "0606";
   

    public Connection connect() {

        try {
            Class.forName(driver);
            System.out.println("Connected to the database");

        } catch (ClassNotFoundException e) {
            System.out.println("Connection failed" + e);
        }
        try {
            DBConnection = DriverManager.getConnection(url, userName, password);
            System.out.println("Database connected");

        } catch (SQLException e) {
            System.out.println("No Database" + e);
        }
        return DBConnection;

    }

}
