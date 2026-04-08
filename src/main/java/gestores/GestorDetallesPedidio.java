package gestores;

import exception.ServiceException;
import exception.ValidationException;
import service.impl.DetallePedidoService;
import util.ConsoleUI;
import util.DatabaseConnection;
import util.Mensajes;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

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

                } catch (NullPointerException ignored) {
                    seguir = ConsoleUI.confirmarContinuacion("¿Desea seguir en la sección de pedidos? S/N: ", "Seguir Menu Pedidos");
                }
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
    }

    private static void listarDetallesPorPedido(DetallePedidoService detallePedidoService) {
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