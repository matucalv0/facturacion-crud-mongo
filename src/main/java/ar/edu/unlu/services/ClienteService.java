package ar.edu.unlu.services;

import ar.edu.unlu.models.Cliente;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class ClienteService implements IClienteService {
    private final MongoCollection<Cliente> clientesCollection;

    public ClienteService(MongoCollection<Cliente> clientesCollection) {
        this.clientesCollection = clientesCollection;
    }

    @Override
    public boolean crearCliente(String nombre, String dni, String email) {
        try {
            Cliente cliente = new Cliente(nombre, dni, email);
            clientesCollection.insertOne(cliente);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Cliente> listarClientes() {
        try {
            List<Cliente> clientes = new ArrayList<>();
            for (Cliente cliente : clientesCollection.find()) {
                clientes.add(cliente);
            }
            return clientes;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Cliente obtenerClientePorDni(String dni) {
        return clientesCollection.find(eq("dni", dni)).first();
    }

    @Override
    public boolean eliminarCliente(String dni) {
        try {
            clientesCollection.deleteOne(eq("dni", dni));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean actualizarCliente(String dni, String nuevoDni, String nombre, String email) {
        try {
            if (dni == null || dni.isEmpty()) {
                return false;
            }
            if (nombre != null && !nombre.isEmpty()) {
                clientesCollection.updateOne(eq("dni", dni), set("nombre", nombre));
            }
            if (email != null && !email.isEmpty()) {
                clientesCollection.updateOne(eq("dni", dni), set("email", email));
            }
            if (nuevoDni != null && !nuevoDni.isEmpty()) {
                clientesCollection.updateOne(eq("dni", dni), set("dni", nuevoDni));
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
