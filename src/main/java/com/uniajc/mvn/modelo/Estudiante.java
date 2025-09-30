package com.uniajc.mvn.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
<<<<<<< HEAD
import java.util.List;
import java.util.ArrayList;
=======
import java.util.ArrayList;
import java.util.List;


>>>>>>> 90c7a3c3a1ec99814739230e71e9271ff4052da3

public class Estudiante {
  private String nombre;
  private int edad;

  public Estudiante() {
    this.nombre = "";
    this.edad = 0;
  }

  public Estudiante(String nombre, int edad) {
    this.nombre = nombre;
    this.edad = edad;
  }

  public String getNombre() {
    return this.nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getEdad() {
    return this.edad;
  }

  public void setEdad(int edad) {
    this.edad = edad;
  }

  public static void insertarEstudiante(Estudiante estudiante) {
<<<<<<< HEAD

    String sql = "INSERT INTO estudiante (nombre, edad) VALUES (?, ?)";

=======
    String sql = "INSERT INTO estudiante (nombre, edad) VALUES (?, ?)";
>>>>>>> 90c7a3c3a1ec99814739230e71e9271ff4052da3
    try {
      Connection conexion = ConexionDatabase.getConnection();

      PreparedStatement preparedStatement = conexion.prepareStatement(sql);
<<<<<<< HEAD

      preparedStatement.setString(1, estudiante.getNombre());
      preparedStatement.setInt(2, estudiante.getEdad());

      // Ejecutar la sentencias SQL INSERT, UPDATE o DELETE
      preparedStatement.executeUpdate();
    } catch (Exception e) {
      System.out.println("Error al insertar el estudiante: " + e.getMessage());
      e.printStackTrace();
    }

  }

  public static List<Estudiante> obtenerTodosLosEstudiantes() {

    List<Estudiante> estudiantes = new ArrayList<>();

=======
      preparedStatement.setString(1, estudiante.getNombre());
      preparedStatement.setInt(2, estudiante.getEdad());

      //Ejecutar la sentencia SQL Insert, update, delete
      preparedStatement.executeUpdate();
  } catch (Exception e) {
    System.out.println("Error al insertar estudiante: " + e.getMessage());
      e.printStackTrace();}
  }

   public static List<Estudiante> obtenerTodosLosEstudiantes() {
    List<Estudiante> estudiantes = new ArrayList<>();
>>>>>>> 90c7a3c3a1ec99814739230e71e9271ff4052da3
    String sql = "SELECT nombre, edad FROM estudiante";

    try {
      Connection conexion = ConexionDatabase.getConnection();
<<<<<<< HEAD

=======
>>>>>>> 90c7a3c3a1ec99814739230e71e9271ff4052da3
      Statement statement = conexion.createStatement();

      // Ejecutar la sentencias SQL SELECT
      ResultSet resultSet = statement.executeQuery(sql);

      while (resultSet.next()) {
        String nombre = resultSet.getString("nombre");
        int edad = resultSet.getInt("edad");
        Estudiante estudiante = new Estudiante(nombre, edad);
        estudiantes.add(estudiante);
      }

    } catch (Exception e) {
      System.out.println("Error al insertar el estudiante: " + e.getMessage());
      e.printStackTrace();
    }

    return estudiantes;
  }

<<<<<<< HEAD
=======
  public static void actualizarEstudiante(Estudiante estudiante) {
    String sql = "UPDATE estudiante SET edad = ? WHERE nombre = ?";
    try {
      Connection conexion = ConexionDatabase.getConnection();

      PreparedStatement preparedStatement = conexion.prepareStatement(sql);
      preparedStatement.setInt(1, estudiante.getEdad());
      preparedStatement.setString(2, estudiante.getNombre());

      // Ejecutar la sentencia SQL Update
      preparedStatement.executeUpdate();
    } catch (Exception e) {
      System.out.println("Error al actualizar el estudiante: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public static void eliminarEstudiante(String nombre) {
    String sql = "DELETE FROM estudiante WHERE nombre = ?";
    try {
      Connection conexion = ConexionDatabase.getConnection();

      PreparedStatement preparedStatement = conexion.prepareStatement(sql);
      preparedStatement.setString(1, nombre);

      // Ejecutar la sentencia SQL Delete
      preparedStatement.executeUpdate();
    } catch (Exception e) {
      System.out.println("Error al eliminar el estudiante: " + e.getMessage());
      e.printStackTrace();
    }
  }

  

>>>>>>> 90c7a3c3a1ec99814739230e71e9271ff4052da3
}
