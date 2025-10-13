
package com.uniajc.mvn.modelo;

// ELIMINAR: import javafx.scene.control.Alert; 
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import com.uniajc.mvn.services.ConexionBDD;

public class Estudiante {

    private int id;
    private String nombre;
    private int edad;
    // ELIMINAR CAMPOS: final private static Alert...

    public Estudiante(int id, String nombre, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
    }
    public Estudiante() {}
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return this.nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getEdad() { return this.edad; }
    public void setEdad(int edad) { this.edad = edad; }

    // Los métodos CRUD ahora lanzan Exception
    public void registrarEstudiante(String nombre, int edad) throws Exception {
        String sql = "INSERT INTO estudiante (nombre, edad) VALUES (?, ?)";

        try (Connection connection = ConexionBDD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, nombre);
            preparedStatement.setInt(2, edad);
            preparedStatement.executeUpdate();

        } catch (Exception ex) {
            throw new Exception("Error al registrar el estudiante en la BD.", ex); 
        }
    }

    public ArrayList<Estudiante> obtenerEstudiantes() throws Exception {
        String sql = "SELECT id, nombre, edad FROM estudiante";
        ArrayList<Estudiante> estudiantes = new ArrayList<>();

        try (Connection connection = ConexionBDD.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Estudiante estudiante = new Estudiante(resultSet.getInt("id"), resultSet.getString("nombre"), resultSet.getInt("edad"));
                estudiantes.add(estudiante);
            }
        } catch (Exception e) {
            throw new Exception("No fue posible obtener los estudiantes.", e); 
        }
        return estudiantes;
    }

    public void actualizarEstudiante(int id, String nombre, int edad) throws Exception {
        String sql = "UPDATE estudiante SET nombre = ?, edad = ? WHERE id = ?";

        try (Connection connection = ConexionBDD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, nombre);
            preparedStatement.setInt(2, edad);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();

        } catch (Exception ex) {
            throw new Exception("No fue posible actualizar la información del estudiante.", ex); 
        }
    }

    public void eliminarEstudiantes(int id) throws Exception {
        String sql = "DELETE FROM estudiante WHERE id = ?";

        try (Connection connection = ConexionBDD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (Exception ex) {
            throw new Exception("No fue posible eliminar el estudiante.", ex); 
        }
    }
}