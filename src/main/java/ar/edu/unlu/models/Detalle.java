package ar.edu.unlu.models;

import org.bson.types.ObjectId;

public class Detalle {
    private ObjectId id;
    private Producto producto;
    private int cantidad;
    private double subtotal;

    public Detalle() {
    }

    public Detalle(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = this.cantidad * this.producto.getPrecio();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subTotal) {
        this.subtotal = subTotal;
    }

    @Override
    public String toString() {
        return "Detalle{" +
                "id=" + id +
                ", producto=" + producto +
                ", cantidad=" + cantidad +
                ", subtotal=" + subtotal +
                '}';
    }
}
