package com.uniajc.mvn.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class Estudiante {
    //private int id;
    private String nombre;
    private int edad;

    public Estudiante( String nombre, int edad) {
        //this.id = id;
        this.nombre = nombre;
        this.edad = edad;
    }

    /*public int getId (){
        return this.id;
    }
    
    public void setId(int id){
        this.id = id;
    }*/

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
    String sql = "INSERT INTO estudiante (nombre, edad) VALUES (?,?)";
    try (Connection conn = ConexionBDD.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        //stmt.setInt(1, estudiante.getId());
        stmt.setString(1, estudiante.getNombre());
        stmt.setInt(2, estudiante.getEdad());
        stmt.executeUpdate();
        

        } catch (Exception ex) {
        ex.printStackTrace();
        }
    }

    

    
}