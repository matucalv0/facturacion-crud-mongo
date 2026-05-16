package ar.edu.unlu.models;

import org.bson.types.ObjectId;

import java.util.List;

public class Factura {
    private ObjectId id;
    private int numero;
    private Cliente cliente;
    private List<Detalle> detalles;
    private double total;

    public Factura() {
    }

    public Factura(int numero, Cliente cliente, List<Detalle> detalles) {
        this.numero=numero;
        this.cliente = cliente;
        this.detalles = detalles;
        this.total = detalles.stream().mapToDouble(Detalle::getSubtotal).sum();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Detalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<Detalle> detalles) {
        this.detalles = detalles;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", detalles=" + detalles +
                ", total=" + total +
                '}';
    }
}
