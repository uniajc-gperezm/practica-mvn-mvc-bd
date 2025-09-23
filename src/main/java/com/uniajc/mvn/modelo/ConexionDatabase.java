package com.uniajc.mvn.modelo;

// Imports for SQL connections
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class ConexionDatabase {

  private static Connection connection = null;

  public static Connection getConnection() {

    System.out.println("Intentando conectar a la base de datos...");

    if (connection == null) {
      try {
        // Cargar el driver JDBC
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Variables de conexión
        String url = "";
        String username = "";
        String password = "";

        // Establecer la conexión
        connection = DriverManager.getConnection(url, username, password);

        System.out.println("Conexión exitosa a la base de datos.");

      } catch (ClassNotFoundException e) {
        System.out.println("Error al cargar el driver JDBC: " + e.getMessage());
      } catch (SQLException e) {
        System.out.println("Error al conectar a la base de datos: " + e.getMessage());
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
