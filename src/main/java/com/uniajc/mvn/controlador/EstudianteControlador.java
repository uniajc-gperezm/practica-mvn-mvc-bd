// src/main/java/com/uniajc/mvn/controlador/EstudianteControlador.java
package com.uniajc.mvn.controlador;

// src/main/java/com/uniajc/mvn/controlador/EstudianteControlador.java


import com.uniajc.mvn.modelo.Estudiante;
import com.uniajc.mvn.services.EstudianteDAO;
import com.uniajc.mvn.services.EstudianteDAOImpl;
import java.util.ArrayList;

public class EstudianteControlador {

    private final EstudianteDAO estudianteDAO = new EstudianteDAOImpl();

    public void registrarEstudiante(String nombre, int edad) {
        Estudiante nuevoEstudiante = new Estudiante();
        nuevoEstudiante.setNombre(nombre);
        nuevoEstudiante.setEdad(edad);
        estudianteDAO.registrar(nuevoEstudiante);
    }
    
    public ArrayList<Estudiante> obtenerTodos() {
        return estudianteDAO.obtenerTodos();
    }

    public void actualizarEstudiante(int id, String nombre, int edad) {
        Estudiante estudianteAActualizar = new Estudiante(id, nombre, edad);
        estudianteDAO.actualizar(estudianteAActualizar);
    }
    
    public void eliminarEstudiante(int id) {
        estudianteDAO.eliminar(id);
    }
    
    public Estudiante obtenerPorId(int id) {
        return estudianteDAO.obtenerPorId(id);
    }
}