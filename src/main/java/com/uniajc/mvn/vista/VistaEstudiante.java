package com.uniajc.mvn.vista;
import com.uniajc.mvn.modelo.Estudiante;
import com.uniajc.mvn.controlador.ControladorEstudiante;
import java.util.List;
import java.util.Scanner;

public class VistaEstudiante {
    public void mostrarDetallesEstudiante(List<Estudiante> estudiantes) {
      
        estudiantes.forEach(estudiante -> {
            System.out.println("Nombre: " + estudiante.getNombre() + " - " + "Edad: " + estudiante.getEdad());
        });
    }

    public void menu (ControladorEstudiante controlador){
        Scanner sc = new Scanner(System.in);
       int opcion;
    do {
        System.out.println("\n -------- MENÚ GESTION DE ESTUDIANTES ------");
        
        System.out.println("1. Agregar estudiante");
        System.out.println("2. Listar todos los estudiantes");
        System.out.println("3. Eliminar estudiante");
        System.out.println("4. Actualizar datos del estudiante");
        System.out.println("5. Buscar estudiante por Id");
        System.out.println("6. Salir del programa");
        
        System.out.print("Seleccione una de las opción que desea realizar: ");
        opcion = sc.nextInt();
        sc.nextLine(); 

        switch (opcion) {
            case 1:
             
                System.out.print("Ingrese el nombre del estudiante: ");
                String nombre = sc.nextLine();

                System.out.print("Ingrese la edad del estudiante: ");
                int edad = sc.nextInt();
                sc.nextLine(); 

                Estudiante estudiante = new Estudiante(nombre, edad);
                controlador.agregarEstudiante(estudiante);
                break;

            case 2:
                controlador.actualizarVista();
                break;
            case 3:
                System.out.print("Ingrese el nombre del estudiante a eliminar: ");
                String nombreEliminar = sc.nextLine();
                controlador.removerEstudiante(nombreEliminar);
                System.out.println("El estudiante ha sido eliminado de la BDD.");
                break;

            case 4: 
                System.out.print("Ingrese el nombre del estudiante a actualizar: ");
                String nombreActualizar = sc.nextLine();

                System.out.print("Ingrese la nueva edad del estudiante: ");
                int nuevaEdad = sc.nextInt();
                sc.nextLine(); 

                Estudiante estudianteActualizado = new Estudiante(nombreActualizar, nuevaEdad);
                controlador.actualizarDatosEstudiante(estudianteActualizado);
                System.out.println("Los datos del estudiante han sido actualizados en la BDD.");
                break;
            case 5:
                System.out.print("Ingrese el nombre del estudiante a buscar: ");
                int id = sc.nextInt();
                controlador.buscarEstudianteDBB(id);
                break;
            case 6:
                System.out.println("Saliendo del programa...");
                break;

            default:
                System.out.println("Opción no válida. Verifica !!");
        }

    } while (opcion != 6);

    sc.close();
    }
    
    
    }

