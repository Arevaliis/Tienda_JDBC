package app;

import util.DatabaseConnection;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        try{
            Connection connection = DatabaseConnection.getConnection();

        } catch (Exception e) { System.err.println(e.getMessage()); }
    }
}