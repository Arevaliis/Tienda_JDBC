package service.interfaces;

import exception.ServiceException;
import model.Cliente;

import java.util.List;

public interface IClienteService {

    void insertarCliente(Cliente cliente) throws ServiceException;
    Cliente buscarClienteID(int id) throws ServiceException;
    List<Cliente> mostrarTodosClientes() throws ServiceException;
    void modificarNombreCliente(Cliente cliente) throws ServiceException;
    void modificarApellidoCliente(Cliente cliente) throws ServiceException;
    void eliminarCliente(int id) throws ServiceException;
}
