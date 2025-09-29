package com.uniajc.mvn.controlador;
import com.uniajc.mvn.modelo.ConexionBDD;
import com.uniajc.mvn.modelo.Estudiante;
import com.uniajc.mvn.vista.VistaEstudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


public class ControladorEstudiante {
    private List<Estudiante> estudiantes;
    private VistaEstudiante vista;

    public ControladorEstudiante (Estudiante modelo, VistaEstudiante vista){
        this.vista = vista;
        this.estudiantes = new ArrayList<Estudiante>();

    }
    
    public void actualizarVista(){
        vista.mostrarDetallesEstudiante(estudiantes);
    }
    

    public void agregarEstudiante (Estudiante nuevoEstudiante){
        nuevoEstudiante.insertarEstudiante(nuevoEstudiante);
        System.out.println("Los datos del estudiante han sido guardado en la BDD");
    }
   
}


