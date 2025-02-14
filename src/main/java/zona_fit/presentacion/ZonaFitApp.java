package zona_fit.presentacion;

import zona_fit.datos.ClienteDAO;
import zona_fit.datos.IClienteDAO;
import zona_fit.dominio.Cliente;

import java.util.Scanner;

public class ZonaFitApp extends ClienteDAO {
    public static void main(String[] args) {
        zonaFitApp();
    }

    private static void zonaFitApp() {
        boolean salir = false;
        Scanner sc = new Scanner(System.in);
        IClienteDAO clienteDao = new ClienteDAO();
        while (!salir){
            try {
                var opcion = mostrarMenu(sc);
                salir = ejecutarOpciones(sc, opcion, clienteDao);
            }catch (Exception e){
                System.out.println("Error al ejecutar opciones: " + e.getMessage());
            }
            System.out.println();
        }
    }

    private static int mostrarMenu(Scanner sc) {
        System.out.print("""
                *** ZONA FIT (GYM) ***
                1. Listar clientes
                2. Buscar cliente
                3. Agregar cliente
                4. Modificar cliente
                5. Eliminar Cliente
                6. Salir
                Elije una opción: \s""");
        return Integer.parseInt(sc.nextLine());
    }

    private static boolean ejecutarOpciones(Scanner sc, int opcion, IClienteDAO clienteDAO){
        var salir = false;
        switch (opcion){
            case 1 -> {//Listar cliente
                System.out.println("--- Listado de Clientes ---");
                var clientes = clienteDAO.listarClientes();
                clientes.forEach(System.out::println);
            }
            case 2 -> {//Buscar clientes
                System.out.print("Proporcione el numero de ID del cliente: ");
                var idCliente = Integer.parseInt(sc.nextLine());
                var cliente = new Cliente(idCliente);
                var encontrado = clienteDAO.buscarClientePorId(cliente);
                if (encontrado){
                    System.out.println("Cliente encontrado: " + cliente);
                }
                else{
                    System.out.println("Cliente no encontrado: " + cliente);
                }
            }
            case 3 -> {//Agregar cliente
                System.out.println("--- Agregar Cliente ---");
                System.out.print("Ingrese su nombre: ");
                String nombre = sc.nextLine();
                System.out.print("Ingrese su apellido: ");
                String apellido = sc.nextLine();
                System.out.print("Ingrese su membresia: ");
                int membresia = Integer.parseInt(sc.nextLine());
                var nuevoCliente = new Cliente(nombre, apellido, membresia);
                var agregado = clienteDAO.agregarCliente(nuevoCliente);
                if (agregado){
                    System.out.println("Cliente agregado: " + nuevoCliente);
                }
                else {
                    System.out.println("Cliente no agregado: " + nuevoCliente);
                }
            }
            case 4 -> {//Modificar cliente
                System.out.println("--- Modificar Cliente ---");
                System.out.print("Ingrese el ID del cliente a modificar: ");
                int id = Integer.parseInt(sc.nextLine());
                System.out.print("Ingrese su nombre: ");
                String nombre = sc.nextLine();
                System.out.print("Ingrese su apellido: ");
                String apellido = sc.nextLine();
                System.out.print("Ingrese su membresia: ");
                int membresia = Integer.parseInt(sc.nextLine());
                var modCliente = new Cliente(id, nombre, apellido, membresia);
                var modificado = clienteDAO.modificarCliente(modCliente);
                if (modificado){
                    System.out.println("Cliente modificado: " + modCliente);
                }
                else {
                    System.out.println("Cliente no modificado: " + modCliente);
                }
            }
            case 5 ->{//Eliminar cliente
                System.out.println("--- Eliminar Cliente ---");
                System.out.print("Ingrese el ID del cliente a eliminar: ");
                int id = Integer.parseInt(sc.nextLine());
                var eliminarCliente = new Cliente(id);
                var eliminado = clienteDAO.eliminarCliente(eliminarCliente);
                if (eliminado){
                    System.out.println("Cliente eliminado: " + eliminarCliente);
                }
                else {
                    System.out.println("Cliente no eliminado: " + eliminarCliente);
                }
            }
            case 6 -> {
                System.out.println("*** CERRANDO APLICACION, NOS VEMOS PRONTO! ***");
                return salir = true;
            }
            default -> System.out.println("Opcion no reconocida: " + opcion);
        }
        return salir;
    }
}