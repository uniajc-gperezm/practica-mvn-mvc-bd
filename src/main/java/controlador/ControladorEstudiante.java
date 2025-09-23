package controlador;
import java.util.ArrayList;
import java.util.List;

import vista.vistaEstudiante;
import modelo.Estudiante;

public class ControladorEstudiante {
    private List<Estudiante> estudiantes;
    private vistaEstudiante vista;

    public ControladorEstudiante (Estudiante modelo, vistaEstudiante vista){
        this.vista = vista;
        this.estudiantes = new ArrayList<Estudiante>();

    }
    public void actualizarVista(){
        vista.mostrarDetallesEstudiante(estudiantes);
    }
    public void agregarEstudiante(Estudiante estudiante) {
        this.estudiantes.add(estudiante);
    }

   
}