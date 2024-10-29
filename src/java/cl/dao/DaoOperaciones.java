/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.dao;

import cl.modelo.Identificacion;
import cl.modelo.Resultado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author horac
 */
public class DaoOperaciones extends Conectar {
    
    public String consultaRegistro(String rut) {
        String rutEx = "";
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT Rut FROM Resultados Where Rut = '"+rut+"';";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //ejecutar la consulta.
            ResultSet res = ps.executeQuery();
            //Se recorre el ResultSet.
            while (res.next()) {
                rutEx = res.getString("Rut");
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoOperaciones.class.getName())
                    .log(Level.SEVERE, "Error en registro del Driver.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DaoOperaciones.class.getName())
                    .log(Level.SEVERE, "Error en SQL.", ex);
        }
        return rutEx;
    }
    
    public String consultaCodigo(String rut) {
        String codigo = "";
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT Codigo FROM Resultados Where Rut = '"+ rut +"';";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //ejecutar la consulta.
            ResultSet res = ps.executeQuery();
            //Se recorre el ResultSet.
            while (res.next()) {
                codigo = res.getString("Codigo");
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoOperaciones.class.getName())
                    .log(Level.SEVERE, "Error en registro del Driver.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DaoOperaciones.class.getName())
                    .log(Level.SEVERE, "Error en SQL.", ex);
        }
        return codigo;
    }
    
     public int consultaId(String rut) {
        int id = 0;
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT Id FROM Resultados WHERE Rut = '" + rut + "';";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //ejecutar la consulta.
            ResultSet res = ps.executeQuery();
            //Se recorre el ResultSet.
            while (res.next()) {
                id = res.getInt("Id");
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoOperaciones.class.getName())
                    .log(Level.SEVERE, "Error en registro del Driver.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DaoOperaciones.class.getName())
                    .log(Level.SEVERE, "Error en SQL.", ex);
        }
        return id;
    }
     
    public String consultaCorreoRegistrado(String rut) {
        String correo = "";
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT Correo FROM Resultados WHERE Rut = '"+rut+"';";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //ejecutar la consulta.
            ResultSet res = ps.executeQuery();
            //Se recorre el ResultSet.
            while (res.next()) {
                correo = res.getString("Correo");
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoOperaciones.class.getName())
                    .log(Level.SEVERE, "Error en registro del Driver.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DaoOperaciones.class.getName())
                    .log(Level.SEVERE, "Error en SQL.", ex);
        }
        return correo;
    }
    
    public int insertarCodigo(String rut, String codigo) {
        int cantFilas = 0;

        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "UPDATE Resultados SET Codigo = '" + codigo + "' WHERE Rut = '" + rut + "';";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            cantFilas = ps.executeUpdate();
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoOperaciones.class.getName()).log(Level.SEVERE, "Problema registro del Driver", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DaoOperaciones.class.getName()).log(Level.SEVERE, "Error SQL.", ex);
        }
        return cantFilas;
    }
    
    public Resultado obtenerResultado(String rut, String codigo) {
        Resultado resultado = new Resultado();
        
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT Puntaje, Nombre, Apellido_Pat, Apellido_Mat FROM Resultados WHERE Rut = '"+rut+"' AND Codigo = '"+codigo+"';";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //ejecutar la consulta.
            ResultSet res = ps.executeQuery();
            //Se recorre el ResultSet.
            while (res.next()) {
                resultado.setPuntaje(Integer.parseInt(res.getString("Puntaje")));
                resultado.setNombre(res.getString("Nombre"));
                resultado.setApellido_pat(res.getString("Apellido_Pat"));
                resultado.setApellido_mat(res.getString("Apellido_Mat"));
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoOperaciones.class.getName())
                    .log(Level.SEVERE, "Error en registro del Driver.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DaoOperaciones.class.getName())
                    .log(Level.SEVERE, "Error en SQL.", ex);
        }
        
        return resultado;
    }
    
    public Identificacion obtenerNombre(String rut) {
        Identificacion identificacion = new Identificacion();
        
        try {
            //Recuperar una conexión.
            Connection con = this.getConexion();
            //Se genera sentecia select
            String strSQL = "SELECT Nombre, Apellido_Pat, Apellido_Mat FROM Resultados WHERE Rut = '"+rut+"';";
            //Se prepara la consulta.
            PreparedStatement ps = con.prepareStatement(strSQL);
            //ejecutar la consulta.
            ResultSet res = ps.executeQuery();
            //Se recorre el ResultSet.
            while (res.next()) {
                identificacion.setNombre(res.getString("Nombre"));
                identificacion.setApellido_pat(res.getString("Apellido_Pat"));
                identificacion.setApellido_mat(res.getString("Apellido_Mat"));
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoOperaciones.class.getName())
                    .log(Level.SEVERE, "Error en registro del Driver.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DaoOperaciones.class.getName())
                    .log(Level.SEVERE, "Error en SQL.", ex);
        }
        
        return identificacion;
    }
    
}
