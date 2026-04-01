package service.impl;

import dao.impl.ClienteDAO;
import exception.DAOException;
import exception.ServiceException;
import model.Cliente;
import service.interfaces.IClienteService;

import java.util.List;

public class ClienteService implements IClienteService {
    private final ClienteDAO clienteDAO;

    public ClienteService(ClienteDAO clienteDAO) { this.clienteDAO = clienteDAO; }

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
            cliente = clienteDAO.buscarClienteID(id);
            cliente.setNombre(nombre);
            clienteDAO.modificarNombreCliente(cliente);

        } catch (DAOException e) { throw new ServiceException("Error Service: Fallo durante la modificación del nombre de cliente", e); }
    }

    @Override
    public void modificarApellidoCliente(String apellido, int id) throws ServiceException {
        Cliente cliente;

        try{
            cliente = clienteDAO.buscarClienteID(id);
            cliente.setApellido(apellido);
            clienteDAO.modificarApellidoCliente(cliente);

        } catch (DAOException e) { throw new ServiceException("Error Service: Fallo durante la modificación del apellido de cliente", e); }
    }


    @Override
    public void eliminarCliente(int id) throws ServiceException {
        try {
            clienteDAO.eliminarCliente(id);

        } catch (DAOException e) { throw new ServiceException("Error Service: Fallo durante la eliminación del cliente", e); }
    }
}