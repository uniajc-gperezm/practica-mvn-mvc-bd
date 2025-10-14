// src/main/java/com/uniajc/mvn/services/EstudianteDAOImpl.java
package com.uniajc.mvn.services;

import com.uniajc.mvn.modelo.Estudiante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

public class EstudianteDAOImpl implements EstudianteDAO {

    // He movido aquí la lógica CRUD que estaba en tu modelo Estudiante.java
    @Override
    public void registrar(Estudiante estudiante) {
        String sql = "INSERT INTO estudiante (nombre, edad) VALUES (?, ?)";
        try (Connection connection = ConexionBDD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, estudiante.getNombre());
            preparedStatement.setInt(2, estudiante.getEdad());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error al registrar estudiante en DB: " + ex.getMessage(), ex);
        }
    }

    @Override
    public ArrayList<Estudiante> obtenerTodos() {
        String sql = "SELECT id, nombre, edad FROM estudiante";
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        try (Connection connection = ConexionBDD.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Estudiante estudiante = new Estudiante(
                    resultSet.getInt("id"), 
                    resultSet.getString("nombre"), 
                    resultSet.getInt("edad")
                );
                estudiantes.add(estudiante);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener estudiantes de DB: " + e.getMessage(), e);
        }
        return estudiantes;
    }

    @Override
    public void actualizar(Estudiante estudiante) {
        String sql = "UPDATE estudiante SET nombre = ?, edad = ? WHERE id = ?";
        try (Connection connection = ConexionBDD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, estudiante.getNombre());
            preparedStatement.setInt(2, estudiante.getEdad());
            preparedStatement.setInt(3, estudiante.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
             throw new RuntimeException("Error al actualizar estudiante en DB: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM estudiante WHERE id = ?";
        try (Connection connection = ConexionBDD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
             throw new RuntimeException("Error al eliminar estudiante en DB: " + ex.getMessage(), ex);
        }
    }
    
    @Override
    public Estudiante obtenerPorId(int id) {
        String sql = "SELECT id, nombre, edad FROM estudiante WHERE id = ?";
        Estudiante estudiante = null;
        try (Connection connection = ConexionBDD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    estudiante = new Estudiante(
                        resultSet.getInt("id"), 
                        resultSet.getString("nombre"), 
                        resultSet.getInt("edad")
                    );
                }
            }
            return estudiante;
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener estudiante por ID: " + e.getMessage(), e);
        }
    }
}