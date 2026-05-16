package ar.edu.unlu.view;
import ar.edu.unlu.services.IProductoService;

import java.util.Scanner;

public class ProductoView {
    private final IProductoService productoService;
    private Scanner scanner = new Scanner(System.in);

    public ProductoView(IProductoService productoService) {
        this.productoService = productoService;
    }

    public void menu() {
        while (true) {
            System.out.println("\n--- PRODUCTOS ---");
            System.out.println("1. Crear producto");
            System.out.println("2. Consultar producto");
            System.out.println("3. Modificar producto");
            System.out.println("4. Borrar producto");
            System.out.println("5. Listar productos");
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
                        System.out.print("Ingrese el ID del producto: ");
                        Integer idp = Integer.parseInt(scanner.nextLine());

                        System.out.print("Ingrese el nombre del producto: ");
                        String nombre = scanner.nextLine();

                        System.out.print("Ingrese el precio del producto: ");
                        Double precio = Double.parseDouble(scanner.nextLine());

                        System.out.print("Ingrese el stock del producto: ");
                        Integer stock = Integer.parseInt(scanner.nextLine());

                        if (productoService.crearProducto(idp, nombre, precio, stock)) {
                            System.out.println("Producto creado");
                        } else {
                            System.out.println("No se pudo crear el producto");
                        }
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.print("Ingrese el ID del producto: ");
                        Integer idp = Integer.parseInt(scanner.nextLine());
                        System.out.println(
                                productoService.obtenerProductoPorIDP(idp)
                        );
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.print("Ingrese el ID del producto a modificar: ");
                        Integer idp = Integer.parseInt(scanner.nextLine());

                        System.out.print("Ingrese el nuevo ID del producto (Enter para no modificar): ");
                        String inputId = scanner.nextLine();

                        Integer nuevoIdp = inputId.isEmpty()
                                ? null
                                : Integer.parseInt(inputId);

                        System.out.print("Ingrese el nuevo nombre del producto (Enter para no modificar): ");
                        String nombre = scanner.nextLine();

                        if (nombre.isEmpty()) {
                            nombre = null;
                        }

                        System.out.print("Ingrese el nuevo precio del producto (Enter para no modificar): ");
                        String inputPrecio = scanner.nextLine();

                        Double precio = inputPrecio.isEmpty()
                                ? null
                                : Double.parseDouble(inputPrecio);
                        System.out.print("Ingrese el nuevo stock del producto (Enter para no modificar): ");
                        String inputStock = scanner.nextLine();

                        Integer stock = inputStock.isEmpty()
                                ? null
                                : Integer.parseInt(inputStock);
                        if (productoService.actualizarProducto(
                                idp,
                                nuevoIdp,
                                nombre,
                                precio,
                                stock
                        )) {
                            System.out.println("Producto actualizado");
                        } else {
                            System.out.println("No se pudo actualizar el producto");
                        }
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        System.out.print("Ingrese el ID del producto a borrar: ");
                        Integer idp = Integer.parseInt(scanner.nextLine());

                        if (productoService.eliminarProducto(idp)) {
                            System.out.println("Producto borrado");
                        } else {
                            System.out.println("No se pudo borrar el producto");
                        }

                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        System.out.println("Productos:");

                        for (var producto : productoService.listarProducto()) {
                            System.out.println(producto);
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
