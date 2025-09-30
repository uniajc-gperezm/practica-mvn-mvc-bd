package com.uniajc.mvn;

import java.sql.Connection;
import java.util.Scanner;

import com.uniajc.mvn.controlador.ControladorEstudiante;
import com.uniajc.mvn.modelo.ConexionDatabase;
import com.uniajc.mvn.modelo.Estudiante;
import com.uniajc.mvn.vista.VistaEstudiante;

public class Main {
  public static void main(String[] args) {

    Connection conexion = ConexionDatabase.getConnection();

    Scanner scanner = new Scanner(System.in);
    Estudiante estudiante = new Estudiante();
     estudiante.setNombre("Valeri 2");
     estudiante.setEdad(22);

    VistaEstudiante vista = new VistaEstudiante();
    ControladorEstudiante controlador = new ControladorEstudiante(estudiante, vista);

     controlador.agregarEstudiante(estudiante);
     controlador.actualizarVista();

    // estudiante.setNombre("valeri 2");
    //estudiante.setEdad(22); // Nueva edad
    //controlador.actualizarEstudiante(estudiante);

    //System.out.print("Ingrese el nombre del estudiante a eliminar: ");
    //String nombreEliminar = scanner.nextLine();

    //Estudiante estudianteEliminar = new Estudiante();
    //estudianteEliminar.setNombre(nombreEliminar);
    //controlador.eliminarEstudiante(estudiante);

    // System.out.print("Ingrese el nombre del estudiante: ");
    // String nombre = scanner.nextLine();

    // System.out.print("Ingrese la edad del estudiante: ");
    // int edad = scanner.nextInt();

    // Estudiante estudiante = new Estudiante(nombre, edad);
    // controlador.agregarEstudiante(estudiante);
    // controlador.actualizarVista();
    // scanner.close();
  }
}