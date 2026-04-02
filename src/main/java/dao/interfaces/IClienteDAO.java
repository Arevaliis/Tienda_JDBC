package dao.interfaces;

import exception.DAOException;
import model.Cliente;
import model.Email;
import model.Telefono;

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
     * Obtiene todos los clientes almacenados en la base de datos.
     *
     * @return lista de clientes
     * @throws DAOException si ocurre un error durante la consulta
     */
    List<Cliente> mostrarTodosClientes() throws DAOException;

    /**
     * Actualiza el nombre de un cliente en la base de datos.
     *
     * @param cliente objeto Cliente que contiene el id y el nuevo nombre
     * @throws DAOException si ocurre un error durante la actualización o el cliente no existe
     */
    void modificarNombreCliente(Cliente cliente) throws DAOException;

    /**
     * Actualiza el apellido de un cliente en la base de datos.
     *
     * @param cliente objeto Cliente que contiene el ID y el nuevo apellido
     * @throws DAOException si ocurre un error durante la actualización o el cliente no existe
     */
    void modificarApellidoCliente(Cliente cliente) throws DAOException;

    /**
     * Elimina un cliente de la base de datos por su identificador.
     *
     * @param id identificador del cliente a eliminar
     * @throws DAOException si ocurre un error durante la eliminación o el cliente no existe
     */
    void eliminarCliente(int id) throws DAOException;

    // EMAIL
    void agregarEmail(Email email) throws DAOException;

    List<String>  obtenerEmails() throws DAOException;

    void modificarEmail(Email email) throws DAOException;

    Email buscarEmail(int id) throws DAOException;

    void cambiarIdClienteEmail(Email email) throws DAOException;

    List<Email> verEmailsPorCliente(int id) throws DAOException;

    void eliminarEmail(int id) throws DAOException;

}
