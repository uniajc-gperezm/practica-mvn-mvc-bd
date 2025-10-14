// src/main/java/com/uniajc/mvn/controlador/DocenteControlador.java
package com.uniajc.mvn.controlador;
// src/main/java/com/uniajc/mvn/controlador/DocenteControlador.java


import com.uniajc.mvn.modelo.Docente;
import com.uniajc.mvn.services.DocenteDAO;
import com.uniajc.mvn.services.DocenteDAOImpl;
import java.util.ArrayList;

public class DocenteControlador {

    private final DocenteDAO docenteDAO = new DocenteDAOImpl();

    public void registrarDocente(String nombre, String departamento) {
        Docente nuevoDocente = new Docente();
        nuevoDocente.setNombre(nombre);
        nuevoDocente.setDepartamento(departamento);
        docenteDAO.registrar(nuevoDocente);
    }
    
    public ArrayList<Docente> obtenerTodos() {
        return docenteDAO.obtenerTodos();
    }
    
    public void actualizarDocente(int id, String nombre, String departamento) {
        Docente docenteAActualizar = new Docente(id, nombre, departamento);
        docenteDAO.actualizar(docenteAActualizar);
    }
    
    public void eliminarDocente(int id) {
        docenteDAO.eliminar(id);
    }
    
    public Docente obtenerPorId(int id) {
        return docenteDAO.obtenerPorId(id);
    }
}