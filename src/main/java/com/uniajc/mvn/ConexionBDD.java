package com.ejerciciomaven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBDD {
    private static final String URL = "jdbc:mysql://localhost/estudiante";
    private static final String USER = "root";       
    private static final String PASSWORD = "admin"; 

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}