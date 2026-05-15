package ar.edu.unlu.view;

import ar.edu.unlu.services.ClienteService;
import ar.edu.unlu.services.IClienteService;

import java.util.Scanner;

public class MongoApp {
    private final IClienteService clienteService;
    private final ClienteView clienteView;


    public MongoApp(IClienteService clienteService) {
        this.clienteService = clienteService;
        this.clienteView = new ClienteView(clienteService);
    }
    public void run() {
        int opcion;

        while (true) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Clientes");
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
                case 0:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
            }
        }





    }
}
