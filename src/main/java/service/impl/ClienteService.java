package service.impl;

import dao.impl.ClienteDAO;
import exception.DAOException;
import exception.ServiceException;
import model.Cliente;
import service.interfaces.IClienteService;

public class ClienteService implements IClienteService {
    private final ClienteDAO clienteDAO;

    public ClienteService(ClienteDAO clienteDAO) { this.clienteDAO = clienteDAO; }

    @Override
    public void insertarCliente(Cliente cliente) throws ServiceException {
        try {
            clienteDAO.insertarCliente(cliente);
        } catch (DAOException e) {
            throw new ServiceException("Error Service: Fallo durante la inserción del cliente");
        }
    }

    @Override
    public void buscarClienteID(int id) throws ServiceException {

    }

    @Override
    public void mostrarTodosClientes() throws ServiceException {

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
