package com.uniajc.mvn.vista;
import com.uniajc.mvn.modelo.Estudiante;

import java.util.List;

public class VistaEstudiante {
    public void mostrarDetallesEstudiante(List<Estudiante> estudiantes) {
      
        /*estudiantes.forEach(estudiante -> {
            System.out.println("Id: " + estudiante.getId() + "Nombre: " + estudiante.getNombre() + " - " + "Edad: " + estudiante.getEdad());
        });*/

        estudiantes.forEach(estudiante -> {
            System.out.println("Nombre: " + estudiante.getNombre() + " - " + "Edad: " + estudiante.getEdad());
        });
    }
}
