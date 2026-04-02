package dao.impl;

import dao.interfaces.IClienteDAO;
import exception.DAOException;
import model.Cliente;
import model.Email;

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
        String sql = "SELECT id, nombre, apellido FROM cliente WHERE id = ?";

        try (PreparedStatement selectCliente = connection.prepareStatement(sql)){
            selectCliente.setInt(1, id);

            try (ResultSet resultado = selectCliente.executeQuery()){

                if (!resultado.next()){ return null; }

                return new Cliente( resultado.getInt("id"),
                                    resultado.getString("nombre"),
                                    resultado.getString("apellido"));
            }

        } catch (SQLException e) { throw new DAOException("Error DAO: Fallo durante select de cliente", e); }
    }

    @Override
    public List<Cliente> mostrarTodosClientes() throws DAOException{
        String sql = "SELECT id, nombre, apellido FROM cliente";

        try (PreparedStatement selectTodosCliente = connection.prepareStatement(sql);
            ResultSet resultado = selectTodosCliente.executeQuery()){

                if (!resultado.next()){ return new ArrayList<>(); }

                List<Cliente> clientes = new ArrayList<>();

                do {
                    clientes.add(
                            new Cliente(resultado.getInt("id"),
                                        resultado.getString("nombre"),
                                        resultado.getString("apellido")));

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

            if (delete.executeUpdate() == 0){throw new DAOException("No se ha podido eliminar al cliente con id " + id);}

        } catch (SQLException e) { throw new DAOException("Error DAO: Fallo durante el delete del cliente", e); }
    }

    @Override
    public void agregarEmail(Email email) throws DAOException {
        String sql = "INSERT INTO email(email, id_cliente) VALUES (?,?)";

        try (PreparedStatement insert = connection.prepareStatement(sql)){
            insert.setString(1, email.getEmail());
            insert.setInt(2, email.getIdCliente());

            if (insert.executeUpdate() == 0){throw new DAOException("El email ingresado ya existe en la base de datos.");}

        } catch (SQLException e) { throw new DAOException("Error DAO: Fallo durante el insert del email", e); }
    }

    @Override
    public List<String> obtenerEmails() throws DAOException {
        String sql = "SELECT email FROM email";

        try (PreparedStatement selectTodosEmails = connection.prepareStatement(sql);
             ResultSet resultado = selectTodosEmails.executeQuery()){

            List<String> emails = new ArrayList<>();

            if (! resultado.next()){ return new ArrayList<>(); }

            do{
                emails.add( resultado.getString("email"));
            } while (resultado.next());

            return emails;

        } catch (SQLException e) { throw new DAOException("Error DAO: Fallo durante el select de los emails", e); }
    }


    @Override
    public void modificarEmail(Email email) throws DAOException {
        String sql = "UPDATE email SET email = ? WHERE id = ?";

        try (PreparedStatement updateEmail = connection.prepareStatement(sql)){
            updateEmail.setString(1, email.getEmail());
            updateEmail.setInt(2, email.getId());

            if (updateEmail.executeUpdate() == 0) { throw new DAOException("No se ha podido modificar el email del cliente."); }

        } catch (SQLException e) { throw new DAOException("Error DAO: Fallo durante el update del apellido del cliente", e); }
    }

    @Override
    public Email buscarEmail(int id) throws DAOException {
        String sql = "SELECT id, email, id_cliente FROM email WHERE id = ?";

        try (PreparedStatement selectEmail = connection.prepareStatement(sql)){
            selectEmail.setInt(1, id);

            try(ResultSet resultado = selectEmail.executeQuery()) {

                if (! resultado.next()){ return null; }

                return new Email(resultado.getInt("id"),
                                 resultado.getString("email"),
                                 resultado.getInt("id_cliente"));
            }

        } catch (SQLException e) { throw new DAOException("Error DAO: Fallo durante el select de los emails", e); }
    }

    @Override
    public void cambiarIdClienteEmail(Email email) throws DAOException {
        String sql = "UPDATE email SET id_cliente = ? WHERE id = ?";

        try (PreparedStatement updateIdClienteEmail = connection.prepareStatement(sql)){
            updateIdClienteEmail.setInt(1, email.getIdCliente());
            updateIdClienteEmail.setInt(2, email.getId());

            if (updateIdClienteEmail.executeUpdate() == 0) { throw new DAOException("No se ha podido modificar el id_cliente del email"); }

        } catch (SQLException e) { throw new DAOException("Error DAO: Fallo durante el update del id_cliente de email", e); }
    }

    @Override
    public List<Email> verEmailsPorCliente(int id) throws DAOException {
        String sql = "SELECT id, email, id_cliente FROM email WHERE id_cliente = ?";

        try (PreparedStatement selectEmailsCliente = connection.prepareStatement(sql)){
            selectEmailsCliente.setInt(1, id);

            try(ResultSet resultado = selectEmailsCliente.executeQuery()) {

                if (! resultado.next()){ return new ArrayList<>(); }

                List<Email> emails = new ArrayList<>();

                do{
                    emails.add(
                            new Email(resultado.getInt("id"),
                                      resultado.getString("email"),
                                      resultado.getInt("id_cliente")));

                } while (resultado.next());

                return emails;
            }

        } catch (SQLException e) { throw new DAOException("Error DAO: Fallo durante el select de los emails", e); }
    }

    @Override
    public void eliminarEmail(int id) throws DAOException {
        String sql = "DELETE FROM email WHERE id = ?";

        try (PreparedStatement delete = connection.prepareStatement(sql)){
            delete.setInt(1, id);

            if (delete.executeUpdate() == 0) { throw new DAOException("No se ha podido eliminar el email"); }

        } catch (SQLException e) { throw new DAOException("Error DAO: Fallo durante el delete del email", e); }
    }
}