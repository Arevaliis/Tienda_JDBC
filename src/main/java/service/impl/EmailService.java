package service.impl;

import dao.impl.EmailDAO;
import exception.DAOException;
import exception.ServiceException;
import model.Cliente;
import model.Email;
import service.interfaces.IEmailService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmailService implements IEmailService {
    private final Connection connection;
    private final EmailDAO emailDAO;
    private final ClienteService clienteService;

    public EmailService(Connection connection, ClienteService clienteService) {
        this.connection = connection;
        this.clienteService = clienteService;
        this.emailDAO = new EmailDAO(connection);
    }

    @Override
    public void agregarEmail(String direccionEmail, int idCliente) throws ServiceException {

        try {
            connection.setAutoCommit(false);

            try {
                // Verifica que el ID del cliente exista en la tabla cliente
                clienteService.buscarClienteID(idCliente);

                // Verifica que el correo ingresado no exista en la base de datos
                if (emailDAO.obtenerNombreEmails().contains(direccionEmail)) { throw new ServiceException("El email ya está registrado en la base de  datos"); }

                emailDAO.agregarEmail(new Email(direccionEmail, idCliente));
                connection.commit();

            } catch (SQLException | DAOException e) {
                connection.rollback();
                throw new ServiceException("Error Service: Fallo durante la transacción de insert email", e);

            } finally {
                connection.setAutoCommit(true);
            }

        } catch ( SQLException e) { throw new ServiceException("Error al configurar la transacción", e); }
    }

    @Override
    public Email buscarEmail(int id) throws ServiceException {
        try{
            Email email = emailDAO.buscarEmail(id);
            if (email == null){ throw new ServiceException("No existe ningún registro de email con el id: " + id); }

            return email;

        } catch (DAOException e) { throw new ServiceException("Error Service: Fallo durante la busqueda del email con id: "+ id, e); }

    }

    @Override
    public void modificarNombreEmail(String nuevoEmail, int idEmail) throws ServiceException {

        try {
            connection.setAutoCommit(false);

            try {
                Email email = buscarEmail(idEmail);

                // Verifica que el nuevo correo ingresado no exista en la base de datos
                if (emailDAO.obtenerNombreEmails().contains(nuevoEmail)) { throw new ServiceException("El email ya está registrado en la base de  datos"); }

                email.setEmail(nuevoEmail);
                emailDAO.actualizarEmail(email);

                connection.commit();

            } catch (SQLException | DAOException e) {
                connection.rollback();
                throw new ServiceException("Error Service: Fallo durante la transacción de update email", e);

            } finally { connection.setAutoCommit(true); }

        } catch ( SQLException e) { throw new ServiceException("Error al configurar la transacción", e); }
    }

    @Override
    public void modificarIdClienteEmail(int nuevoClienteId, int id) throws ServiceException {
        try {

            connection.setAutoCommit(false);

            try {
                Email email = buscarEmail(id);
                clienteService.buscarClienteID(nuevoClienteId);

                email.setIdCliente(nuevoClienteId);
                emailDAO.actualizarEmail(email);

                connection.commit();

            } catch (SQLException | DAOException e) {
                connection.rollback();
                throw new ServiceException("Error Service: Fallo durante el cambio de id cliente en el email", e);

            } finally { connection.setAutoCommit(true); }

        } catch ( SQLException e) { throw new ServiceException("Error al configurar la transacción", e); }
    }

    @Override
    public List<Email> verEmailsPorCliente(int id) throws ServiceException {

        try{
            List<Email> emailsIdInt = emailDAO.verEmailsPorCliente(id);
            if (emailsIdInt.isEmpty()) { throw new ServiceException("No hay email registrados en la base de datos para el cliente con id: " + id); }

            List<Email> emailsCliente = new ArrayList<>();
            Cliente cliente = clienteService.buscarClienteID(emailsIdInt.get(0).getIdCliente());

            for (Email email: emailsIdInt){
                emailsCliente.add(
                                new Email(email.getId(),
                                        email.getEmail(),
                                        cliente));
            }

            return emailsCliente;

        } catch (DAOException e) { throw new ServiceException("Error Service: Fallo durante la búsqueda de email por cliente", e); }
    }

    @Override
    public void eliminarEmail(int id) throws ServiceException {
        try {
            if (buscarEmail(id) == null){ return ;}

            emailDAO.eliminarEmail(id);

        } catch (DAOException e) { throw new ServiceException("Error Service: Fallo durante la eliminación de email con id: " + id, e); }

    }
}
