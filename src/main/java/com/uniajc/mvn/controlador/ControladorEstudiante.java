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
    private Estudiante modelo;

    public ControladorEstudiante (Estudiante modelo, VistaEstudiante vista){
        this.modelo = modelo;
        this.vista = vista;
        this.estudiantes = new ArrayList<Estudiante>();

    }
    
    public void actualizarVista(){
        List<Estudiante> estudiantes = listarTodosLosEstudiantes();
        vista.mostrarDetallesEstudiante(estudiantes);
    }
    

    public void agregarEstudiante (Estudiante nuevoEstudiante){
        nuevoEstudiante.insertarEstudiante(nuevoEstudiante);
        System.out.println("Los datos del estudiante han sido guardado en la BDD");
    }

    public List<Estudiante> listarTodosLosEstudiantes() {
        return Estudiante.obtenerTodosLosEstudiantes();
    }

    public void removerEstudiante (String nombre){
        modelo.eliminarEstudiante(nombre);    
    }

    public void actualizarDatosEstudiante (Estudiante estudianteActualizado){
        modelo.actualizarEstudiante(estudianteActualizado);
    }


   
}


