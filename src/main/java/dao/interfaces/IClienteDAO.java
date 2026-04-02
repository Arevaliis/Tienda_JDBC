package dao.interfaces;

import exception.DAOException;
import model.Cliente;
import model.Email;

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

    /**
     * Inserta un nuevo email en la base de datos asociado a un cliente.
     *
     * @param email objeto Email que contiene la dirección y el id del cliente
     * @throws DAOException si ocurre un error durante la inserción
     */
    void agregarEmail(Email email) throws DAOException;

    /**
     * Obtiene todas las direcciones de email registradas en la base de datos.
     *
     * @return lista de emails en formato String
     * @throws DAOException si ocurre un error durante la consulta
     */
    List<String> obtenerNombreEmails() throws DAOException;

    /**
     * Modifica la dirección de un email existente en la base de datos.
     *
     * @param email objeto Email con el id y la nueva dirección
     * @throws DAOException si ocurre un error durante la actualización o el email no existe
     */
    void modificarEmail(Email email) throws DAOException;

    /**
     * Busca un email en la base de datos por su identificador.
     *
     * @param id identificador único del email
     * @return objeto Email encontrado
     * @throws DAOException si ocurre un error durante la consulta
     */
    Email buscarEmail(int id) throws DAOException;

    /**
     * Cambia el cliente asociado a un email existente.
     *
     * @param email objeto Email que contiene el id del email y el nuevo id del cliente
     * @throws DAOException si ocurre un error durante la actualización
     */
    void cambiarIdClienteEmail(Email email) throws DAOException;

    /**
     * Obtiene todos los emails asociados a un cliente específico.
     *
     * @param id identificador del cliente
     * @return lista de objetos Email asociados al cliente
     * @throws DAOException si ocurre un error durante la consulta
     */
    List<Email> verEmailsPorCliente(int id) throws DAOException;

    /**
     * Elimina un email de la base de datos por su identificador.
     *
     * @param id identificador del email a eliminar
     * @throws DAOException si ocurre un error durante la eliminación o el email no existe
     */
    void eliminarEmail(int id) throws DAOException;

}
