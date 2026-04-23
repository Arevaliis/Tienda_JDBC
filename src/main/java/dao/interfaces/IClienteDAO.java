package dao.interfaces;

import exception.DAOException;
import model.Cliente;
import java.util.List;

/**
 * Interfaz DAO que define las operaciones de acceso a datos para la entidad Cliente.
 */
public interface IClienteDAO {

    /**
     * Inserta un nuevo cliente en la base de datos.
     *
     * @param cliente objeto Cliente a insertar
     * @throws DAOException si ocurre un error durante la inserción en base de datos
     */
    void insertarCliente(Cliente cliente) throws DAOException;

    /**
     * Busca un cliente en la base de datos por su identificador.
     *
     * @param id identificador único del cliente
     * @return cliente encontrado
     * @throws DAOException si ocurre un error en la consulta o el cliente no existe
     */
    Cliente buscarClienteID(int id) throws DAOException;

    /**
     * Obtiene todos los clientes almacenados en la base de datos con sus emails o null si no tienen.
     *
     * @return lista de clientes
     * @throws DAOException si ocurre un error durante la consulta
     */
    List<Cliente> listarClientes() throws DAOException;

    /**
     * Actualiza un cliente en la base de datos.
     *
     * @param cliente objeto Cliente que contiene el campo modificado
     * @throws DAOException si ocurre un error durante la actualización o el cliente no existe
     */
    void actualizarCliente(Cliente cliente) throws DAOException;


    /**
     * Elimina un cliente de la base de datos por su identificador. No se puede eliminar aquellos clientes que estén referenciados en otra tabla como FK.
     *
     * @param id identificador del cliente a eliminar
     * @throws DAOException si ocurre un error durante la eliminación o el cliente no existe
     */
    void eliminarCliente(int id) throws DAOException;
}
