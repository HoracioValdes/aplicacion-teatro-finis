/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.modelo;

/**
 *
 * @author horac
 */
public class Ingreso {
    private boolean valido;

    public Ingreso() {
    }

    public Ingreso(boolean valido) {
        this.valido = valido;
    }

    public boolean isValido() {
        return valido;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }

    @Override
    public String toString() {
        return "Ingreso{" + "valido=" + valido + '}';
    }
}
