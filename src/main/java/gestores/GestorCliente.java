package gestores;

import dao.impl.ClienteDAO;
import exception.ServiceException;
import service.impl.ClienteService;
import util.ConsoleUI;
import util.DatabaseConnection;
import util.Mensajes;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.SQLException;

public class GestorCliente {
    public static void ejecutarGestorClientes(){

        boolean seguir = true;

        try (Connection connection = DatabaseConnection.getConnection()) {
            ClienteService clientesService = new ClienteService(new ClienteDAO(connection));

            while (seguir) {
                try {

                    if (ejecutarOpcion(clientesService) == 6) { return; }
                    seguir = ConsoleUI.continuarGestorPedidos("¿Desea seguir en la sección de clientes? S/N: ", "Seguir Menu Clientes");

                } catch (IllegalArgumentException | ServiceException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static int ejecutarOpcion(ClienteService clientesService) throws ServiceException {
        int opc = ConsoleUI.ingresarNumero(Mensajes.MENSAJE_MENU_CLIENTE, "Menu Clientes");

        switch (opc){
            case 1 -> {
                System.out.println("Cliente ingresado");
            }

            case 2 -> System.out.println("Ver Cliente");
            case 3 -> System.out.println("Ver Clientes");

            case 4 -> {
                System.out.println("Cliente modificado");
            }
            case 5 -> {
                System.out.println("Cliente eliminado");
            }

            case 6 -> {}
            default -> throw new IllegalArgumentException("Debe ingresar un número comprendido entre 1 y 6");
        }

        return opc;
    }
}