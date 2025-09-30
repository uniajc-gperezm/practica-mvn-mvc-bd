package com.uniajc.mvn;
import java.sql.Connection;
import java.util.Scanner;
import com.uniajc.mvn.controlador.ControladorEstudiante;
import com.uniajc.mvn.modelo.ConexionBDD;
import com.uniajc.mvn.modelo.Estudiante;
import com.uniajc.mvn.vista.VistaEstudiante;

public class Main {
    public static void main(String[] args) {

        Connection conexion = ConexionBDD.getConnection();


        Scanner sc = new Scanner(System.in);
        VistaEstudiante vista = new VistaEstudiante();
        ControladorEstudiante controlador = new ControladorEstudiante(new Estudiante( "", 0), vista);

        //Para el Id del Estudiante
        /*System.out.println("Ingrese el id del estudiante: ");
        int id = sc.nextInt();
        sc.nextLine();*/

        //Para el nombre del estudiante
        System.out.println("Ingrese el nombre del estudiante: ");
        String nombre = sc.nextLine();

        //Para la Edad del estudiante
        System.out.println("Ingrese la edad del estudiante: ");
        int edad = sc.nextInt();


        Estudiante estudiante = new Estudiante(nombre, edad);

        //Llamar metodos
        controlador.agregarEstudiante(estudiante);  
        controlador.actualizarVista();    
        
                            
        sc.close();
    }
}