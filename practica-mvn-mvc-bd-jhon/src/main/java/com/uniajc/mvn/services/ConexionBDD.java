package com.uniajc.mvn.services;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBDD {

    private static Connection connection = null;
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConexionBDD.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.err.println("Error: Archivo config.properties no encontrado en /resources.");
                throw new RuntimeException("Falta el archivo de configuración de la BD.");
            }
            properties.load(input);
            Class.forName(properties.getProperty("db.driver")); 
        } catch (Exception e) {
            System.err.println("Error fatal en la inicialización de la BD.");
            throw new RuntimeException("Fallo en la configuración de la BD.", e); 
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = properties.getProperty("db.url");
        String username = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");
        
        if (connection == null || connection.isClosed()) {
             connection = DriverManager.getConnection(url, username, password);
        }

        return connection;
    }
}