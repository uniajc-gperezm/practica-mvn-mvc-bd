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

    // Scanner scanner = new Scanner(System.in);
    Estudiante estudiante = new Estudiante();
    estudiante.setNombre("Leonel Messi");
    estudiante.setEdad(38);

    VistaEstudiante vista = new VistaEstudiante();
    ControladorEstudiante controlador = new ControladorEstudiante(estudiante, vista);

    controlador.agregarEstudiante(estudiante);
    controlador.actualizarVista();

    //Estudiantes para actualizar
    Estudiante estudianteActualizado = new Estudiante();
    estudianteActualizado.setNombre("Jaime Araujo");
    estudianteActualizado.setEdad(23);
    
    controlador.actualizarEstudiante("Leonel Messi", estudianteActualizado);
    controlador.actualizarVista();

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