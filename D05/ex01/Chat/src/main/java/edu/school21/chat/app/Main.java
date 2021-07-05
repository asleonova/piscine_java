package edu.school21.chat.app;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.Scanner;

public class Main{

    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "postgres";

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    public static void main(String[] args) throws SQLException {

        Statement stmt = null;
        Connection connection = null;
        String createSql;

        try {

            FileInputStream fileInput = new FileInputStream("./src/main/resources/schema.sql");
            FileInputStream fileInputData = new FileInputStream("./src/main/resources/data.sql");
            Scanner sc = new Scanner(fileInput).useDelimiter(";");
            Scanner scData = new Scanner(fileInputData).useDelimiter(";");

            Main app = new Main();
            connection = app.connect();
            stmt = connection.createStatement();

            while (sc.hasNext()) {
                createSql = sc.next().trim();
                createSql += ";";
                stmt.executeUpdate(createSql);
            }

            while (scData.hasNext()) {
                createSql = scData.next().trim();
                createSql += ";";
                stmt.executeUpdate(createSql);
            }
            stmt.close();
            connection.close();
        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(-1);
        }
        System.out.println("Table Created successfully");
    }
}