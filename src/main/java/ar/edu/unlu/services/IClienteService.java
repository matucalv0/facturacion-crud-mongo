package ar.edu.unlu.services;

import ar.edu.unlu.models.Cliente;
import org.bson.types.ObjectId;

import java.util.List;

public interface IClienteService {
    public boolean crearCliente(String nombre, String dni, String email);
    public List<Cliente> listarClientes();
    public Cliente obtenerClientePorDni(String dni);
    public boolean eliminarCliente(String dni);
    public boolean actualizarCliente(String dni, String nuevoDni, String nombre, String email);
}
