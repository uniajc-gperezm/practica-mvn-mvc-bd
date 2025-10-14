// src/main/java/com/uniajc/mvn/services/CursoDAOImpl.java
package com.uniajc.mvn.services;

// src/main/java/com/uniajc/mvn/services/CursoDAOImpl.java


import com.uniajc.mvn.modelo.Curso;
import java.sql.*;
import java.util.ArrayList;

public class CursoDAOImpl implements CursoDAO {

    @Override
    public void registrar(Curso curso) {
        String sql = "INSERT INTO curso (nombre, creditos) VALUES (?, ?)";
        try (Connection connection = ConexionBDD.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, curso.getNombre());
            ps.setInt(2, curso.getCreditos());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error al registrar curso: " + ex.getMessage(), ex);
        }
    }

    @Override
    public ArrayList<Curso> obtenerTodos() {
        String sql = "SELECT id, nombre, creditos FROM curso ORDER BY id";
        ArrayList<Curso> cursos = new ArrayList<>();
        try (Connection connection = ConexionBDD.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Curso curso = new Curso(
                    rs.getInt("id"), 
                    rs.getString("nombre"), 
                    rs.getInt("creditos")
                );
                cursos.add(curso);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener cursos: " + e.getMessage(), e);
        }
        return cursos;
    }
    
    @Override
    public void actualizar(Curso curso) {
        String sql = "UPDATE curso SET nombre = ?, creditos = ? WHERE id = ?";
        try (Connection connection = ConexionBDD.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, curso.getNombre());
            ps.setInt(2, curso.getCreditos());
            ps.setInt(3, curso.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
             throw new RuntimeException("Error al actualizar curso: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM curso WHERE id = ?";
        try (Connection connection = ConexionBDD.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
             throw new RuntimeException("Error al eliminar curso: " + ex.getMessage(), ex);
        }
    }
    
    @Override
    public Curso obtenerPorId(int id) {
        String sql = "SELECT id, nombre, creditos FROM curso WHERE id = ?";
        Curso curso = null;
        try (Connection connection = ConexionBDD.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    curso = new Curso(rs.getInt("id"), rs.getString("nombre"), rs.getInt("creditos"));
                }
            }
            return curso;
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener curso por ID: " + e.getMessage(), e);
        }
    }
}