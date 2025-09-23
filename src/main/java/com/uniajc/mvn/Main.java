package com.uniajc.mvn;
import java.util.Scanner;
import com.uniajc.mvn.controlador.ControladorEstudiante;
import com.uniajc.mvn.modelo.Estudiante;
import com.uniajc.mvn.vista.VistaEstudiante;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        VistaEstudiante vista = new VistaEstudiante();
        ControladorEstudiante controlador = new ControladorEstudiante(new Estudiante("", 0), vista);

        System.out.print("Ingrese el nombre del estudiante: ");
        String nombre = sc.nextLine();

        System.out.print("Ingrese la edad del estudiante: ");
        int edad = sc.nextInt();

        Estudiante estudiante = new Estudiante(nombre, edad);

        controlador.agregarEstudiante(estudiante);  
        controlador.actualizarVista();              
        controlador.guardarEnBD(estudiante);        

        sc.close();
    }
}