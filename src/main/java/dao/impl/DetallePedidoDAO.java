package dao.impl;

import dao.interfaces.IDetallePedidoDAO;
import exception.DAOException;
import model.Cliente;
import model.DetallePedido;
import model.Pedido;
import model.Producto;

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
        String sql =
                "SELECT  dp.id_pedido, " +
                        "c.id AS id_cliente, " +
                        "c.nombre, " +
                        "c.apellido, " +
                        "pr.id AS id_producto, " +
                        "pr.nombre AS nombre_producto, " +
                        "dp.cantidad, " +
                        "dp.precio_unitario, " +
                        "p.fecha " +

                        "FROM detalle_pedido dp " +

                        "INNER JOIN pedido p ON dp.id_pedido = p.id " +
                        "INNER JOIN cliente c ON c.id = p.id_cliente " +
                        "INNER JOIN producto pr ON pr.id = dp.id_producto " +

                        "WHERE dp.id_pedido = ? " +
                        "ORDER BY id_producto ASC";

        try (PreparedStatement selectPedido = connection.prepareStatement(sql)){
            selectPedido.setInt(1, idPedido);

            try(ResultSet resultado = selectPedido.executeQuery()){

                if (! resultado.next()) { return new ArrayList<>(); }

                List<DetallePedido> detallesDePedido = new ArrayList<>();

                Cliente cliente = new Cliente(  resultado.getInt("id_cliente"),
                                                resultado.getString("nombre"),
                                                resultado.getString("apellido")
                );

                do {

                    Producto producto = new Producto( resultado.getInt("id_producto"),
                                                      resultado.getString("nombre_producto")
                    );

                    Pedido pedido = new Pedido( resultado.getInt("id_pedido"),
                                                cliente,
                                                resultado.getTimestamp("fecha"),
                                    null
                    );

                    DetallePedido detallePedido = new DetallePedido(  pedido,
                                                                      producto,
                                                                      resultado.getInt("cantidad"),
                                                                      resultado.getDouble("precio_unitario")
                    );

                    detallesDePedido.add( detallePedido );

                } while (resultado.next());

                return detallesDePedido;
            }

        } catch (SQLException e) { throw new DAOException("Error DAO: Fallo durante select de los detalles del pedido con id: " + idPedido, e); }
    }

    @Override
    public DetallePedido listarDetallePorId(int idPedido, int idProducto) throws DAOException {
        String sql =
                "SELECT  dp.id_pedido, " +
                        "c.id AS id_cliente, " +
                        "c.nombre, " +
                        "c.apellido, " +
                        "pr.id AS id_producto, " +
                        "pr.nombre AS nombre_producto, " +
                        "dp.cantidad, " +
                        "dp.precio_unitario, " +
                        "p.fecha " +

                        "FROM detalle_pedido dp " +

                        "INNER JOIN pedido p ON dp.id_pedido = p.id " +
                        "INNER JOIN cliente c ON c.id = p.id_cliente " +
                        "INNER JOIN producto pr ON pr.id = dp.id_producto " +

                        "WHERE dp.id_pedido = ? and dp.id_producto = ? " +
                        "ORDER BY id_producto ASC";

        try (PreparedStatement selectPedido = connection.prepareStatement(sql)){
            selectPedido.setInt(1, idPedido);
            selectPedido.setInt(2, idProducto);

            try(ResultSet resultado = selectPedido.executeQuery()){

                if (! resultado.next()) { return null; }

                Cliente cliente = new Cliente(  resultado.getInt("id_cliente"),
                                                resultado.getString("nombre"),
                                                resultado.getString("apellido")
                );

                Producto producto = new Producto( resultado.getInt("id_producto"),
                                                  resultado.getString("nombre_producto")
                );

                Pedido pedido = new Pedido( resultado.getInt("id_pedido"),
                                            cliente,
                                            resultado.getTimestamp("fecha"),
                                null
                );

                return new DetallePedido(  pedido,
                                           producto,
                                           resultado.getInt("cantidad"),
                                           resultado.getDouble("precio_unitario")
                );
            }

        } catch (SQLException e) { throw new DAOException("Error DAO: Fallo durante select de uno de los detalles del pedido con id: " + idPedido, e); }
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
