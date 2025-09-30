package com.uniajc.mvn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import com.uniajc.mvn.controlador.ControladorEstudiante;
import com.uniajc.mvn.modelo.ConexionBDD;
import com.uniajc.mvn.modelo.Estudiante;
import com.uniajc.mvn.vista.VistaEstudiante;

public class Main {
    public static void main(String[] args)  {

        Connection conexion = ConexionBDD.getConnection();

        VistaEstudiante vista = new VistaEstudiante();
        ControladorEstudiante controlador = new ControladorEstudiante(new Estudiante("", 0), vista);
        
        vista.menu(controlador);
          
     }
}