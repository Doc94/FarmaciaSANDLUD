/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;

/**
 *
 * @author Administrador
 */
public class Venta {
    private int folio;
    private String fecha;
    private String vendedor;
    private int total;
    public ArrayList <Producto>detalle=new ArrayList();

    public Venta() {
    }

    public Venta(int folio, String fecha, String vendedor, int total) {
        this.folio = folio;
        this.fecha = fecha;
        this.vendedor = vendedor;
        this.total = total;
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    
}
