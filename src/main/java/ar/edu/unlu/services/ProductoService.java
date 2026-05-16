package ar.edu.unlu.services;

import ar.edu.unlu.models.Producto;
import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class ProductoService implements IProductoService{
    private final MongoCollection<Producto> productosCollection;
    public ProductoService(MongoCollection<Producto> productosCollection) {
        this.productosCollection = productosCollection;
    }

    @Override
    public boolean crearProducto(Integer idp, String nombre,Double precio, Integer stock) {
        try {
            Producto producto = new Producto(idp,nombre, precio, stock);
            productosCollection.insertOne(producto);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Producto> listarProducto() {
        try {
            return productosCollection.find().into(new ArrayList<>());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Producto obtenerProductoPorIDP(Integer idp) {
        return productosCollection.find(
                eq("idp", idp)
        ).first();
    }

    @Override
    public boolean eliminarProducto(Integer idp) {
        try {
            productosCollection.deleteOne(
                    eq("idp", idp)
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean actualizarProducto(Integer idp, Integer nuevoIdp, String nombre, Double precio, Integer stock) {
        try {
            if (idp == null) {
                return false;
            }
            if (nuevoIdp != null) {
                productosCollection.updateOne(
                        eq("idp", idp),
                        set("idp", nuevoIdp)
                );
            }
            if (nombre != null && !nombre.isEmpty()) {
                productosCollection.updateOne(
                        eq("idp", idp),
                        set("nombre", nombre)
                );
            }
            if (precio != null) {
                productosCollection.updateOne(
                        eq("idp", idp),
                        set("precio", precio)
                );
            }
            if (stock != null) {
                productosCollection.updateOne(
                        eq("idp", idp),
                        set("stock", stock)
                );
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
