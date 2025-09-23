import java.util.Scanner;

import controlador.*;
import modelo.Estudiante;
import vista.vistaEstudiante;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Estudiante modelo = new Estudiante("", 0);
        vistaEstudiante vista = new vistaEstudiante();
        ControladorEstudiante controlador = new ControladorEstudiante(modelo, vista);

        System.out.print("Ingrese el nombre del estudiante: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese la edad del estudiante: ");
        int edad = scanner.nextInt();

        Estudiante estudiante = new Estudiante(nombre, edad);
        controlador.agregarEstudiante(estudiante);
        controlador.actualizarVista();
        scanner.close();
    }
}