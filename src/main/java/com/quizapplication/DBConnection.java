package com.quizapplication;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnection {
    public static Connection getConnection() {
        Connection con = null;
      

        try {
        	
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/quiz_app", "root", "root"
            );
            

            String query = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(100) NOT NULL," +
                    "email VARCHAR(100) NOT NULL UNIQUE," +
                    "password VARCHAR(100) NOT NULL" +
                    ")";

            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("✅ 'users' table created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
