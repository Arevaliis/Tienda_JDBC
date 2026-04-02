package gestores;

import exception.ServiceException;
import exception.ValidationException;
import model.Cliente;
import model.Email;
import service.impl.ClienteService;
import util.ConsoleUI;
import util.DatabaseConnection;
import util.Mensajes;

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
     * Establece la conexión con la base de datos y ejecuta el menú en bucle hasta que el usuario decida salir.
     * Maneja excepciones tanto de acceso a datos {@SQLException} como de lógica de negocio {@ServiceException}.
     */
    public static void ejecutarGestorClientes(){

        boolean seguir = true;

        try (Connection connection = DatabaseConnection.getConnection()) {
            ClienteService clientesService = new ClienteService(connection);

            while (seguir) {
                try {

                    if (ejecutarOpcion(clientesService) == 0) { return; }
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
     * Muestra el menú de clientes, solicita una opción al usuario y ejecuta la acción correspondiente.
     *
     * @param clientesService servicio encargado de la lógica de negocio de clientes
     * @return opción seleccionada por el usuario
     *
     * @throws ServiceException si ocurre un error en la capa de servicio
     * @throws ValidationException si los datos introducidos no son válidos
     * @throws IllegalArgumentException si la opción no está dentro del rango permitido
     */
    private static int ejecutarOpcion(ClienteService clientesService)  throws ServiceException, ValidationException {

        int opc = ConsoleUI.ingresarNumero(Mensajes.MENU_CLIENTES, "Menu Clientes");

        switch (opc) {
            case 1 -> crearCliente(clientesService);
            case 2 -> buscarCliente(clientesService);
            case 3 -> listarClientes(clientesService);
            case 4 -> menuModificar(clientesService);
            case 5 -> eliminarCliente(clientesService);
            case 6 -> menuEmail(clientesService);

            case 0 -> {}
            default -> throw new IllegalArgumentException("Debe ingresar un número comprendido entre 0-6");
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

        clientesService.insertarCliente(ConsoleUI.crearCliente());
        JOptionPane.showMessageDialog( null,  "Nuevo cliente agregado con éxito",  "Crear cliente",  JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Busca un cliente por su identificador y muestra su información en pantalla.
     *
     * @param clientesService servicio encargado de gestionar clientes
     * @throws ServiceException si ocurre un error al buscar el cliente
     */
    private static void buscarCliente(ClienteService clientesService)  throws ServiceException {

        Cliente cliente = clientesService.buscarClienteID( ConsoleUI.ingresarNumero("Ingrese el id del cliente: ", "Buscar Cliente Por ID"));
        JOptionPane.showMessageDialog( null,  cliente,  "Ver Cliente",  JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Muestra por pantalla la lista de todos los clientes registrados en el sistema.
     *
     * @param clientesService servicio encargado de gestionar clientes
     * @throws ServiceException si ocurre un error al obtener los clientes
     */
    private static void listarClientes(ClienteService clientesService) throws ServiceException {

        List<Cliente> clientes = clientesService.mostrarTodosClientes();
        List<String> mensaje = clientes.stream()
                                        .map(Object::toString)
                                        .toList();

        JOptionPane.showMessageDialog( null,  String.join("\n", mensaje),  "Clientes Registrados",  JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Elimina un cliente del sistema a partir de su identificador.
     *
     * @param clientesService servicio encargado de gestionar clientes
     * @throws ServiceException si ocurre un error al eliminar el cliente
     */
    private static void eliminarCliente(ClienteService clientesService)  throws ServiceException {

        clientesService.eliminarCliente( ConsoleUI.ingresarNumero("Ingrese el id del cliente: ", "Eliminar Cliente") );
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
            default -> { }
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
     * Muestra el menú de opciones relacionadas con la gestión de emails
     * y ejecuta la acción correspondiente según la opción elegida.
     *
     * @param clientesService servicio de clientes utilizado para realizar las operaciones
     * @throws ValidationException si ocurre un error en la validación de los datos introducidos
     */
    private static void menuEmail(ClienteService clientesService) throws ValidationException {
        int opc = ConsoleUI.ingresarNumero(Mensajes.MENU_EMAIL, "Menu Email");

        switch (opc) {
            case 1 -> agregarEmail(clientesService);
            case 2 -> modificarEmail(clientesService);
            case 3 -> cambiarIdClienteEmail(clientesService);
            case 4 -> verEmailPorCliente(clientesService);
            case 5 -> eliminarEmail(clientesService);

            case 0 -> {}
            default -> throw new IllegalArgumentException("Debe ingresar un número comprendido entre 0 y 5");
        }
    }

    /**
     * Solicita los datos necesarios y agrega un nuevo email a un cliente.
     *
     * @param clientesService servicio de clientes utilizado para realizar la operación
     * @throws ValidationException si el email introducido no es válido
     */
    private static void agregarEmail(ClienteService clientesService) throws ValidationException {
        String email = ConsoleUI.ingresarEmail();
        int idCliente = ConsoleUI.ingresarNumero("Ingrese el id del cliente: ", "Ingresar Email");

        clientesService.agregarEmail(email, idCliente);
        JOptionPane.showMessageDialog( null,  "Email agregado con éxito",  "Ingresar Email",  JOptionPane.INFORMATION_MESSAGE);

    }

    /**
     * Solicita los datos necesarios y modifica un email existente.
     *
     * @param clientesService servicio de clientes utilizado para realizar la operación
     * @throws ValidationException si el email introducido no es válido
     */
    private static void modificarEmail(ClienteService clientesService) throws ValidationException {
        String email = ConsoleUI.ingresarEmail();
        int id = ConsoleUI.ingresarNumero("Ingrese el id del email: ", "Modificar Email");

        clientesService.modificarEmail(email, id);
        JOptionPane.showMessageDialog( null,  "Email modificado con éxito",  "Modificar Email",  JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Cambia el cliente asociado a un email existente.
     *
     * @param clientesService servicio de clientes utilizado para realizar la operación
     */
    private static void cambiarIdClienteEmail(ClienteService clientesService) {
        int idCliente = ConsoleUI.ingresarNumero("Ingrese el id del cliente: ", "Modificar Email");
        int idEmail = ConsoleUI.ingresarNumero("Ingrese el id del email: ", "Modificar Email");

        clientesService.cambiarIdClienteEmail(idCliente, idEmail);
        JOptionPane.showMessageDialog( null,  "Cliente email modificado con éxito",  "Modificar Email",  JOptionPane.INFORMATION_MESSAGE);

    }

    /**
     * Muestra todos los emails asociados a un cliente específico.
     *
     * @param clientesService servicio de clientes utilizado para realizar la consulta
     */
    private static void verEmailPorCliente(ClienteService clientesService) {
        List<Email> emails = clientesService.verEmailsPorCliente(ConsoleUI.ingresarNumero("Ingrese el id del cliente: ", "Ver Emails Cliente"));
        List<String> mensaje = emails.stream()
                                    .map(Email::toString)
                                    .toList();

        JOptionPane.showMessageDialog( null,  String.join("\n", mensaje),  "Emails Cliente",  JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Elimina un email de la base de datos a partir de su identificador.
     *
     * @param clientesService servicio de clientes utilizado para realizar la operación
     */
    private static void eliminarEmail(ClienteService clientesService) {
        int id = ConsoleUI.ingresarNumero("Ingrese el id del email: ", "Modificar Email");
        clientesService.eliminarEmail(id);

        JOptionPane.showMessageDialog( null,  "Email eliminado con éxito",  "Eliminar Email",  JOptionPane.INFORMATION_MESSAGE);
    }
}