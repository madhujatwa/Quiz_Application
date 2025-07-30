package com.quizapplication;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateTable {
    public static void main(String[] args) {
        Connection con = null;
        Statement stmt = null;

        try {
            // JDBC driver load
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Database connection
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz_app", "root", "root");

            // SQL statement create
            stmt = con.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                         "id INT AUTO_INCREMENT PRIMARY KEY, " +
                         "name VARCHAR(100) NOT NULL, " +
                         "email VARCHAR(100) NOT NULL UNIQUE, " +
                         "password VARCHAR(100) NOT NULL)";

            stmt.executeUpdate(sql);
            System.out.println("✅ Users table created successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
