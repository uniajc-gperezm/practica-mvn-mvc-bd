package com.uniajc.mvn.config;

import com.uniajc.mvn.modelo.Estudiante;

/**
 * Service Locator/Factoría que proporciona acceso a las instancias Singleton del Modelo.
 */
public class AppServices {

    // Instancia Singleton del Modelo Estudiante para toda la aplicación
    private static final Estudiante estudianteService = new Estudiante();

    public static Estudiante getEstudiante() {
        return estudianteService;
    }
}