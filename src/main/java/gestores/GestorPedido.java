package gestores;

import exception.ServiceException;
import exception.ValidationException;
import model.Pedido;
import service.impl.ClienteService;
import service.impl.PedidoService;
import util.ConsoleUI;
import util.DatabaseConnection;
import util.Mensajes;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Gestor encargado de la interfaz de usuario para la gestión de pedidos.
 */
public class GestorPedido {

    /**
     * Ejecuta el menú principal de pedidos. Mantiene la conexión a base de datos durante toda la sesión
     * y gestiona la interacción con el usuario.
     */
    public static void ejecutarMenuPedido(){
        boolean seguir = true;

        try (Connection connection = DatabaseConnection.getConnection()) {
            ClienteService clienteService = new ClienteService(connection);
            PedidoService pedidoService = new PedidoService(connection, clienteService);


            while (seguir) {
                try {

                    if (ejecutarOpcion(pedidoService) <= 0) { return; }
                    seguir = ConsoleUI.confirmarContinuacion("¿Desea seguir en la sección de pedidos? S/N: ", "Seguir Menu Pedidos");

                } catch (IllegalArgumentException | ServiceException | ValidationException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

                } catch (NullPointerException ignored) {
                    seguir = ConsoleUI.confirmarContinuacion("¿Desea seguir en la sección de pedidos? S/N: ", "Seguir Menu Pedidos");
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Ejecuta la opción seleccionada por el usuario en el menú de pedidos.
     *
     * @param pedidoService servicio que contiene la lógica de pedidos
     * @return opción seleccionada por el usuario
     * @throws ServiceException si ocurre un error en la capa de servicio
     * @throws ValidationException si los datos no son válidos
     * @throws IllegalArgumentException si la opción no está en el rango permitido
     */
    private static int ejecutarOpcion(PedidoService pedidoService)  throws ServiceException, ValidationException {

        int opc = ConsoleUI.ingresarNumero(Mensajes.MENU_PEDIDOS, "Menu Pedidos");

        switch (opc) {
            case 1 -> crearPedido(pedidoService);
            case 2 -> buscarPedidoID(pedidoService);
            case 3 -> listarPedidos(pedidoService);
            case 4 -> listarPedidosPorCliente(pedidoService);
            case 5 -> menuModificarPedido(pedidoService);
            case 6 -> eliminarPedido(pedidoService);

            case 0, -1 -> {}
            default -> throw new IllegalArgumentException("Debe ingresar un número comprendido entre 0-6");
        }

        return opc;
    }
    /**
     * Crea un nuevo pedido asociado a un cliente.
     *
     * @param pedidoService servicio de pedidos
     */
    private static void crearPedido(PedidoService pedidoService) {
        int id_cliente = ConsoleUI.ingresarNumero("Ingrese el id del cliente para el pedido: ", "Ingresar Pedido");
        if (id_cliente == -1) { return; }

        pedidoService.crearPedido(id_cliente);

        JOptionPane.showMessageDialog( null,  "Pedido ingresado con éxito",  "Ingresar Pedido",  JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Busca un pedido por su ID y lo muestra por pantalla.
     *
     * @param pedidoService servicio de pedidos
     */
    private static void buscarPedidoID(PedidoService pedidoService) {
        int id_pedido = ConsoleUI.ingresarNumero("Ingrese el id del pedido: ", "Buscar Pedido Por ID");
        if (id_pedido == -1){ return;}

        Pedido pedido = pedidoService.buscarPedidoID(id_pedido);
        JOptionPane.showMessageDialog( null,  pedido,  "Ver Pedido",  JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Lista todos los pedidos registrados.
     *
     * @param pedidoService servicio de pedidos
     */
    private static void listarPedidos(PedidoService pedidoService) {
    }

    /**
     * Lista los pedidos filtrados por cliente.
     *
     * @param pedidoService servicio de pedidos
     */
    private static void listarPedidosPorCliente(PedidoService pedidoService) {
    }

    /**
     * Muestra el menú para modificar un pedido existente, fecha o id_cliente asociado.
     *
     * @param pedidoService servicio de pedidos
     */
    private static void menuModificarPedido(PedidoService pedidoService) {
    }

    /**
     * Elimina un pedido del sistema.
     *
     * @param pedidoService servicio de pedidos
     */
    private static void eliminarPedido(PedidoService pedidoService) {
    }
}