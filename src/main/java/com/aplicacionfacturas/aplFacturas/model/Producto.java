package com.aplicacionfacturas.aplFacturas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Factura
 */

@Entity
@Table(name = "producto")
public class Producto {


    @Id // pk no nula
    @GeneratedValue(strategy = GenerationType.AUTO) // auto increment
    private Long id;

    @NotNull //no nulo
    private String descripcion;

    @NotNull
    private String fabricante;

    @NotNull
    private Float precio;

 

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "id=" + id + "Factura [descripcion=" + descripcion + ", fabricante=" + fabricante +", precio="+ precio + "]";
    }

  
    public Producto(@NotNull String descripcion, @NotNull String fabricante, @NotNull Float precio) {
        this.descripcion = descripcion;
        this.fabricante = fabricante;
        this.precio = precio;
      
    }

    public Producto() {
    }
}