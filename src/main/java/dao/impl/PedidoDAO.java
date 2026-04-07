package dao.impl;

import dao.interfaces.IPedidoDAO;
import exception.DAOException;
import model.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO implements IPedidoDAO {
    private final Connection connection;

    public PedidoDAO(Connection connection) { this.connection = connection; }

    @Override
    public void crearPedido(Pedido pedido) throws DAOException {
        String sql = "INSERT INTO pedido (id_cliente) VALUES (?)";

        try (PreparedStatement insert = connection.prepareStatement(sql)){
            insert.setInt(1, pedido.getId_cliente());

            if (insert.executeUpdate() == 0) { throw new DAOException("No se ha podido completar el registro del pedido en la base de datos"); }

        } catch (SQLException e) { throw new DAOException("Error DAO: Fallo durante insert pedido", e); }
    }

    @Override
    public Pedido buscarPedidoID(int idPedido) throws DAOException {
        String sql = "SELECT id, id_cliente, fecha FROM pedido WHERE id = ?";

        try (PreparedStatement selectPedido = connection.prepareStatement(sql)){
            selectPedido.setInt(1, idPedido);

            try(ResultSet resultado = selectPedido.executeQuery()) {

                if (! resultado.next()) { return null; }

                return new Pedido(  resultado.getInt("id"),
                                    resultado.getInt("id_cliente"),
                                    resultado.getTimestamp("fecha"));
            }

        } catch (SQLException e) { throw new DAOException("Error DAO: Fallo durante select pedido", e); }
    }

    @Override
    public List<Pedido> listarPedidos() throws DAOException {
        String sql = "SELECT id, id_cliente, fecha FROM pedido";

        try (PreparedStatement selectTodosLosPedidos = connection.prepareStatement(sql);
             ResultSet resultado = selectTodosLosPedidos.executeQuery()) {

                if (! resultado.next()) { return new ArrayList<>(); }

                List<Pedido> pedidos = new ArrayList<>();

                do{
                    pedidos.add(
                                new Pedido( resultado.getInt("id"),
                                            resultado.getInt("id_cliente"),
                                            resultado.getTimestamp("fecha")));
                } while (resultado.next());

                return pedidos;

        } catch (SQLException e) { throw new DAOException("Error DAO: Fallo durante select de los pedidos", e); }
    }

    @Override
    public List<Pedido> listarPedidosPorCliente(int idCliente) throws DAOException {
        String sql = "SELECT id, id_cliente, fecha FROM pedido WHERE id_cliente = ?";

        try (PreparedStatement selectPedido = connection.prepareStatement(sql)){
            selectPedido.setInt(1, idCliente);

            try(ResultSet resultado = selectPedido.executeQuery()) {

                if (! resultado.next()) { return new ArrayList<>(); }

                List<Pedido> pedidos = new ArrayList<>();

                do{
                    pedidos.add(
                            new Pedido( resultado.getInt("id"),
                                        resultado.getInt("id_cliente"),
                                        resultado.getTimestamp("fecha")));

                } while (resultado.next());

                return pedidos;
            }

        } catch (SQLException e) { throw new DAOException("Error DAO: Fallo durante select pedido del cliente con id: " + idCliente, e); }
    }

    @Override
    public void actualizarPedido(Pedido pedido) throws DAOException {
        String sql = "UPDATE pedido " +
                     "SET id_cliente = ?, fecha = ? " +
                     "WHERE id = ?";

        try(PreparedStatement update = connection.prepareStatement(sql)) {
            update.setInt(1, pedido.getId_cliente());
            update.setTimestamp(2, pedido.getFecha());
            update.setInt(3, pedido.getId());

            if (update.executeUpdate() == 0){ throw new DAOException("No se ha podido actualizar el pedido"); }

        } catch (SQLException e) { throw new DAOException("Error DAO: Fallo al hacer update del pedido", e); }
    }

    @Override
    public void eliminarPedido(int idPedido) throws DAOException {
        String sql = "DELETE FROM pedido WHERE id = ?";

        try(PreparedStatement delete = connection.prepareStatement(sql)) {
            delete.setInt(1, idPedido);

            if (delete.executeUpdate() == 0){ throw new DAOException("No se ha podido eliminar el pedido"); }

        } catch (SQLException e) { throw new DAOException("Error DAO: Fallo al hacer delete del pedido", e); }
    }
}