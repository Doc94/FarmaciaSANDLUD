/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            con = DriverManager.getConnection("jdbc:mysql://hercules.staxhosting.com:3306/doc_FarmaciaSANDLUD?zeroDateTimeBehavior=convertToNull", "doc_farmsand", "salco123"); //URL de Conexion
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
    
    public boolean existeuser(String rut, String pass){
        
        boolean existe = false;
        
        try {
            Conexion c = new Conexion();
            
            String query = "SELECT * FROM usuarios WHERE rut=?  and contraseña=? ";
            
            ps = c.getCon().prepareStatement(query);
            ps.setString(1, rut);
            ps.setString(2, pass);
            
            
            ResultSet r_query = ps.executeQuery(); //insert-delete-update | select->executeQuery
            existe = r_query.next();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return existe;
    }
    
    public Producto retorna_producto(String codigo) {
        Producto pro = new Producto();
        try {
            Conexion c = new Conexion();
            
            String query = "SELECT * FROM producto WHERE codigo=?   ";
            
            ps = c.getCon().prepareStatement(query);
            ps.setString(1, codigo);
            
            
            
            ResultSet r_query = ps.executeQuery(); //insert-delete-update | select->executeQuery
            
            while(r_query.next())
            {
            pro.setCodigo(String.valueOf(r_query.getObject("codigo")));
            pro.setNombre(String.valueOf(r_query.getObject("nombre")));
            pro.setComponente(String.valueOf(r_query.getObject("compuesto")));
            pro.setDescripcion(String.valueOf(r_query.getObject("descripción")));
            pro.setStock(Integer.parseInt(String.valueOf(r_query.getObject("stock"))));
            pro.setCategoria(String.valueOf(r_query.getObject("categoria")));
            pro.setPrecio(Integer.parseInt(String.valueOf(r_query.getObject("precio"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pro;
    }
    
    public Usuario retorna_usuario(String rut)
    {
        Usuario U = new Usuario();
        try {
            Conexion c = new Conexion();
            
            String query = "SELECT * FROM usuarios WHERE rut=?   ";
            
            ps = c.getCon().prepareStatement(query);
            ps.setString(1, rut);
            
            
            
            ResultSet r_query = ps.executeQuery(); //insert-delete-update | select->executeQuery
            
            while(r_query.next())
            {
            U.setNombre(String.valueOf(r_query.getObject("Nombre")));
            U.setRut(String.valueOf(r_query.getObject("rut")));
            U.setNivel(Integer.parseInt(String.valueOf(r_query.getObject("nivel"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return U;
     }
}
