/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author David
 */
public class Producto {
    private String codigo;
    private String marca;
    private String componente;
    private String categoria;
    private int precio;
    private int stock;

    public Producto(String codigo, String marca, String componente, String categoria, int precio, int stock) {
        this.codigo = codigo;
        this.marca = marca;
        this.componente = componente;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getComponente() {
        return componente;
    }

    public void setComponente(String componente) {
        this.componente = componente;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    
    
}
