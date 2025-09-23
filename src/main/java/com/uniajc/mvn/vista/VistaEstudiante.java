package com.ejerciciomaven.vistaEstudiante;
import com.ejerciciomaven.modelo.Estudiante;

import java.util.List;

public class VistaEstudiante {
    public void mostrarDetallesEstudiante(List<Estudiante> estudiantes) {
      
        estudiantes.forEach(estudiante -> {
            System.out.println("Nombre: " + estudiante.getNombre() + " - " + "Edad: " + estudiante.getEdad());
        });
    }
}
