package ar.edu.unlu.view;

import ar.edu.unlu.models.Cliente;
import ar.edu.unlu.models.Detalle;
import ar.edu.unlu.models.Producto;
import ar.edu.unlu.services.IFacturaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FacturaView {
    private final IFacturaService facturaService;
    private final Scanner scanner = new Scanner(System.in);

    public FacturaView(IFacturaService facturaService) {
        this.facturaService = facturaService;
    }

    public void menu() {
        while (true) {
            System.out.println("\n--- FACTURAS ---");
            System.out.println("1. Crear factura");
            System.out.println("2. Consultar factura");
            System.out.println("3. Borrar factura");
            System.out.println("4. Listar facturas");
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
                        System.out.print("Ingrese numero de factura: ");
                        Integer numero = Integer.parseInt(scanner.nextLine());
                        System.out.println("\n--- DATOS CLIENTE ---");
                        System.out.print("Ingrese el nombre del cliente: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Ingrese el DNI del cliente: ");
                        String dni = scanner.nextLine();
                        System.out.print("Ingrese el email del cliente: ");
                        String email = scanner.nextLine();
                        Cliente cliente = new Cliente(nombre, dni, email);
                        List<Detalle> detalles = new ArrayList<>();

                        while (true) {
                            System.out.println("\n--- PRODUCTO ---");
                            System.out.print("Ingrese ID producto: ");
                            Integer idp = Integer.parseInt(scanner.nextLine());
                            System.out.print("Ingrese nombre producto: ");
                            String nombreProducto = scanner.nextLine();
                            System.out.print("Ingrese precio producto: ");
                            Double precio = Double.parseDouble(scanner.nextLine());
                            System.out.print("Ingrese stock producto: ");
                            Integer stock = Integer.parseInt(scanner.nextLine());

                            Producto producto = new Producto(idp, nombreProducto, precio, stock   //Agregar metodo auxiliar en FacturaService para crear el detalle?
                            );
                            System.out.print("Ingrese cantidad: ");
                            Integer cantidad = Integer.parseInt(scanner.nextLine());
                            Detalle detalle = new Detalle(producto, cantidad);
                            detalles.add(detalle);

                            System.out.print("Desea agregar otro producto? (s/n): ");
                            String respuesta = scanner.nextLine();
                            if (!respuesta.equalsIgnoreCase("s")) {
                                break;
                            }
                        }
                        if (facturaService.crearFactura(numero, cliente, detalles)) {
                            System.out.println("Factura creada");
                        } else {
                            System.out.println("No se pudo crear la factura");
                        }
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.print("Ingrese numero de factura: ");
                        Integer numero = Integer.parseInt(scanner.nextLine());

                        System.out.println(
                                facturaService.obtenerFactura(numero)
                        );
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.print("Ingrese numero de factura a borrar: ");
                        Integer numero = Integer.parseInt(scanner.nextLine());

                        if (facturaService.eliminarFactura(numero)) {
                            System.out.println("Factura borrada");
                        } else {
                            System.out.println("No se pudo borrar la factura");
                        }
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        System.out.println("Facturas:");
                        for (var factura : facturaService.listarFacturas()) {
                            System.out.println(factura);
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