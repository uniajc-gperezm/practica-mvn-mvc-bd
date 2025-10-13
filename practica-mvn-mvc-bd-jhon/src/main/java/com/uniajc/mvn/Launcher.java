package com.uniajc.mvn;

/**
 * Clase Lanzadora necesaria para ejecutar correctamente la aplicación JavaFX 
 * sin encontrar el error de "runtime components are missing".
 */
public class Launcher {
    
    public static void main(String[] args) {
        // Delega la ejecución a la clase Main de JavaFX
        Main.main(args); 
    }
}