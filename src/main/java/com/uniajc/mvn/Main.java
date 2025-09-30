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
        if (conexion != null) {
            System.out.println("Conexión exitosa a la base de datos.");
        } else {
            System.out.println("No se pudo conectar a la base de datos.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        VistaEstudiante vista = new VistaEstudiante();
        ControladorEstudiante controlador = new ControladorEstudiante(new Estudiante(), vista);

        boolean salir = false;
        while (!salir) {
            System.out.println("\nMenú:");
            System.out.println("1. Agregar estudiante");
            System.out.println("2. Listar estudiantes");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Edad: ");
                    int edad = scanner.nextInt();
                    scanner.nextLine();
                    Estudiante estudiante = new Estudiante(nombre, edad);
                    controlador.eliminarEstudiante(estudiante);
                    break;
                case 2:
                    vista.mostrarDetallesEstudiante(controlador.listarTodosLosEstudiantes());
                    break;
                case 3:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
        scanner.close();
    }
}


// package com.uniajc.mvn;

// import java.sql.Connection;
// import java.util.List;
// import java.util.Scanner;

// import com.uniajc.mvn.controlador.ControladorEstudiante;
// import com.uniajc.mvn.modelo.ConexionDatabase;
// import com.uniajc.mvn.modelo.Estudiante;
// import com.uniajc.mvn.vista.VistaEstudiante;

// public class Main {
// public static void main(String[] args) {

//   Connection conexion = ConexionDatabase.getConnection();

//   Scanner scanner = new Scanner(System.in);
//   // Estudiante estudiante = new Estudiante();
//   // estudiante.setNombre("Leonel Messi");
//   // estudiante.setEdad(38);

//   // VistaEstudiante vista = new VistaEstudiante();
//   // ControladorEstudiante controlador = new ControladorEstudiante(estudiante, vista);

//   // controlador.agregarEstudiante(estudiante);
//   // controlador.actualizarVista();

//   // System.out.print("Ingrese el nombre del estudiante: ");
//   // String nombre = scanner.nextLine();

//   // System.out.print("Ingrese la edad del estudiante: ");
//   // int edad = scanner.nextInt();

//   // Estudiante estudiante = new Estudiante(nombre, edad);
//   // controlador.agregarEstudiante(estudiante);
//   // controlador.actualizarVista();
//  scanner.close();
// }

// public void mostrarEstudiante(Estudiante estudiante) {
//   if (estudiante != null) {
//       System.out.println("Nombre: " + estudiante.getNombre() + " - Edad: " + estudiante.getEdad());
//   } else {
//       System.out.println("Estudiante no encontrado.");
//   }
// }

// public void mostrarListaEstudiantes(List <Estudiante> estudiantes) {
//   estudiantes.forEach(e -> System.out.println("Nombre: " + e.getNombre() + " - Edad: " + e.getEdad()));
// }
// }