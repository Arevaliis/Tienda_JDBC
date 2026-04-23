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

            if (insert.executeUpdate() == 0){throw new DAOException("No se insertó ningún detalle del pedido en la base de datos.");}

        } catch (SQLException e) { throw new DAOException("Error al insertar el detalle del pedido en la base de datos", e); }
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
                        "pr.descripcion, " +
                        "pr.precio, " +
                        "pr.stock, " +
                        "dp.cantidad, " +
                        "dp.precio_unitario, " +
                        "p.fecha " +

                        "FROM detalle_pedido dp " +

                        "INNER JOIN pedido p ON dp.id_pedido = p.id " +
                        "INNER JOIN cliente c ON c.id = p.id_cliente " +
                        "INNER JOIN producto pr ON pr.id = dp.id_producto " +

                        "WHERE dp.id_pedido = ? " +
                        "ORDER BY pr.id ASC";

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
                                                        resultado.getString("nombre_producto"),
                                                        resultado.getString("descripcion"),
                                                        resultado.getInt("precio"),
                                                        resultado.getInt("stock")
                    );

                    Pedido pedido = new Pedido( resultado.getInt("id_pedido"),
                                                cliente,
                                                resultado.getTimestamp("fecha")
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

        } catch (SQLException e) { throw new DAOException("Error al buscar los detalles del pedido en la base de datos", e); }
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
                        "pr.descripcion, " +
                        "pr.precio, " +
                        "pr.stock, " +
                        "dp.cantidad, " +
                        "dp.precio_unitario, " +
                        "p.fecha " +

                        "FROM detalle_pedido dp " +

                        "INNER JOIN pedido p ON dp.id_pedido = p.id " +
                        "INNER JOIN cliente c ON c.id = p.id_cliente " +
                        "INNER JOIN producto pr ON pr.id = dp.id_producto " +

                        "WHERE dp.id_pedido = ? and pr.id = ? " +
                        "ORDER BY pr.id ASC";

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
                                                  resultado.getString("nombre_producto"),
                                                  resultado.getString("descripcion"),
                                                  resultado.getInt("precio"),
                                                  resultado.getInt("stock")
                );

                Pedido pedido = new Pedido( resultado.getInt("id_pedido"),
                                            cliente,
                                            resultado.getTimestamp("fecha")
                );

                return new DetallePedido(  pedido,
                                           producto,
                                           resultado.getInt("cantidad"),
                                           resultado.getDouble("precio_unitario")
                );
            }

        } catch (SQLException e) { throw new DAOException("Error al buscar el detalle del pedido en la base de datos", e); }
    }

    @Override
    public void modificarCantidadProducto(DetallePedido detallePedido) throws DAOException {
        String sql = "UPDATE detalle_pedido SET cantidad = ? WHERE id_pedido = ? AND id_producto = ?";

        try (PreparedStatement update = connection.prepareStatement(sql)) {
            update.setInt(1, detallePedido.getCantidad());
            update.setInt(2, detallePedido.getPedido().getId());
            update.setInt(3, detallePedido.getProducto().getId());

            if (update.executeUpdate() == 0) { throw new DAOException("No se actualizó la cantidad en la base de datos"); }

        } catch (SQLException e) { throw new DAOException("Error al modificar la cantidad del detalle del pedido en la base de datos", e); }
    }

    @Override
    public void eliminarDetallesPedido(int idPedido, int idProducto) throws DAOException {
        String sql = "DELETE FROM detalle_pedido WHERE id_pedido = ? AND id_producto = ?";

        try (PreparedStatement delete = connection.prepareStatement(sql)) {
            delete.setInt(1, idPedido);
            delete.setInt(2, idProducto);

            if (delete.executeUpdate() == 0) { throw new DAOException("No se eliminó el detalle pedido en la base de datos"); }

        } catch (SQLException e) { throw new DAOException("Error al eliminar el detalle del pedido en la base de datos", e); }
    }

    @Override
    public void eliminarPorPedido(int idPedido) throws DAOException {
        String sql = "DELETE FROM detalle_pedido WHERE id_pedido = ?";

        try (PreparedStatement delete = connection.prepareStatement(sql)) {
            delete.setInt(1, idPedido);

            if (delete.executeUpdate() == 0) { throw new DAOException("No se eliminó los detalles del pedido en la base de datos"); }

        } catch (SQLException e) { throw new DAOException("Error al eliminar los detalles del pedido en la base de datos", e); }
    }

    @Override
    public List<DetallePedido> listarDetallesPedidos() throws DAOException {
        String sql =
                "SELECT  dp.id_pedido, " +
                        "c.id AS id_cliente, " +
                        "c.nombre, " +
                        "c.apellido, " +
                        "pr.id AS id_producto, " +
                        "pr.nombre AS nombre_producto, " +
                        "pr.descripcion, " +
                        "pr.precio, " +
                        "pr.stock, " +
                        "dp.cantidad, " +
                        "dp.precio_unitario, " +
                        "p.fecha " +

                        "FROM detalle_pedido dp " +

                        "INNER JOIN pedido p ON dp.id_pedido = p.id " +
                        "INNER JOIN cliente c ON c.id = p.id_cliente " +
                        "INNER JOIN producto pr ON pr.id = dp.id_producto " +

                        "ORDER BY pr.id ASC";

        try (PreparedStatement selectPedido = connection.prepareStatement(sql);
             ResultSet resultado = selectPedido.executeQuery()){

            if (! resultado.next()) { return new ArrayList<>(); }

            List<DetallePedido> detallesDePedido = new ArrayList<>();

            do {
                Cliente cliente = new Cliente(  resultado.getInt("id_cliente"),
                        resultado.getString("nombre"),
                        resultado.getString("apellido")
                );

                Producto producto = new Producto( resultado.getInt("id_producto"),
                        resultado.getString("nombre_producto"),
                        resultado.getString("descripcion"),
                        resultado.getInt("precio"),
                        resultado.getInt("stock")
                );

                Pedido pedido = new Pedido( resultado.getInt("id_pedido"),
                        cliente,
                        resultado.getTimestamp("fecha")
                );

                DetallePedido detallePedido = new DetallePedido(
                        pedido,
                        producto,
                        resultado.getInt("cantidad"),
                        resultado.getDouble("precio_unitario")
                );

                detallesDePedido.add( detallePedido );

            } while (resultado.next());

            return detallesDePedido;

        } catch (SQLException e) { throw new DAOException("Error al buscar los detalles de todos los pedidos en la base de datos", e); }
    }

    /**
     * Calcula el total de un pedido.
     *
     * @param idPedido ID del pedido
     * @return Importe total
     * @throws DAOException Si ocurre un error en la base de datos
     */
    public double obtenerTotalPedido(int idPedido) throws DAOException {
        String sql = "SELECT sum(cantidad * precio_unitario) AS total " +
                "FROM detalle_pedido " +
                "WHERE id_pedido = ?;";

        try (PreparedStatement sumTotal = connection.prepareStatement(sql)) {
            sumTotal.setInt(1, idPedido);

            try(ResultSet resultado = sumTotal.executeQuery()){
                if (! resultado.next()) { throw new DAOException("No se pudo obtener el total del pedido en la base de datos"); }

                return resultado.getDouble("total");
            }

        } catch (SQLException e) { throw new DAOException("Error al calcular el total del pedido en la base de datos", e); }
    }

    /**
     * Calcula el total vendido.
     *
     * @return Importe total
     * @throws DAOException Si ocurre un error en la base de datos
     */
    public double obtenerTotalVendido() throws DAOException {
        String sql = "SELECT sum(cantidad * precio_unitario) AS total FROM detalle_pedido";

        try (PreparedStatement sumTotal = connection.prepareStatement(sql);
             ResultSet resultado = sumTotal.executeQuery()){

                if (! resultado.next()) { throw new DAOException("No se pudo calcular el total de ventas"); }

                return resultado.getDouble("total");

        } catch (SQLException e) { throw new DAOException("Error al calcular el total vendido en la base de datos", e); }
    }
}