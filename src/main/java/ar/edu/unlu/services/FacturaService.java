package ar.edu.unlu.services;

import ar.edu.unlu.models.Cliente;
import ar.edu.unlu.models.Detalle;
import ar.edu.unlu.models.Factura;
import ar.edu.unlu.models.Producto;
import com.mongodb.client.MongoCollection;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class FacturaService implements IFacturaService {
    private final MongoCollection<Factura> facturasCollection;
    private final MongoCollection<Producto> productosCollection;
    private final MongoCollection<Cliente> clientesCollection;
    public FacturaService(MongoCollection<Factura> facturasCollection, MongoCollection<Producto> productosCollection, MongoCollection<Cliente> clientesCollection) {
        this.facturasCollection = facturasCollection;
        this.productosCollection = productosCollection;
        this.clientesCollection = clientesCollection;
    }

    @Override
    public boolean crearFactura(int numero, Cliente cliente, List<Detalle> detalles) {
        try {
            double total = 0;

            if (clientesCollection.find(eq("dni", cliente.getDni())).first() == null) {
                throw new IllegalArgumentException("El cliente con DNI " + cliente.getDni() + " no existe");
            }

            for (Detalle detalle : detalles) {
                if (detalle.getCantidad() <= 0 || detalle.getProducto().getPrecio() < 0) {
                    throw new IllegalArgumentException("Cantidad y precio deben ser positivos");
                }
                if (detalle.getCantidad() > detalle.getProducto().getStock()) {
                    throw new IllegalArgumentException("No hay suficiente stock para el producto: " + detalle.getProducto().getNombre());
                }

                if (productosCollection.find(eq("idp", detalle.getProducto().getIdp())).first() == null) {
                    throw new IllegalArgumentException("El producto con ID " + detalle.getProducto().getIdp() + " no existe");
                }

                total += detalle.getSubtotal();
            }
            Factura factura = new Factura(numero, cliente, detalles);
            facturasCollection.insertOne(factura);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Factura obtenerFactura(int numero) {
        try {
            return facturasCollection.find(eq("numero", numero)).first();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Factura> listarFacturas() {
        List<Factura> facturas = new ArrayList<>();
        try {
            facturasCollection.find().into(facturas);
        } catch (Exception e) {
            return new ArrayList<>();
        }
        return facturas;
    }

    @Override
    public boolean eliminarFactura(int numero) {
        try {
            facturasCollection.deleteOne(eq("numero", numero));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
