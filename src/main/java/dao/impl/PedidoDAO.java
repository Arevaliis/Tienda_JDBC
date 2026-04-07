package dao.impl;

import dao.interfaces.IPedidoDAO;
import exception.DAOException;
import model.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public Pedido buscarPedidoID(int id_pedido) throws DAOException {
        String sql = "SELECT id, id_cliente, fecha FROM pedido WHERE id = ?";

        try (PreparedStatement selectPedido = connection.prepareStatement(sql)){
            selectPedido.setInt(1, id_pedido);

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
        return List.of();
    }

    @Override
    public List<Pedido> listarPedidosPorCliente(int id_cliente) throws DAOException {
        return List.of();
    }

    @Override
    public void actualizarPedido(Pedido pedido) throws DAOException {

    }

    @Override
    public void eliminarPedido(int id_pedido) throws DAOException {

    }
}