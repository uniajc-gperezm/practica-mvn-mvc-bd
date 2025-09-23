package vista;
import java.util.List;

import modelo.Estudiante;
public class vistaEstudiante {
    public void mostrarDetallesEstudiante(List<Estudiante> estudiantes) {

        /*  for (Estudiante estudiante : estudiantes) {
            System.out.println("Detalles del Estudiante:");
            System.out.println("Nombre: " + estudiante.getNombre());
            System.out.println("Edad: " + estudiante.getEdad());
        }*/
        estudiantes.forEach(estudiante -> {
            System.out.println("Nombre: " + estudiante.getNombre() + " - " + "Edad: " + estudiante.getEdad());
        });
    }

}