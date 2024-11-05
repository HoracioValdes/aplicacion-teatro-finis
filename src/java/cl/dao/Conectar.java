/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author horac
 */
public class Conectar {
    
    public static Connection getConexion() 
            throws ClassNotFoundException, SQLException{
        //Registro del Driver.
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Retornar al conexión
        //Conexión en productivo
        //return DriverManager.getConnection("jdbc:mysql://192.168.5.128:3306/resultados_paes?useSSL=false&useTimezone=true&serverTimezone=UTC","paes","nNbeIN.7bWaMY");
        //Conexión en localhost
        //return DriverManager.getConnection("jdbc:mysql://localhost:3306/resultados_teatro?useSSL=false&useTimezone=true&serverTimezone=UTC","root","admin");
        //Conexión en ambiente de desarrollo
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/resultados_teatro?useSSL=false&useTimezone=true&serverTimezone=UTC","root","WebUft.,2015");
    }
    
    public void Desconectar(Connection cn){
        try{
            cn.close();
        }catch(Exception error){ 
        }
    }
}
