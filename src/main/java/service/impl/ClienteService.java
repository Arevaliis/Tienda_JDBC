package service.impl;

import dao.impl.ClienteDAO;
import exception.DAOException;
import exception.ServiceException;
import model.Cliente;
import service.interfaces.IClienteService;

import java.util.ArrayList;
import java.util.List;

public class ClienteService implements IClienteService {
    private final ClienteDAO clienteDAO;

    public ClienteService(ClienteDAO clienteDAO) { this.clienteDAO = clienteDAO; }

    @Override
    public void insertarCliente(Cliente cliente) throws ServiceException {
        try {
            clienteDAO.insertarCliente(cliente);
        } catch (DAOException e) {
            throw new ServiceException("Error Service: Fallo durante la inserción del cliente", e);
        }
    }

    @Override
    public Cliente buscarClienteID(int id) throws ServiceException {
        Cliente cliente;

        try{
            cliente = clienteDAO.buscarClienteID(id);

            if (cliente == null){throw new ServiceException("No existe ningún cliente con el id " + id);}

            return cliente;

        } catch (DAOException e) {
            throw new ServiceException("Error Service: Fallo durante select de cliente", e);
        }
    }

    @Override
    public List<Cliente> mostrarTodosClientes() throws ServiceException {
        return new ArrayList<>();
    }

    @Override
    public void modificarNombreCliente(Cliente cliente) throws ServiceException {

    }

    @Override
    public void modificarApellidoCliente(Cliente cliente) throws ServiceException {

    }

    @Override
    public void eliminarCliente(int id) throws ServiceException {

    }
}
