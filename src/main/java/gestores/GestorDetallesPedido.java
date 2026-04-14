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

public class GestorDetallesPedido {

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

    private static void obtenerDetalleConcreto(DetallePedidoService detallePedidoService) {
        int idPedido = ConsoleUI.ingresarNumero("Ingrese el id del pedido: ", "Ingresar Detalle Pedido");
        if (idPedido == -1) { return; }

        int idProducto= ConsoleUI.ingresarNumero("Ingrese el id del producto: ", "Ingresar Detalle Pedido");
        if (idProducto == -1) { return; }

        DetallePedido detallePedido = detallePedidoService.listarDetallePorId(idPedido, idProducto);
        JOptionPane.showMessageDialog( null,  detallePedido,  "Ver Detalle Pedido",  JOptionPane.INFORMATION_MESSAGE );
    }

    private static void modificarCantidadProducto(DetallePedidoService detallePedidoService) {
    }

    private static void eliminarDetalle(DetallePedidoService detallePedidoService) {
    }

    private static void eliminarDetallesPorPedido(DetallePedidoService detallePedidoService) {
    }

    private static void calcularTotalPedido(DetallePedidoService detallePedidoService) {
    }
}