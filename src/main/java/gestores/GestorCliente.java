package gestores;

import dao.impl.ClienteDAO;
import exception.ServiceException;
import exception.ValidationException;
import service.impl.ClienteService;
import util.ConsoleUI;
import util.DatabaseConnection;
import util.Mensajes;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Clase encargada de gestionar la interacción del usuario con el módulo de clientes.
 */
public class GestorCliente {

    /**
     * Inicia el gestor de clientes
     * Establece la conexión con la base de datos y ejecuta el menú en bucle hasta que el usuario decida salir.
     * Maneja excepciones tanto de acceso a datos {@SQLException} como de lógica de negocio {@ServiceException}.
     */
    public static void ejecutarGestorClientes(){

        boolean seguir = true;

        try (Connection connection = DatabaseConnection.getConnection()) {
            ClienteService clientesService = new ClienteService(new ClienteDAO(connection));

            while (seguir) {
                try {

                    if (ejecutarOpcion(clientesService) == 6) { return; }
                    seguir = ConsoleUI.confirmarContinuacion("¿Desea seguir en la sección de clientes? S/N: ", "Seguir Menu Clientes");

                } catch (IllegalArgumentException | ServiceException | ValidationException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Solicita al usuario una opción del menú de clientes y ejecuta la acción correspondiente.
     *
     * @param clientesService servicio de clientes utilizado para la lógica de negocio
     * @return número de la opción seleccionada
     * @throws IllegalArgumentException si la opción ingresada no está en el rango válido (1-6)
     * @throws ServiceException si ocurre un error en la capa de servicio
     */
    private static int ejecutarOpcion(ClienteService clientesService) throws ServiceException, ValidationException {
        int opc = ConsoleUI.ingresarNumero(Mensajes.MENU_CLIENTES, "Menu Clientes");

        switch (opc){
            case 1 -> {
                clientesService.insertarCliente(ConsoleUI.crearCliente());
                JOptionPane.showMessageDialog(null, "Nuevo cliente agregado con éxito", "Crear cliente", JOptionPane.INFORMATION_MESSAGE);
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