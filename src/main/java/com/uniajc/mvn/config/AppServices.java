package com.uniajc.mvn.config;

import com.uniajc.mvn.modelo.Estudiante;

public class AppServices {

    private static final Estudiante ESTUDIANTE = new Estudiante();

    public static Estudiante getEstudiante() {
        return ESTUDIANTE;
    }
}
