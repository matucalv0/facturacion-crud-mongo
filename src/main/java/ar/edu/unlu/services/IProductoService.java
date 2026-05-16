package ar.edu.unlu.services;

import ar.edu.unlu.models.Producto;

import java.util.List;

public interface IProductoService {
    public boolean crearProducto(Integer idp,String nombre, Double precio,Integer stock);
    public List<Producto> listarProducto();
    public Producto obtenerProductoPorIDP(Integer idp);
    public boolean eliminarProducto(Integer idp);
    public boolean actualizarProducto(Integer idp,Integer nuevoIdp, String nombre, Double precio, Integer stock);
}
