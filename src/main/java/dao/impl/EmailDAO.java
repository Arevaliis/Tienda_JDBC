package dao.impl;

import dao.interfaces.IEmailDAO;
import exception.DAOException;
import model.Email;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmailDAO implements IEmailDAO {
    private final Connection connection;

    public EmailDAO(Connection connection) { this.connection = connection; }

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
    public List<String> obtenerNombreEmails() throws DAOException {
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
    public void actualizarEmail(Email email) throws DAOException {
        String sql = "UPDATE email SET email = ?, id_cliente = ? WHERE id = ?";

        try (PreparedStatement update = connection.prepareStatement(sql)){
            update.setString(1, email.getEmail());
            update.setInt(2, email.getIdCliente());
            update.setInt(3, email.getId());

            if (update.executeUpdate() == 0) { throw new DAOException("No se ha podido modificar el email del cliente."); }

        } catch (SQLException e) { throw new DAOException("Error DAO: Fallo durante el update del apellido del cliente", e); }
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