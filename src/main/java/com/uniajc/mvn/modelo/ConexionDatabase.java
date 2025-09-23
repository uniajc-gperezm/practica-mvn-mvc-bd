package com.uniajc.mvn.modelo;

// Imports for SQL connections
import java.sql.DriverManager;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ConexionDatabase {

  private static Connection connection = null;

  public static Connection getConnection() {

    System.out.println("Intentando conectar a la base de datos...");

    if (connection == null) {

      Properties properties = new Properties();

      try {
        // Cargar el driver JDBC
        Class.forName("com.mysql.cj.jdbc.Driver");

        properties.load(new FileInputStream(new File("config.properties")));

        // Configurar los parámetros de conexión
        String url = properties.get("URL").toString();
        String username = properties.get("USERNAME").toString();
        String password = properties.get("PASSWORD").toString();

        // Establecer la conexión
        connection = DriverManager.getConnection(url, username, password);

        System.out.println("Conexión exitosa a la base de datos.");

      } catch (ClassNotFoundException e) {
        System.out.println("Error al cargar el driver JDBC: " + e.getMessage());
      } catch (SQLException e) {
        System.out.println("Error al conectar a la base de datos: " + e.getMessage());
      } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        // System.out.println("Archivo de configuración no encontrado: " +
        // e.getMessage());
        e.printStackTrace();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return connection;
  }

  public static void closeConnection() {
    if (connection != null) {
      try {
        connection.close();
        System.out.println("Conexión cerrada.");
      } catch (SQLException e) {
        System.out.println("Error al cerrar la conexión: " + e.getMessage());
      }
    }
  }

}
