
package com.uniajc.mvn.modelo;




public class Docente {
    private int id;
    private String nombre;
    private String departamento; 

    public Docente() {}
    public Docente(int id, String nombre, String departamento) {
        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }
    
    @Override
    public String toString() {
        return "Docente [id=" + id + ", nombre=" + nombre + ", departamento=" + departamento + "]";
    }
}