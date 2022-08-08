package com.example.SpringLaptop.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name ="laptos")
@ApiModel("Entidad Laptos descripcion de los atributos de cada laptop")
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("Clave ficticia autoincremental tipo Long")
    private Long id;
    private String marca;
    private String modelo;
    @ApiModelProperty("precio en Euros de cada laptop")
    private double precio;

    public Laptop(){

    }
    public Laptop(Long id, String marca, String modelo, double precio) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
    }

    //getter y setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", precio=" + precio +
                '}';
    }
}