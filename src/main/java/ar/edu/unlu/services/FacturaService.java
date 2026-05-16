package ar.edu.unlu.services;

import ar.edu.unlu.models.Cliente;
import ar.edu.unlu.models.Detalle;
import ar.edu.unlu.models.Factura;
import com.mongodb.client.MongoCollection;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class FacturaService implements IFacturaService {
    private final MongoCollection<Factura> facturasCollection;
    public FacturaService(MongoCollection<Factura> facturasCollection) {
        this.facturasCollection = facturasCollection;
    }

    @Override
    public boolean crearFactura(int numero, Cliente cliente, List<Detalle> detalles) {
        try {
            double total = 0;
            for (Detalle detalle : detalles) {
                total += detalle.getSubtotal();
            }
            Factura factura = new Factura(numero, cliente, detalles);
            facturasCollection.insertOne(factura);
            return true;
        } catch (Exception e) {
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
