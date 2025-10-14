// src/main/java/com/uniajc/mvn/services/CursoDAO.java
package com.uniajc.mvn.services;
// src/main/java/com/uniajc/mvn/services/CursoDAO.java


import com.uniajc.mvn.modelo.Curso;
import java.util.ArrayList;

public interface CursoDAO {
    void registrar(Curso curso);
    ArrayList<Curso> obtenerTodos();
    void actualizar(Curso curso);
    void eliminar(int id);
    Curso obtenerPorId(int id);
}