package com.uniajc.mvn.config;

import java.util.ArrayList;
import com.uniajc.mvn.modelo.Estudiante;

public class AppServices {

    private static ArrayList<Estudiante> estudiantes;

    static  {
        estudiantes = Estudiante.obtenerEstudiantes();
    }

    public static ArrayList<Estudiante> getEstudiantes() {
        return estudiantes = Estudiante.obtenerEstudiantes();
    }
}
