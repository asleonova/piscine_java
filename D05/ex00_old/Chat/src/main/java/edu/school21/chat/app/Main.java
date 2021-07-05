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

            FileInputStream fileInput = new FileInputStream("/target/classes/schema.sql");
            Scanner sc = new Scanner(fileInput).useDelimiter(";");


            Main app = new Main();
            connection = app.connect();
            stmt = connection.createStatement();

            while (sc.hasNext()) {
                createSql = sc.next().trim();
                createSql += ";";
                stmt.executeUpdate(createSql);
            }
//            createSql = "CREATE SCHEMA IF NOT EXISTS chat;";
//            stmt.executeUpdate(createSql);
//            createSql = "CREATE TABLE IF NOT EXISTS chat.user(userID SERIAL PRIMARY KEY, login varchar(30) NOT NULL UNIQUE);";
//
//            createSql = "CREATE TABLE IF NOT EXISTS chat.chatroom (chatID SERIAL PRIMARY KEY, chatName varchar(30) NOT NULL UNIQUE, chatOwner int REFERENCES chat.user(userID) NOT NULL);";
//            stmt.executeUpdate(createSql);
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
