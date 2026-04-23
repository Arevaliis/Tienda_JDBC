package gestores;

import exception.ServiceException;
import exception.ValidationException;
import model.Cliente;
import service.impl.ClienteService;
import util.*;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Clase encargada de gestionar la interacción del usuario con el módulo de clientes.
 */
public class GestorCliente {

    /**
     * Inicia el gestor de clientes
     * <p>
     * Establece la conexión con la base de datos y ejecuta el menú en bucle hasta que el usuario decida salir.
     * Maneja excepciones tanto de acceso a datos {@SQLException} como de lógica de negocio {@ServiceException}.
     * </p>
     */
    public static void ejecutarGestorClientes(){
        boolean seguir = true;

        try (Connection connection = DatabaseConnection.getConnection()) {
            ClienteService clientesService = new ClienteService(connection);

            while (seguir) {
                try {
                    if (ejecutarOpcion(connection ,clientesService) <= 0) { return; }
                    seguir = ConsoleUI.confirmarContinuacion("¿Desea seguir en la sección de clientes? S/N: ", "Seguir Menu Clientes");

                } catch (IllegalArgumentException | ServiceException | ValidationException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

                } catch (NullPointerException ignored) {
                    seguir = ConsoleUI.confirmarContinuacion("¿Desea seguir en la sección de clientes? S/N: ", "Seguir Menu Clientes");
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Muestra el menú de clientes, solicita una opción al usuario y ejecuta la acción correspondiente.
     *
     * @param clientesService servicio encargado de la lógica de negocio de clientes
     * @param connection connection base de datos
     *
     * @return opción seleccionada por el usuario
     *
     * @throws ServiceException si ocurre un error en la capa de servicio
     * @throws ValidationException si los datos introducidos no son válidos
     * @throws IllegalArgumentException si la opción no está dentro del rango permitido
     */
    private static int ejecutarOpcion(Connection connection, ClienteService clientesService)  throws ServiceException, ValidationException {

        int opc = ConsoleUI.ingresarNumero(Mensajes.MENU_CLIENTES, "Menu Clientes");

        switch (opc) {
            case 1 -> crearCliente(clientesService);
            case 2 -> buscarCliente(clientesService);
            case 3 -> listarClientes(clientesService);
            case 4 -> menuModificar(clientesService);
            case 5 -> eliminarCliente(clientesService);
            case 6 -> GestorEmail.menuEmail(connection, clientesService);
            case 7 -> exportarClientes(clientesService);

            case 0, -1 -> {}
            default -> throw new IllegalArgumentException("Debe ingresar un número comprendido entre 0-7");
        }

        return opc;
    }

    /**
     * Solicita los datos de un nuevo cliente y lo inserta en el sistema.
     *
     * @param clientesService servicio encargado de gestionar clientes
     * @throws ServiceException si ocurre un error al insertar el cliente
     * @throws ValidationException si los datos del cliente no son válidos
     */
    private static void crearCliente(ClienteService clientesService) throws ServiceException, ValidationException {
        String nombre = ConsoleUI.ingresarPalabra("Ingrese el nombre del cliente: ", "Crear Cliente");
        String apellido = ConsoleUI.ingresarPalabra("Ingrese el apellido del cliente: ", "Crear Cliente");

        clientesService.insertarCliente(nombre, apellido);
        JOptionPane.showMessageDialog( null,  "Nuevo cliente agregado con éxito",  "Crear cliente",  JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Busca un cliente por su identificador y muestra su información en pantalla.
     *
     * @param clientesService servicio encargado de gestionar clientes
     * @throws ServiceException si ocurre un error al buscar el cliente
     */
    private static void buscarCliente(ClienteService clientesService)  throws ServiceException {
        int id = ConsoleUI.ingresarNumero("Ingrese el id del cliente: ", "Buscar Cliente Por ID");

        Cliente cliente = clientesService.buscarClienteID( id );
        String[] columnas = {"id", "Nombre", "Apellido"};

        String [][] datosCliente = {
                { String.valueOf(cliente.getId()), cliente.getNombre(), cliente.getApellido(), cliente.getEmail()}
        };

        TablaViewer.crearTabla(datosCliente, columnas, "Ver Cliente");

    }

    /**
     * Muestra por pantalla la tabla con todos los clientes registrados en el sistema además muestra el email del cliente o null si no tiene.
     *
     * @param clientesService servicio encargado de gestionar clientes
     * @throws ServiceException sí ocurre un error al obtener los clientes
     */
    private static void listarClientes(ClienteService clientesService) throws ServiceException {
        List<Cliente> clientes = clientesService.listarClientes();

        String[] columnas = {"id", "Nombre", "Apellido", "email"};
        String [][] datosClientes = new String[clientes.size()][columnas.length];

        for (int i = 0; i < clientes.size(); i++) {
            Cliente cliente = clientes.get(i);
            String email = (cliente.getEmail() != null) ? cliente.getEmail() : "Sin registro";

            String[] clienteDatos = {String.valueOf(cliente.getId()), cliente.getNombre(), cliente.getApellido(), email};

            System.arraycopy(clienteDatos, 0, datosClientes[i], 0, columnas.length);
        }

        TablaViewer.crearTabla(datosClientes, columnas, "Ver Clientes");
    }

    /**
     * Elimina un cliente del sistema a partir de su identificador.
     *
     * @param clientesService servicio encargado de gestionar clientes
     * @throws ServiceException si ocurre un error al eliminar el cliente
     */
    private static void eliminarCliente(ClienteService clientesService)  throws ServiceException {
        int id = ConsoleUI.ingresarNumero("Ingrese el id del cliente: ", "Eliminar Cliente");

        clientesService.eliminarCliente( id );
        JOptionPane.showMessageDialog( null,  "Cliente eliminado con éxito",  "Eliminar Cliente",  JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Muestra el submenú de modificación de clientes y ejecuta la opción seleccionada.
     *
     * @param clientesService servicio encargado de gestionar clientes
     * @throws ValidationException si los datos introducidos no son válidos
     */
    private static void menuModificar(ClienteService clientesService) throws ValidationException {
        int opc = ConsoleUI.seleccionarOpcion( new String[]{"Nombre", "Apellido"},  "Modificar Cliente" ) + 1;

        switch (opc) {
            case 1 -> modificarNombreCliente(clientesService);
            case 2 -> modificarApellidoCliente(clientesService);
            default -> {}
        }
    }

    /**
     * Modifica el nombre de un cliente existente.
     *
     * @param clientesService servicio encargado de gestionar clientes
     * @throws ValidationException si los datos introducidos no son válidos
     */
    private static void modificarNombreCliente(ClienteService clientesService) throws ValidationException {
        int id = ConsoleUI.ingresarNumero("Ingrese el id del cliente: ", "Buscar Cliente");

        String nombre = ConsoleUI.ingresarPalabra("Ingrese el nuevo nombre del cliente; ", "Modificar Nombre");

        clientesService.modificarNombreCliente(nombre, id);
        JOptionPane.showMessageDialog( null,  "Cliente modificado con éxito",  "Modificar Cliente", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Modifica el apellido de un cliente existente.
     *
     * @param clientesService servicio encargado de gestionar clientes
     * @throws ValidationException si los datos introducidos no son válidos
     */
    private static void modificarApellidoCliente(ClienteService clientesService) throws ValidationException {
        int id = ConsoleUI.ingresarNumero("Ingrese el id del cliente: ", "Buscar Cliente");

        String apellido = ConsoleUI.ingresarPalabra("Ingrese el nuevo apellido del cliente; ", "Modificar Apellido");

        clientesService.modificarApellidoCliente(apellido, id);
        JOptionPane.showMessageDialog( null,  "Cliente modificado con éxito",  "Modificar Cliente",  JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Exporta la lista de clientes a un archivo JSON.
     *
     * @param clientesService servicio encargado de obtener la lista de clientes
     */
    private static void exportarClientes( ClienteService clientesService ) {
        List<Cliente> clientes = clientesService.listarClientes();

        ExportardorJSON<Cliente> exportador = new ExportardorJSON<>();
        exportador.exportarJSON(clientes, "clientes.json");

        JOptionPane.showMessageDialog( null,  "Clientes exportados con éxito",  "Exportar Clientes",  JOptionPane.INFORMATION_MESSAGE);
    }
}