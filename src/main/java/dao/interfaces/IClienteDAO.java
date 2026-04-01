package dao.interfaces;

import exception.DAOException;
import model.Cliente;

import java.util.List;

public interface IClienteDAO {

    void insertarCliente(Cliente cliente) throws DAOException;
    Cliente buscarClienteID(int id) throws DAOException;
    List<Cliente> mostrarTodosClientes() throws DAOException;
    void modificarNombreCliente(Cliente cliente) throws DAOException;
    void modificarApellidoCliente(Cliente cliente) throws DAOException;
    void eliminarCliente(int id) throws DAOException;

}
