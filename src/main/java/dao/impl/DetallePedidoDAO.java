package dao.impl;

import dao.interfaces.IDetallePedidoDAO;
import exception.DAOException;
import model.DetallePedido;

import java.sql.Connection;
import java.util.List;

public class DetallePedidoDAO implements IDetallePedidoDAO {
    private final Connection connection;

    public DetallePedidoDAO(Connection connection) { this.connection = connection; }


    @Override
    public void insertar(DetallePedido detalle) throws DAOException {

    }

    @Override
    public List<DetallePedido> listarPedido(int idPedido) throws DAOException {
        return List.of();
    }

    @Override
    public DetallePedido listarDetallesPorId(int idPedido, int idProducto) throws DAOException {
        return null;
    }

    @Override
    public void modificarCantidadProducto(int idPedido, int idProducto, int cantidad) throws DAOException {

    }

    @Override
    public void eliminarDetallesPedido(int idPedido, int idProducto) throws DAOException {

    }

    @Override
    public void eliminarPorPedido(int idPedido) throws DAOException {

    }

    @Override
    public double obtenerTotalPedido(int idPedido) throws DAOException {
        return 0;
    }
}
