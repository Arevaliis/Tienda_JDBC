package dao.interfaces;

import exception.DAOException;
import model.Cliente;

public interface IClienteDAO {

    void insertarCliente(Cliente cliente) throws DAOException;
    void buscarClienteID(int id) throws DAOException;
    void mostrarTodosClientes() throws DAOException;
    void modificarNombreCliente(Cliente cliente) throws DAOException;
    void modificarApellidoCliente(Cliente cliente) throws DAOException;
    void eliminarCliente(int id) throws DAOException;

}
