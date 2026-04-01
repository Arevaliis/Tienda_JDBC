package dao.impl;

import dao.interfaces.IClienteDAO;
import exception.DAOException;
import model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements IClienteDAO {
    private final Connection connection;

    public ClienteDAO(Connection connection) { this.connection = connection; }

    @Override
    public void insertarCliente(Cliente cliente) throws DAOException {
        String sql = "INSERT INTO cliente (nombre, apellido) VALUES (?,?)";

        try (PreparedStatement insert = connection.prepareStatement(sql)){
            insert.setString(1, cliente.getNombre());
            insert.setString(2, cliente.getApellido());

            if (insert.executeUpdate() == 0){throw new DAOException("No se ha completado la inserción del cliente en la base de datos.");}

        } catch (SQLException e) {
            throw new DAOException("Error DAO: Fallo durante la inserción del cliente", e);
        }
    }

    @Override
    public Cliente buscarClienteID(int id) throws DAOException{
        String sql = "SELECT id, nombre, apellido FROM cliente WHERE id = ?";

        try (PreparedStatement selectCliente = connection.prepareStatement(sql)){
            selectCliente.setInt(1, id);

            try (ResultSet resultado = selectCliente.executeQuery()){

                if (!resultado.next()){ return null; }

                return new Cliente( resultado.getInt("id"),
                                    resultado.getString("nombre"),
                                    resultado.getString("apellido"));
            }

        } catch (SQLException e) {
            throw new DAOException("Error DAO: Fallo durante select de cliente", e);
        }
    }

    @Override
    public List<Cliente> mostrarTodosClientes() throws DAOException{
        return new ArrayList<>();
    }

    @Override
    public void modificarNombreCliente(Cliente cliente) throws DAOException{

    }

    @Override
    public void modificarApellidoCliente(Cliente cliente) throws DAOException{

    }

    @Override
    public void eliminarCliente(int id) throws DAOException{

    }
}
