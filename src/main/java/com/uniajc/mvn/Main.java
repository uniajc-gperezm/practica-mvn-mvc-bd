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

<<<<<<< HEAD
    // Scanner scanner = new Scanner(System.in);
    Estudiante estudiante = new Estudiante();
    estudiante.setNombre("Leonel Messi");
    estudiante.setEdad(38);
=======
    //Agregar estudiante
    Scanner scanner = new Scanner(System.in);
    Estudiante estudiante = new Estudiante();
    estudiante.setNombre("Valeri 2");
    estudiante.setEdad(22);
>>>>>>> 90c7a3c3a1ec99814739230e71e9271ff4052da3

    VistaEstudiante vista = new VistaEstudiante();
    ControladorEstudiante controlador = new ControladorEstudiante(estudiante, vista);

    controlador.agregarEstudiante(estudiante);
    controlador.actualizarVista();
<<<<<<< HEAD
=======

    //Actualizar estudiante
    estudiante.setNombre("valeri 2");
    estudiante.setEdad(22); // Nueva edad
    controlador.actualizarEstudiante(estudiante);


    //Eliminar estudiante
    System.out.print("Ingrese el nombre del estudiante a eliminar: ");
    String nombreEliminar = scanner.nextLine();

    Estudiante estudianteEliminar = new Estudiante();
    estudianteEliminar.setNombre(nombreEliminar);
    controlador.eliminarEstudiante(estudiante);
>>>>>>> 90c7a3c3a1ec99814739230e71e9271ff4052da3

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