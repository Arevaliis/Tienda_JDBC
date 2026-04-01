package dao.impl;

import dao.interfaces.IClienteDAO;
import exception.DAOException;
import model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
            throw new DAOException("Error DAO: Fallo durante la inserción del cliente");
        }
    }

    @Override
    public void buscarClienteID(int id) throws DAOException{

    }

    @Override
    public void mostrarTodosClientes() throws DAOException{

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
