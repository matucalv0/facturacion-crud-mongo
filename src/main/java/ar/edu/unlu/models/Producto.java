package ar.edu.unlu.models;

import org.bson.types.ObjectId;

public class Producto {
    private ObjectId id;
    private Integer idp;
    private String nombre;
    private Double precio;
    private Integer stock;

    public Producto() {
    }

    public Producto(Integer idp,String nombre, Double precio, Integer stock) {
        this.idp=idp;
        this.nombre = nombre;
        this.precio = precio;
        this.stock=stock;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                '}';
    }
}
