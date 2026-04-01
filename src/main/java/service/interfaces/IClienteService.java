package service.interfaces;

import exception.ServiceException;
import model.Cliente;

public interface IClienteService {

    void insertarCliente(Cliente cliente) throws ServiceException;
    void buscarClienteID(int id) throws ServiceException;
    void mostrarTodosClientes() throws ServiceException;
    void modificarNombreCliente(Cliente cliente) throws ServiceException;
    void modificarApellidoCliente(Cliente cliente) throws ServiceException;
    void eliminarCliente(int id) throws ServiceException;
}
