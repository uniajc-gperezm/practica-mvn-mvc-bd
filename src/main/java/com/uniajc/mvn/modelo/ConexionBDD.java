package com.uniajc.mvn.modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;

public class ConexionBDD {

  private static Connection connection = null;
  public static Connection getConnection() {

    System.out.println("Intentando conectar a la base de datos...");

    if (connection == null) {

      Properties properties = new Properties();

      try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        properties.load(new FileInputStream(new File("config.properties")));

        //Par hacer la conexion a la BDD
        String url = properties.get("URL").toString();
        String username = properties.get("USERNAME").toString();
        String password = properties.get("PASSWORD").toString();

        // Establecer la conexión
        connection = DriverManager.getConnection(url, username, password);
        
        //Mensaje de conexion Exitosa
        System.out.println("La conexion con la base de datos a sido exitosa!!!");

      } catch (ClassNotFoundException e) {
        System.out.println("Error al cargar el driver JDBC: " + e.getMessage());
      } catch (SQLException e) {
        System.out.println("Error al conectar a la base de datos, por favor verifica!!: " + e.getMessage());
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {

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
