package service.impl;

import dao.impl.ClienteDAO;
import exception.DAOException;
import exception.ServiceException;
import model.Cliente;
import model.Email;
import service.interfaces.IClienteService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteService implements IClienteService {
    private final Connection connection;
    private final ClienteDAO clienteDAO;

    public ClienteService(Connection connection) {
        this.connection = connection;
        this.clienteDAO = new ClienteDAO(connection);
    }

    @Override
    public void insertarCliente(Cliente cliente) throws ServiceException {
        try {
            clienteDAO.insertarCliente(cliente);

        } catch (DAOException e) { throw new ServiceException("Error Service: Fallo durante la inserción del cliente", e); }
    }

    @Override
    public Cliente buscarClienteID(int id) throws ServiceException {
        Cliente cliente;

        try{
            cliente = clienteDAO.buscarClienteID(id);

            if (cliente == null){throw new ServiceException("No existe ningún cliente con el id " + id);}

            return cliente;

        } catch (DAOException e) { throw new ServiceException("Error Service: Fallo durante select de cliente", e); }
    }

    @Override
    public List<Cliente> mostrarTodosClientes() throws ServiceException {
        List<Cliente> clientes;

        try{
            clientes = clienteDAO.mostrarTodosClientes();

            if (clientes.isEmpty()){throw new ServiceException("No existe registro de clientes en la base de datos");}

            return clientes;

        } catch (DAOException e) { throw new ServiceException("Error Service: Fallo durante select de todos los clientes", e); }
    }

    @Override
    public void modificarNombreCliente(String nombre, int id) throws ServiceException {
        Cliente cliente;

        try{
            cliente = buscarClienteID(id);
            cliente.setNombre(nombre);
            clienteDAO.modificarNombreCliente(cliente);

        } catch (DAOException e) { throw new ServiceException("Error Service: Fallo durante la modificación del nombre de cliente", e); }
    }

    @Override
    public void modificarApellidoCliente(String apellido, int id) throws ServiceException {
        Cliente cliente;

        try{
            cliente = buscarClienteID(id);
            cliente.setApellido(apellido);
            clienteDAO.modificarApellidoCliente(cliente);

        } catch (DAOException e) { throw new ServiceException("Error Service: Fallo durante la modificación del apellido de cliente", e); }
    }


    @Override
    public void eliminarCliente(int id) throws ServiceException {
        try {
            if (buscarClienteID(id) == null){ return ;}

            clienteDAO.eliminarCliente(id);

        } catch (DAOException e) { throw new ServiceException("Error Service: Fallo durante la eliminación del cliente", e); }
    }

    @Override
    public void agregarEmail(String direccionEmail, int idCliente) throws ServiceException {

        try {
            connection.setAutoCommit(false);

            try {
                buscarClienteID(idCliente); // Verifica que el ID del cliente exista en la tabla cliente
                if (clienteDAO.obtenerNombreEmails().contains(direccionEmail)) { throw new ServiceException("El email ya está registrado en la base de  datos"); }

                clienteDAO.agregarEmail(new Email(direccionEmail, idCliente));
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
    public void modificarEmail(String nuevoEmail, int idEmail) throws ServiceException {

        try {
            connection.setAutoCommit(false);

            try {
                Email email = buscarEmail(idEmail);
                if (clienteDAO.obtenerNombreEmails().contains(nuevoEmail)) { throw new ServiceException("El email ya está registrado en la base de  datos"); }

                email.setEmail(nuevoEmail);
                clienteDAO.modificarEmail(email);

                connection.commit();

            } catch (SQLException | DAOException e) {
                connection.rollback();
                throw new ServiceException("Error Service: Fallo durante la transacción de update email", e);

            } finally { connection.setAutoCommit(true); }

        } catch ( SQLException e) { throw new ServiceException("Error al configurar la transacción", e); }
    }

    @Override
    public Email buscarEmail(int id) throws ServiceException {
        try{
            Email email = clienteDAO.buscarEmail(id);
            if (email == null){ throw new ServiceException("No existe ningún registro de email con el id: " + id); }

            return email;

        } catch (DAOException e) { throw new ServiceException("Error Service: Fallo durante la busqueda del email con id: "+ id, e); }

    }

    @Override
    public void cambiarIdClienteEmail(int nuevoClienteId, int id) throws ServiceException {
        try {

            connection.setAutoCommit(false);

            try {
                Email email = buscarEmail(id);
                buscarClienteID(nuevoClienteId);

                email.setIdCliente(nuevoClienteId);
                clienteDAO.cambiarIdClienteEmail(email);

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
            List<Email> emailsIdInt = clienteDAO.verEmailsPorCliente(id);
            if (emailsIdInt.isEmpty()) { throw new ServiceException("No hay email registrados en la base de datos para el cliente con id: " + id); }

            List<Email> emailsCliente = new ArrayList<>();

            for (Email email: emailsIdInt){
                Cliente cliente = clienteDAO.buscarClienteID(email.getIdCliente());

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

            clienteDAO.eliminarEmail(id);

        } catch (DAOException e) { throw new ServiceException("Error Service: Fallo durante la eliminación de email con id: " + id, e); }

    }
}