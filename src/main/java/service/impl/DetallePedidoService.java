package service.impl;

import dao.impl.DetallePedidoDAO;
import exception.ServiceException;
import model.DetallePedido;
import service.interfaces.IDetallePedidoService;

import java.sql.Connection;
import java.util.List;

public class DetallePedidoService implements IDetallePedidoService {
    private final Connection connection;
    private final DetallePedidoDAO detallePedidoDAO;
    private final PedidoService pedidoService;

    public DetallePedidoService(Connection connection) {
        this.connection = connection;
        this.detallePedidoDAO = new DetallePedidoDAO(connection);
        this.pedidoService = new PedidoService(connection);
    }

    @Override
    public void insertarDetallePedido(DetallePedido detalle) throws ServiceException {

    }

    @Override
    public List<DetallePedido> listarPedido(int idPedido) throws ServiceException {
        return List.of();
    }

    @Override
    public DetallePedido listarDetallesPorId(int idPedido, int idProducto) throws ServiceException {
        return null;
    }

    @Override
    public void modificarCantidadProducto(int idPedido, int idProducto, int cantidad) throws ServiceException {

    }

    @Override
    public void eliminarDetallesPedido(int idPedido, int idProducto) throws ServiceException {

    }

    @Override
    public void eliminarPorPedido(int idPedido) throws ServiceException {

    }

    @Override
    public double obtenerTotalPedido(int idPedido) throws ServiceException {
        return 0;
    }
}
