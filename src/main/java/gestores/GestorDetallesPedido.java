package gestores;

import exception.ServiceException;
import exception.ValidationException;
import model.DetallePedido;
import service.impl.DetallePedidoService;
import util.ConsoleUI;
import util.DatabaseConnection;
import util.Mensajes;
import util.TablaViewer;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Clase encargada de gestionar el menú y las operaciones relacionadas con los detalles de pedido dentro de la aplicación.
 */
public class GestorDetallesPedido {

    /**
     * Método principal que ejecuta el menú de gestión de detalles de pedido.
     * <p>
     * Mantiene un bucle hasta que el usuario decide salir. Gestiona la conexión
     * a base de datos y controla las excepciones producidas durante la ejecución.
     * </p>
     */
    public static void ejecutarMenuPedidoDetalles(){
        boolean seguir = true;

        try (Connection connection = DatabaseConnection.getConnection()) {
            DetallePedidoService detallePedidoService = new DetallePedidoService(connection);

            while (seguir) {
                try {

                    if (ejecutarOpcion(detallePedidoService) <= 0) { return; }
                    seguir = ConsoleUI.confirmarContinuacion("¿Desea seguir en la sección de pedidos? S/N: ", "Seguir Menu Pedidos");

                } catch (IllegalArgumentException | ServiceException | ValidationException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();

                } catch (NullPointerException ignored) {}
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Ejecuta la opción seleccionada por el usuario en el menú.
     *
     * @param detallePedidoService servicio que gestiona la lógica de detalles de pedido
     * @return número de opción seleccionada
     * @throws IllegalArgumentException si la opción está fuera de rango
     */
    private static int ejecutarOpcion(DetallePedidoService detallePedidoService){
        int opc = ConsoleUI.ingresarNumero(Mensajes.MENU_DETALLES_PEDIDO, "Menu Detalles Pedido");

        switch (opc) {
            case 1 -> agregarDetallePedido(detallePedidoService);
            case 2 -> listarDetallesPorPedido(detallePedidoService);
            case 3 -> obtenerDetalleConcreto(detallePedidoService);
            case 4 -> modificarCantidadProducto(detallePedidoService);
            case 5 -> eliminarDetalle(detallePedidoService);
            case 6 -> eliminarDetallesPorPedido(detallePedidoService);
            case 7 -> calcularTotalPedido(detallePedidoService);

            case 0, -1 -> {}
            default -> throw new IllegalArgumentException("Debe ingresar un número comprendido entre 0 y 7");
        }

        return opc;
    }

    /**
     * Solicita los datos necesarios al usuario e inserta un nuevo detalle de pedido.
     *
     * @param detallePedidoService servicio que gestiona la inserción del detalle
     */
    private static void agregarDetallePedido(DetallePedidoService detallePedidoService) {
        int idPedido = ConsoleUI.ingresarNumero("Ingrese el id del pedido: ", "Ingresar Detalle Pedido");
        if (idPedido == -1) { return; }

        int idProducto= ConsoleUI.ingresarNumero("Ingrese el id del producto: ", "Ingresar Detalle Pedido");
        if (idProducto == -1) { return; }

        int cantidad = ConsoleUI.ingresarNumero("Ingrese la cantidad: ", "Ingresar Detalle Pedido");
        if (cantidad == -1) { return; }

        detallePedidoService.insertarDetallePedido(idPedido, idProducto, cantidad);
        JOptionPane.showMessageDialog( null,  "Detalle Pedido ingresado con éxito",  "Ingresar Detalle Pedido",  JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Muestra en formato tabla todos los detalles asociados a un pedido concreto.
     *
     * @param detallePedidoService servicio que obtiene los detalles del pedido
     */
    private static void listarDetallesPorPedido(DetallePedidoService detallePedidoService) {
        int idPedido = ConsoleUI.ingresarNumero("Ingrese el id del pedido: ", "Ver Detalles Pedido");
        if (idPedido == -1) { return; }

        List<DetallePedido> detallesPedido = detallePedidoService.listarDetallesPedido(idPedido);

        String[] columnas = {"id_pedido", "Nombre", "Apellido", "Producto", "Cantidad", "Precio Unitario", "Fecha"};
        String [][] datosPedido = new String[detallesPedido.size()][columnas.length];

        for (int i = 0; i < detallesPedido.size(); i++) {

            String[] pedidoDatos = obtenerRegistro(detallesPedido, i);
            System.arraycopy(pedidoDatos, 0, datosPedido[i], 0, columnas.length);
        }

        TablaViewer.crearTabla(datosPedido, columnas, "Ver Detalles Pedido", 950, 120);
    }

    /**
     * Convierte un objeto {@link DetallePedido} en un array de Strings para su visualización.
     *
     * @param pedidos lista de detalles de pedido
     * @param i índice del detalle a convertir
     * @return array de Strings con los datos del detalle
     */
    private static String[] obtenerRegistro(List<DetallePedido> pedidos, int i) {
        DetallePedido detallePedido = pedidos.get(i);

        return new String[]{
                String.valueOf(detallePedido.getPedido().getId()),
                detallePedido.getPedido().getCliente().getNombre(),
                detallePedido.getPedido().getCliente().getApellido(),
                detallePedido.getProducto().getNombre(),
                String.valueOf(detallePedido.getCantidad()),
                String.valueOf(detallePedido.getPrecioUnitario()),
                String.valueOf(detallePedido.getPedido().getFecha())
        };
    }

    /**
     * Obtiene y muestra un detalle de pedido concreto a partir de su id de pedido y producto.
     *
     * @param detallePedidoService servicio que realiza la consulta
     */
    private static void obtenerDetalleConcreto(DetallePedidoService detallePedidoService) {
        int idPedido = ConsoleUI.ingresarNumero("Ingrese el id del pedido: ", "Ingresar Detalle Pedido");
        if (idPedido == -1) { return; }

        int idProducto= ConsoleUI.ingresarNumero("Ingrese el id del producto: ", "Ingresar Detalle Pedido");
        if (idProducto == -1) { return; }

        DetallePedido detallePedido = detallePedidoService.listarDetallePorId(idPedido, idProducto);

        String[] columnas = {"id_pedido", "Nombre", "Apellido", "id_producto" ,"Producto", "Cantidad", "Precio Unitario", "Fecha"};

        String [][] datosPedido = {
                {
                    String.valueOf(detallePedido.getPedido().getId()),
                    detallePedido.getPedido().getCliente().getNombre(),
                    detallePedido.getPedido().getCliente().getApellido(),
                    String.valueOf(detallePedido.getProducto().getId()),
                    detallePedido.getProducto().getNombre(),
                    String.valueOf(detallePedido.getCantidad()),
                    String.valueOf(detallePedido.getPrecioUnitario()),
                    String.valueOf(detallePedido.getPedido().getFecha())
                }
        };

        TablaViewer.crearTabla(datosPedido, columnas, "Ver Detalle Pedido", 1000, 80);
    }

    /**
     * Permite modificar la cantidad de un producto dentro de un detalle de pedido.
     *
     * @param detallePedidoService servicio encargado de la actualización
     */
    private static void modificarCantidadProducto(DetallePedidoService detallePedidoService) {

    }

    /**
     * Elimina un detalle de pedido específico.
     *
     * @param detallePedidoService servicio encargado de la eliminación
     */
    private static void eliminarDetalle(DetallePedidoService detallePedidoService) {

    }

    /**
     * Elimina todos los detalles asociados a un pedido concreto.
     *
     * @param detallePedidoService servicio encargado de la eliminación
     */
    private static void eliminarDetallesPorPedido(DetallePedidoService detallePedidoService) {

    }

    /**
     * Calcula y muestra el importe total de un pedido sumando todos sus detalles.
     *
     * @param detallePedidoService servicio encargado del cálculo
     */
    private static void calcularTotalPedido(DetallePedidoService detallePedidoService) {

    }
}