package com.uniajc.mvn.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//import com.mysql.cj.xdevapi.Statement;

public class Estudiante {

    private String nombre;
    private int edad;

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
        String sql = "INSERT INTO estudiante (nombre, edad) VALUES (?,?)";
        try (Connection conn = ConexionBDD.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, estudiante.getNombre());
            stmt.setInt(2, estudiante.getEdad());
            stmt.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static List<Estudiante> obtenerTodosLosEstudiantes() {

        List<Estudiante> estudiantes = new ArrayList<>();

        String sql = "SELECT nombre, edad FROM estudiante";

        try {
            Connection conexion = ConexionBDD.getConnection();

            Statement statement = conexion.createStatement();

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

    public static void eliminarEstudiante(String nombre) {
    String sql = "DELETE FROM estudiante WHERE nombre = ?";
    try (Connection conn = ConexionBDD.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, nombre);
        stmt.executeUpdate();
        

        } catch (Exception ex) {
        ex.printStackTrace();
        }
    }

    public static void actualizarEstudiante(Estudiante estudiante) {
        String sql = "UPDATE estudiante SET edad = ? WHERE nombre = ?";
        try (Connection conn = ConexionBDD.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, estudiante.getEdad());
            stmt.setString(2, estudiante.getNombre());
            stmt.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}