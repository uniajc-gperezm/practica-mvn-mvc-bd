package com.uniajc.mvn.controlador;

import java.util.ArrayList;
import java.util.List;

import com.uniajc.mvn.modelo.Estudiante;
import com.uniajc.mvn.vista.VistaEstudiante;

public class ControladorEstudiante {
  private List<Estudiante> estudiantes;
  private VistaEstudiante vista;

  public ControladorEstudiante(Estudiante modelo, VistaEstudiante vista) {
    this.vista = vista;
    this.estudiantes = new ArrayList<Estudiante>();

  }

//   public void actualizarVista() {
//     List<Estudiante> estudiantes = listarTodosLosEstudiantes();
//     vista.mostrarDetallesEstudiante(estudiantes);
//   }

//   public void agregarEstudiante(Estudiante estudiante) {
//     Estudiante.insertarEstudiante(estudiante);
//     System.out.println("Estudiante agregado: " + estudiante.getNombre());
//   }

//   public List<Estudiante> listarTodosLosEstudiantes() {
//     return Estudiante.obtenerTodosLosEstudiantes();
//   }

public void eliminarEstudiante(int id) {
    Estudiante.eliminarEstudiantePorId(id);
}

public void actualizarEstudiante(int id, String nombre, int edad) {
    Estudiante.actualizarEstudiantePorId(id, nombre, edad);
}

public List<Estudiante> buscarEstudiantesPorNombre(String nombre) {
    return Estudiante.buscarPorNombre(nombre);
}

public List<Estudiante> buscarEstudiantesPorEdad(int edad) {
    return Estudiante.buscarPorEdad(edad);
}

public Estudiante buscarEstudiantePorId(int id) {
    return Estudiante.buscarPorId(id);
}

}
