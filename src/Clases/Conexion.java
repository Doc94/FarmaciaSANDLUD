/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author heltonsmith
 */
public class Conexion {
    
    public Connection con;
    PreparedStatement ps;
    public Conexion() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver"); //Driver class Conexion
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmaciasandlud?zeroDateTimeBehavior=convertToNull", "root", ""); //URL de Conexion
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void cerrar(){
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
    
    public boolean registrar(){
        
        int res = 0;
        
        try {
            Conexion c = new Conexion();
            
            String query = "insert into persona values (?,?,?) ";
            
            ps = c.getCon().prepareStatement(query);
            ps.setString(1, rut);
            ps.setString(2, nombre);
            ps.setString(3, apellido);
            
            
            res = ps.executeUpdate(); //insert-delete-update | select->executeQuery
        
        } catch (SQLException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
            
}
