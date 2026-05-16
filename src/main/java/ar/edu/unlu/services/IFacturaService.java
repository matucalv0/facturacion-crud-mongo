package ar.edu.unlu.services;

import ar.edu.unlu.models.Cliente;
import ar.edu.unlu.models.Detalle;
import ar.edu.unlu.models.Factura;

import java.util.List;

public interface IFacturaService {
    public boolean crearFactura(int numero, Cliente cliente, List<Detalle> detalles);
    public Factura obtenerFactura(int numero);
    public List<Factura> listarFacturas();
    public boolean eliminarFactura(int numero);

}
