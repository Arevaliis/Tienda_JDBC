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

        } catch (SQLException e) { throw new DAOException("Error DAO: Fallo durante la inserción del cliente", e); }
    }

    @Override
    public Cliente buscarClienteID(int id) throws DAOException{
        String sql = "SELECT c.id, c.nombre, c.apellido, e.email " +
                     "FROM cliente c " +
                     "INNER JOIN email e ON e.id_cliente = c.id " +
                     "WHERE c.id = ? ";

        try (PreparedStatement selectCliente = connection.prepareStatement(sql)){
            selectCliente.setInt(1, id);

            try (ResultSet resultado = selectCliente.executeQuery()){

                if (!resultado.next()){ return null; }

                return new Cliente( resultado.getInt("id"),
                                    resultado.getString("nombre"),
                                    resultado.getString("apellido"),
                        (List<String>) resultado.getArray("email"));
            }

        } catch (SQLException e) { throw new DAOException("Error DAO: Fallo durante select de cliente", e); }
    }

    @Override
    public List<Cliente> mostrarTodosClientes() throws DAOException{
        String sql = "SELECT c.id, c.nombre, c.apellido, e.email " +
                     "FROM cliente c " +
                     "INNER JOIN email e ON e.id_cliente = c.id " +
                     "ORDER BY c.id ASC";

        try (PreparedStatement selectTodosCliente = connection.prepareStatement(sql);
            ResultSet resultado = selectTodosCliente.executeQuery()){

                if (!resultado.next()){ return new ArrayList<>(); }

                List<Cliente> clientes = new ArrayList<>();

                do {
                    clientes.add(
                            new Cliente( resultado.getInt("id"),
                                         resultado.getString("nombre"),
                                         resultado.getString("apellido"),
                          (List<String>) resultado.getArray("email")

                            )
                    );

                } while (resultado.next());

                return clientes;

        } catch (SQLException e) { throw new DAOException("Error DAO: Fallo durante select de cliente", e); }
    }

    @Override
    public void modificarNombreCliente(Cliente cliente) throws DAOException{
        String sql = "UPDATE cliente SET nombre = ? WHERE id = ?";

        try (PreparedStatement updateNombre = connection.prepareStatement(sql)){
            updateNombre.setString(1, cliente.getNombre());
            updateNombre.setInt(2, cliente.getId());

            if (updateNombre.executeUpdate() == 0){throw new DAOException("No se ha podido modificar el nombre del cliente.");}

        } catch (SQLException e) { throw new DAOException("Error DAO: Fallo durante el update del nombre del cliente", e); }
    }

    @Override
    public void modificarApellidoCliente(Cliente cliente) throws DAOException{
        String sql = "UPDATE cliente SET apellido = ? WHERE id = ?";

        try (PreparedStatement updateApellido = connection.prepareStatement(sql)){
            updateApellido.setString(1, cliente.getApellido());
            updateApellido.setInt(2, cliente.getId());

            if (updateApellido.executeUpdate() == 0) { throw new DAOException("No se ha podido modificar el apellido del cliente."); }

        } catch (SQLException e) { throw new DAOException("Error DAO: Fallo durante el update del apellido del cliente", e); }
    }

    @Override
    public void eliminarCliente(int id) throws DAOException{
        String sql = "DELETE FROM cliente WHERE id = ?";

        try (PreparedStatement delete = connection.prepareStatement(sql)){
            delete.setInt(1, id);

            if (delete.executeUpdate() == 0){ throw new DAOException("No se ha podido eliminar al cliente con id " + id);}

        } catch (SQLException e) { throw new DAOException("Error DAO: Fallo durante el delete del cliente", e); }
    }
}