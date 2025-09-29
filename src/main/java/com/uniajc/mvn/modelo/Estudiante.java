package com.uniajc.mvn.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Estudiante {
    private int id;
    private String nombre;
    private int edad;

    public Estudiante( int id, String nombre, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
    }

    public int getId (){
        return this.id;
    }
    
    public void setId(int id){
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return this.edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    //Metodo para crear y guardar el Estudiante a la base de datos
    public static void insertarEstudiante(Estudiante estudiante) {
    String sql = "INSERT INTO estudiante (Id, nombre, edad) VALUES (?,?,?)";
    try (Connection conn = ConexionBDD.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, estudiante.getId());
        stmt.setString(2, estudiante.getNombre());
        stmt.setInt(3, estudiante.getEdad());
        stmt.executeUpdate();
        

        } catch (Exception ex) {
        ex.printStackTrace();
        }
    }

    
}