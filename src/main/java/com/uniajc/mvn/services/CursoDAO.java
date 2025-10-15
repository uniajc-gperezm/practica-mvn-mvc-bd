
package com.uniajc.mvn.services;



import com.uniajc.mvn.modelo.Curso;
import java.util.ArrayList;

public interface CursoDAO {
    void registrar(Curso curso);
    ArrayList<Curso> obtenerTodos();
    void actualizar(Curso curso);
    void eliminar(int id);
    Curso obtenerPorId(int id);
}