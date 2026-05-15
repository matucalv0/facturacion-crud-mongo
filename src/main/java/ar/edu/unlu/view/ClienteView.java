package ar.edu.unlu.view;

import ar.edu.unlu.services.ClienteService;
import ar.edu.unlu.services.IClienteService;

import java.util.Scanner;

public class ClienteView {

    private final IClienteService clienteService;
    private Scanner scanner = new Scanner(System.in);

    public ClienteView(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public void menu() {

        while (true) {

            System.out.println("\n--- CLIENTES ---");
            System.out.println("1. Crear cliente");
            System.out.println("2. Consultar cliente");
            System.out.println("3. Modificar cliente");
            System.out.println("4. Borrar cliente");
            System.out.println("5. Listar clientes");
            System.out.println("0. Volver al menú principal");

            int opcion;

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Ingrese un numero valido");
                continue;
            }

            switch (opcion) {

                case 1:
                    try {
                        System.out.print("Ingrese el nombre del cliente: ");
                        String nombre = scanner.nextLine();

                        System.out.print("Ingrese el DNI del cliente: ");
                        String dni = scanner.nextLine();

                        System.out.println("Ingrese el mail del cliente: ");
                        String email = scanner.nextLine();

                        if (clienteService.crearCliente(nombre, dni, email)) {
                            System.out.println("Cliente creado");
                        } else {
                            System.out.println("No se pudo crear el cliente");
                        }
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.print("Ingrese el DNI del cliente: ");
                        String dni = scanner.nextLine();
                        System.out.println(clienteService.obtenerClientePorDni(dni));
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.print("Ingrese el DNI del cliente a modificar: ");
                        String dni = scanner.nextLine();
                        System.out.print("Ingrese el nuevo dni del cliente: (Enter para no modificar) ");
                        String nuevoDni = scanner.nextLine();
                        System.out.print("Ingrese el nuevo nombre del cliente:  (Enter para no modificar) ");
                        String nuevoNombre = scanner.nextLine();
                        System.out.print("Ingrese el nuevo mail del cliente:  (Enter para no modificar) ");
                        String email = scanner.nextLine();

                        if (clienteService.actualizarCliente(dni, nuevoDni, nuevoNombre, email)) {
                            System.out.println("Cliente actualizado");
                        } else {
                            System.out.println("No se pudo actualizar el cliente");
                        }

                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        System.out.print("Ingrese el DNI del cliente a borrar: ");
                        String dni = scanner.nextLine();
                        clienteService.eliminarCliente(dni);
                        System.out.println("Cliente borrado");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        System.out.println("Clientes:");
                        for (var cliente : clienteService.listarClientes()) {
                            System.out.println(cliente);
                        }
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opcion invalida");
            }
        }
    }
}
