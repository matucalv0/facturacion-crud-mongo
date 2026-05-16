package ar.edu.unlu.view;

import ar.edu.unlu.services.ClienteService;
import ar.edu.unlu.services.IClienteService;
import ar.edu.unlu.services.IFacturaService;
import ar.edu.unlu.services.IProductoService;

import java.util.Scanner;

public class MongoApp {
    private final IClienteService clienteService;
    private final ClienteView clienteView;
    private final IProductoService productoService;
    private final ProductoView productoView;
    private final IFacturaService facturaService;
    private final FacturaView facturaView;

    public MongoApp(IClienteService clienteService, IProductoService productoService, IFacturaService facturaService) {
        this.clienteService = clienteService;
        this.clienteView = new ClienteView(clienteService);
        this.productoService= productoService;
        this.productoView = new ProductoView(productoService);
        this.facturaService= facturaService;
        this.facturaView=new FacturaView(facturaService);
    }
    public void run() {
        int opcion;

        while (true) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Clientes");
            System.out.println("2. Productos");
            System.out.println("3. Facturas");
            System.out.println("0. Salir");

            try {
                opcion = Integer.parseInt(new Scanner(System.in).nextLine());
            } catch (Exception e) {
                System.out.println("Ingrese un numero valido");
                continue;
            }

            switch (opcion) {
                case 1:
                    clienteView.menu();
                    break;
                case 2:
                    productoView.menu();
                    break;
                case 3:
                    facturaView.menu();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
            }
        }





    }
}
