
package com.uniajc.mvn.services;




import com.uniajc.mvn.modelo.Estudiante;
import java.util.ArrayList;

public interface EstudianteDAO {
    void registrar(Estudiante estudiante);
    ArrayList<Estudiante> obtenerTodos();
    void actualizar(Estudiante estudiante);
    void eliminar(int id);
    Estudiante obtenerPorId(int id);
}