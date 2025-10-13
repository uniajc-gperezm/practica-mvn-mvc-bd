package com.uniajc.mvn.services; // ⬅️ DEBE ESTAR EN EL MISMO PAQUETE

public class Launcher {
    
    public static void main(String[] args) {
        // La clase Main también DEBE estar en este paquete
        com.uniajc.mvn.Main.main(args); // ⬅️ Usa el nombre completo para evitar ambigüedades
    }
}