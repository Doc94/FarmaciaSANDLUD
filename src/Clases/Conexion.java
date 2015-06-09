/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import com.sun.deploy.uitoolkit.impl.fx.ui.MixedCodeInSwing;
import com.sun.webkit.ContextMenu;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author heltonsmith
 */
public class Conexion {
    
    public Connection con;
    PreparedStatement ps;
    PreparedStatement ps2;
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
        Producto pro = null;
        try {
            Conexion c = new Conexion();
            
            String query = "SELECT * FROM producto WHERE codigo=?   ";
            
            ps = c.getCon().prepareStatement(query);
            ps.setString(1, codigo);
            
            
            
            ResultSet r_query = ps.executeQuery(); //insert-delete-update | select->executeQuery
            
            while(r_query.next())
            {
            pro = new Producto();
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
    public Producto retorna_producto_n(String nombre) {
        Producto pro = null;
        try {
            Conexion c = new Conexion();
            
            String query = "SELECT * FROM producto WHERE nombre=?   ";
            
            ps = c.getCon().prepareStatement(query);
            ps.setString(1, nombre);
            
            
            
            ResultSet r_query = ps.executeQuery(); //insert-delete-update | select->executeQuery
            
            while(r_query.next())
            {
                pro = new Producto();
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
    public int crear_venta(Venta v)
    {
        int rsul=0;
        Conexion c = new Conexion();
            
            String query = "insert into ventas values(?,?,?,?)";
            try { 
            ps = c.getCon().prepareStatement(query);
            ps.setInt(1, v.getFolio());
            ps.setString(2, v.getFecha());
            ps.setString(3, v.getVendedor());
            ps.setInt(4, v.getTotal());
            
            
            rsul= ps.executeUpdate(); //insert-delete-update | select->executeQuery
            
            }
            catch (SQLException ex)
            {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
    
        return rsul;
    }
    
    public int crear_detalle(ArrayList<Producto> li,int folio)
    {
        int rsul=0;
        Conexion c = new Conexion();
        for(int i=0;i<li.size();i++)
        {   
            
            String query = "insert into detalle_venta  values (?,?,?)   ";
            try { 
                ps = c.getCon().prepareStatement(query);
                ps.setInt(1, folio);
                ps.setString(2, li.get(i).getCodigo());
                ps.setInt(3, li.get(i).getStock());
            
                rsul= ps.executeUpdate(); //insert-delete-update | select->executeQuery
            }
            catch (SQLException ex)
            {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         c.modifica_stock(li);
             
            
        return rsul;
            
    }
    public int retorna_stock (String codigo)
    {
        int stock=0;
        String query = "SELECT * FROM producto WHERE codigo=?   ";
        try 
        {
            Conexion c = new Conexion();
            
            ps = c.getCon().prepareStatement(query);
            ps.setString(1, codigo);
            
            
            
            ResultSet r_query = ps.executeQuery(); //insert-delete-update | select->executeQuery
            
            while(r_query.next())
            {
                stock=Integer.parseInt(String.valueOf(r_query.getObject("precio")));
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        return stock;
    }
   
    public int modifica_stock(ArrayList<Producto>li)
    {
     int rsul=0;
     int cant=0;
     int stock=0;
     int ST=0;
        Conexion c = new Conexion();
        for(int i=0;i<li.size();i++)
        {   
        stock=c.retorna_stock(li.get(i).getCodigo());
        cant=li.get(i).getStock();
        ST=stock-cant;
        try
        {
        String query = "update producto set stock=? where codigo=?   ";
        ps = c.getCon().prepareStatement(query);
        ps.setInt(1,ST);
        rsul= ps.executeUpdate();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
       
        } 
     return rsul;   
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
    
    public int getUltimoFolio() {
        int fol = -1;       
        try {
            Conexion c = new Conexion();
            
            String query = "SELECT * FROM ventas ORDER BY folio DESC";
            
            ps = c.getCon().prepareStatement(query);
          
            
            
            ResultSet r_query = ps.executeQuery(); //insert-delete-update | select->executeQuery
            
            if(r_query.next()) {
                fol = Integer.parseInt(String.valueOf(r_query.getObject("folio")));
            } else {
                fol = 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fol;
    }
}
