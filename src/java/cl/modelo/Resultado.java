/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.modelo;

/**
 *
 * @author horac
 */
public class Resultado {
    private int puntaje;
    private String nombre;
    private String apellido_pat;
    private String apellido_mat;

    public Resultado() {
    }

    public Resultado(int puntaje, String nombre, String apellido_pat, String apellido_mat) {
        this.puntaje = puntaje;
        this.nombre = nombre;
        this.apellido_pat = apellido_pat;
        this.apellido_mat = apellido_mat;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_pat() {
        return apellido_pat;
    }

    public void setApellido_pat(String apellido_pat) {
        this.apellido_pat = apellido_pat;
    }

    public String getApellido_mat() {
        return apellido_mat;
    }

    public void setApellido_mat(String apellido_mat) {
        this.apellido_mat = apellido_mat;
    }

    @Override
    public String toString() {
        return "Resultado{" + "puntaje=" + puntaje + ", nombre=" + nombre + ", apellido_pat=" + apellido_pat + ", apellido_mat=" + apellido_mat + '}';
    }
}
