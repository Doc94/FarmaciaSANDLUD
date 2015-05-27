/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Administrador
 */
public class Usuario {
    private String Rut;
    private String Nombre;
    private int nivel;

    public Usuario() {
    }

    public Usuario(String Rut, String Nombre, int nivel) {
        this.Rut = Rut;
        this.Nombre = Nombre;
        this.nivel = nivel;
    }

    public String getRut() {
        return Rut;
    }

    public void setRut(String Rut) {
        this.Rut = Rut;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
}
