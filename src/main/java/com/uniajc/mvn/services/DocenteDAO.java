// src/main/java/com/uniajc/mvn/services/DocenteDAO.java
package com.uniajc.mvn.services;

// src/main/java/com/uniajc/mvn/services/DocenteDAO.java


import com.uniajc.mvn.modelo.Docente;
import java.util.ArrayList;

public interface DocenteDAO {
    void registrar(Docente docente);
    ArrayList<Docente> obtenerTodos();
    void actualizar(Docente docente);
    void eliminar(int id);
    Docente obtenerPorId(int id);
}