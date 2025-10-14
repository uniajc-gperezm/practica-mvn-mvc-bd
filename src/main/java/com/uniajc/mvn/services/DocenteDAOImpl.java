// src/main/java/com/uniajc/mvn/services/DocenteDAOImpl.java
package com.uniajc.mvn.services;
// src/main/java/com/uniajc/mvn/services/DocenteDAOImpl.java


import com.uniajc.mvn.modelo.Docente;
import java.sql.*;
import java.util.ArrayList;

public class DocenteDAOImpl implements DocenteDAO {

    @Override
    public void registrar(Docente docente) {
        String sql = "INSERT INTO docente (nombre, departamento) VALUES (?, ?)";
        try (Connection connection = ConexionBDD.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, docente.getNombre());
            ps.setString(2, docente.getDepartamento());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error al registrar docente: " + ex.getMessage(), ex);
        }
    }

    @Override
    public ArrayList<Docente> obtenerTodos() {
        String sql = "SELECT id, nombre, departamento FROM docente ORDER BY id";
        ArrayList<Docente> docentes = new ArrayList<>();
        try (Connection connection = ConexionBDD.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Docente docente = new Docente(
                    rs.getInt("id"), 
                    rs.getString("nombre"), 
                    rs.getString("departamento")
                );
                docentes.add(docente);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener docentes: " + e.getMessage(), e);
        }
        return docentes;
    }
    
    @Override
    public void actualizar(Docente docente) {
        String sql = "UPDATE docente SET nombre = ?, departamento = ? WHERE id = ?";
        try (Connection connection = ConexionBDD.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, docente.getNombre());
            ps.setString(2, docente.getDepartamento());
            ps.setInt(3, docente.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
             throw new RuntimeException("Error al actualizar docente: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM docente WHERE id = ?";
        try (Connection connection = ConexionBDD.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
             throw new RuntimeException("Error al eliminar docente: " + ex.getMessage(), ex);
        }
    }

    @Override
    public Docente obtenerPorId(int id) {
        String sql = "SELECT id, nombre, departamento FROM docente WHERE id = ?";
        Docente docente = null;
        try (Connection connection = ConexionBDD.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    docente = new Docente(rs.getInt("id"), rs.getString("nombre"), rs.getString("departamento"));
                }
            }
            return docente;
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener docente por ID: " + e.getMessage(), e);
        }
    }
}