
package com.uniajc.mvn.controlador;



import com.uniajc.mvn.modelo.Curso;
import com.uniajc.mvn.services.CursoDAO;
import com.uniajc.mvn.services.CursoDAOImpl;
import java.util.ArrayList;

public class CursoControlador {

    private final CursoDAO cursoDAO = new CursoDAOImpl();

    public void registrarCurso(String nombre, int creditos) {
        Curso nuevoCurso = new Curso();
        nuevoCurso.setNombre(nombre);
        nuevoCurso.setCreditos(creditos);
        cursoDAO.registrar(nuevoCurso);
    }
    
    public ArrayList<Curso> obtenerTodos() {
        return cursoDAO.obtenerTodos();
    }

    public void actualizarCurso(int id, String nombre, int creditos) {
        Curso cursoAActualizar = new Curso(id, nombre, creditos);
        cursoDAO.actualizar(cursoAActualizar);
    }
    
    public void eliminarCurso(int id) {
        cursoDAO.eliminar(id);
    }
    
    public Curso obtenerPorId(int id) {
        return cursoDAO.obtenerPorId(id);
    }
}