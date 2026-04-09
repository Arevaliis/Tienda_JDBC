package dao.impl;

import dao.interfaces.IDetallePedidoDAO;
import exception.DAOException;
import model.DetallePedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetallePedidoDAO implements IDetallePedidoDAO {
    private final Connection connection;

    public DetallePedidoDAO(Connection connection) { this.connection = connection; }

    @Override
    public void insertar(DetallePedido detalle) throws DAOException {
        String sql = "INSERT INTO detalle_pedido (id_pedido, id_producto, cantidad, precio_unitario) VALUES (?,?,?,?)";

        try (PreparedStatement insert = connection.prepareStatement(sql)){
            insert.setInt(1, detalle.getIdPedido());
            insert.setInt(2, detalle.getIdProducto());
            insert.setInt(3, detalle.getCantidad());
            insert.setDouble(4, detalle.getPrecioUnitario());


            if (insert.executeUpdate() == 0) { throw new DAOException("No se ha podido completar el registro de los detalles del pedido en la base de datos"); }

        } catch (SQLException e) { throw new DAOException("Error DAO: Fallo durante insert detalle pedido", e); }
    }

    @Override
    public List<DetallePedido> listarDetallesPedido(int idPedido) throws DAOException {
        String sql = "SELECT id_pedido, id_producto, cantidad, precio_unitario FROM detalle_pedido WHERE id_pedido = ?";

        try (PreparedStatement selectPedido = connection.prepareStatement(sql)){
            selectPedido.setInt(1, idPedido);

            try(ResultSet resultado = selectPedido.executeQuery()){

                if (! resultado.next()) { return new ArrayList<>(); }

                List<DetallePedido> detallesDePedido = new ArrayList<>();

                do {
                    detallesDePedido.add(
                                        new DetallePedido(resultado.getInt("id_pedido"),
                                                          resultado.getInt("id_producto"),
                                                          resultado.getInt("cantidad"),
                                                          resultado.getDouble("precio_unitario"))
                    );
                } while (resultado.next());

                return detallesDePedido;
            }

        } catch (SQLException e) { throw new DAOException("Error DAO: Fallo durante select de los detalles del pedido con id: " + idPedido, e); }
    }

    @Override
    public DetallePedido listarDetallePorId(int idPedido, int idProducto) throws DAOException {
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
