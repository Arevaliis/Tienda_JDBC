package gestores;

import exception.ServiceException;
import exception.ValidationException;
import model.Cliente;
import model.DetallePedido;
import model.Pedido;
import service.impl.DetallePedidoService;
import util.ConsoleUI;
import util.DatabaseConnection;
import util.Mensajes;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GestorDetallesPedidio {

    public static void ejecutarMenuPedidoDetalles( ){
        boolean seguir = true;

        try (Connection connection = DatabaseConnection.getConnection()) {
            DetallePedidoService detallePedidoService = new DetallePedidoService(connection);


            while (seguir) {
                try {

                    if (ejecutarOpcion(detallePedidoService) <= 0) { return; }
                    seguir = ConsoleUI.confirmarContinuacion("¿Desea seguir en la sección de pedidos? S/N: ", "Seguir Menu Pedidos");

                } catch (IllegalArgumentException | ServiceException | ValidationException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

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
        List<String> mensaje = detallesPedido.stream()
                                              .map(DetallePedido::toString)
                                              .toList();

        JOptionPane.showMessageDialog( null,  String.join("\n", mensaje),  "Ver Detalles Pedido",  JOptionPane.INFORMATION_MESSAGE );
    }

    private static void obtenerDetalleConcreto(DetallePedidoService detallePedidoService) {
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