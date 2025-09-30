package com.uniajc.mvn.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

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

  // public static void insertarEstudiante(Estudiante estudiante) {

  //   String sql = "INSERT INTO estudiante (nombre, edad) VALUES (?, ?)";

  //   try {
  //     Connection conexion = ConexionDatabase.getConnection();

  //     PreparedStatement preparedStatement = conexion.prepareStatement(sql);

  //     preparedStatement.setString(1, estudiante.getNombre());
  //     preparedStatement.setInt(2, estudiante.getEdad());

  //     // Ejecutar la sentencias SQL INSERT, UPDATE o DELETE
  //     preparedStatement.executeUpdate();
  //   } catch (Exception e) {
  //     System.out.println("Error al insertar el estudiante: " + e.getMessage());
  //     e.printStackTrace();
  //   }

  // }

  // public static List<Estudiante> obtenerTodosLosEstudiantes() {

  //   List<Estudiante> estudiantes = new ArrayList<>();

  //   String sql = "SELECT nombre, edad FROM estudiante";

  //   try {
  //     Connection conexion = ConexionDatabase.getConnection();

  //     Statement statement = conexion.createStatement();

  //     // Ejecutar la sentencias SQL SELECT
  //     ResultSet resultSet = statement.executeQuery(sql);

  //     while (resultSet.next()) {
  //       String nombre = resultSet.getString("nombre");
  //       int edad = resultSet.getInt("edad");
  //       Estudiante estudiante = new Estudiante(nombre, edad);
  //       estudiantes.add(estudiante);
  //     }

  //   } catch (Exception e) {
  //     System.out.println("Error al insertar el estudiante: " + e.getMessage());
  //     e.printStackTrace();
  //   }

  //   return estudiantes;

    public static void eliminarEstudiantePorId(int id) {
    String sql = "DELETE FROM estudiante WHERE id = ?";
    try (Connection conexion = ConexionDatabase.getConnection();
         PreparedStatement ps = conexion.prepareStatement(sql)) {
        ps.setInt(1, id);
        ps.executeUpdate();
    } catch (Exception e) {
        System.out.println("Error al eliminar el estudiante: " + e.getMessage());
    }
}

public static void actualizarEstudiantePorId(int id, String nombre, int edad) {
    String sql = "UPDATE estudiante SET nombre = ?, edad = ? WHERE id = ?";
    try (Connection conexion = ConexionDatabase.getConnection();
         PreparedStatement ps = conexion.prepareStatement(sql)) {
        ps.setString(1, nombre);
        ps.setInt(2, edad);
        ps.setInt(3, id);
        ps.executeUpdate();
    } catch (Exception e) {
        System.out.println("Error al actualizar el estudiante: " + e.getMessage());
    }
}

public static List<Estudiante> buscarPorNombre(String nombre) {
    List<Estudiante> estudiantes = new ArrayList<>();
    String sql = "SELECT * FROM estudiante WHERE nombre LIKE ?";
    try (Connection conexion = ConexionDatabase.getConnection();
         PreparedStatement ps = conexion.prepareStatement(sql)) {
        ps.setString(1, "%" + nombre + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            estudiantes.add(new Estudiante(rs.getString("nombre"), rs.getInt("edad")));
        }
    } catch (Exception e) {
        System.out.println("Error al buscar por nombre: " + e.getMessage());
    }
    return estudiantes;
}

public static List<Estudiante> buscarPorEdad(int edad) {
    List<Estudiante> estudiantes = new ArrayList<>();
    String sql = "SELECT * FROM estudiante WHERE edad = ?";
    try (Connection conexion = ConexionDatabase.getConnection();
         PreparedStatement ps = conexion.prepareStatement(sql)) {
        ps.setInt(1, edad);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            estudiantes.add(new Estudiante(rs.getString("nombre"), rs.getInt("edad")));
        }
    } catch (Exception e) {
        System.out.println("Error al buscar por edad: " + e.getMessage());
    }
    return estudiantes;
}

public static Estudiante buscarPorId(int id) {
    String sql = "SELECT * FROM estudiante WHERE id = ?";
    try (Connection conexion = ConexionDatabase.getConnection();
         PreparedStatement ps = conexion.prepareStatement(sql)) {
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Estudiante(rs.getString("nombre"), rs.getInt("edad"));
        }
    } catch (Exception e) {
        System.out.println("Error al buscar por id: " + e.getMessage());
    }
    return null;
}
  }
