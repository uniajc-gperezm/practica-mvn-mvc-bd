package com.uniajc.mvn.controlador;
import com.uniajc.mvn.ConexionBDD;
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
    
    public void agregarEstudiante(Estudiante estudiante) {
        this.estudiantes.add(estudiante);
    }

    public static void guardarEnBD(Estudiante estudiante) {
    String sql = "INSERT INTO estudiante (nombre, edad) VALUES (?, ?)";
    try (Connection conn = ConexionBDD.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, estudiante.getNombre());
        stmt.setInt(2, estudiante.getEdad());
        stmt.executeUpdate();
        System.out.println("Los datos del estudiante " + estudiante.getNombre() + "han sido guardado en la BDD");
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}
   
}


