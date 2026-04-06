package service.interfaces;

import exception.ServiceException;
import model.Cliente;
import model.Email;

import java.util.List;

/**
 * Interfaz que define las operaciones básicas de gestión de clientes.
 */
public interface IClienteService {

    /**
     * Inserta un nuevo cliente en el sistema.
     *
     * @param nombre nombre del cliente a insertar
     * @param apellido apellido del cliente a insertar
     * @throws ServiceException si ocurre un error en la capa de servicio o persistencia
     */
    void insertarCliente(String nombre, String apellido) throws ServiceException;

    /**
     * Busca un cliente en el sistema a partir de su identificador.
     *
     * @param id identificador único del cliente
     * @return cliente encontrado
     * @throws ServiceException si ocurre un error durante la búsqueda o el cliente no existe
     */
    Cliente buscarClienteID(int id) throws ServiceException;

    /**
     * Obtiene una lista con todos los clientes registrados en el sistema.
     *
     * @return lista de clientes
     * @throws ServiceException si ocurre un error al recuperar los datos
     */
    List<Cliente> mostrarTodosClientes() throws ServiceException;

    /**
     * Modifica el nombre de un cliente existente.
     *
     * @param nombre nuevo nombre del cliente
     * @param id identificador del cliente a modificar
     * @throws ServiceException si ocurre un error durante la actualización o el cliente no existe
     */
    void modificarNombreCliente(String nombre, int id) throws ServiceException;

    /**
     * Modifica el apellido de un cliente existente.
     *
     * @param apellido nuevo apellido del cliente
     * @param id identificador del cliente a modificar
     * @throws ServiceException si ocurre un error durante la actualización o el cliente no existe
     */
    void modificarApellidoCliente(String apellido, int id) throws ServiceException;

    /**
     * Elimina un cliente del sistema a partir de su identificador.
     *
     * @param id identificador del cliente a eliminar
     * @throws ServiceException si ocurre un error durante la eliminación o el cliente no existe
     */
    void eliminarCliente(int id) throws ServiceException;

    /**
     * Agrega un nuevo email a un cliente existente.
     *
     * @param direccionEmail dirección de email a registrar
     * @param idCliente identificador del cliente al que se asociará el email
     * @throws ServiceException si el cliente no existe, el email ya está registrado
     *                          o ocurre un error durante la operación
     */
    void agregarEmail(String direccionEmail, int idCliente) throws ServiceException;

    /**
     * Modifica la dirección de un email existente.
     *
     * @param nuevoEmail nueva dirección de email
     * @param idEmail identificador del email a modificar
     * @throws ServiceException si el email no existe, el nuevo email ya está en uso
     *                          o ocurre un error durante la operación
     */
    void modificarEmail(String nuevoEmail, int idEmail) throws ServiceException;

    /**
     * Busca un email por su identificador.
     *
     * @param id identificador del email
     * @return objeto Email encontrado
     * @throws ServiceException si el email no existe o ocurre un error en la búsqueda
     */
    Email buscarEmail(int id) throws ServiceException;

    /**
     * Cambia el cliente asociado a un email existente.
     *
     * @param nuevoClienteId identificador del nuevo cliente
     * @param idCliente identificador del email a modificar
     * @throws ServiceException si el email o el cliente no existen
     *                          o ocurre un error durante la operación
     */
    void cambiarIdClienteEmail(int nuevoClienteId, int idCliente) throws ServiceException;

    /**
     * Obtiene todos los emails asociados a un cliente específico.
     *
     * @param id identificador del cliente
     * @return lista de emails asociados al cliente
     * @throws ServiceException si el cliente no existe o ocurre un error en la consulta
     */
    List<Email> verEmailsPorCliente(int id) throws ServiceException;

    /**
     * Elimina un email de la base de datos.
     *
     * @param id identificador del email a eliminar
     * @throws ServiceException si el email no existe o ocurre un error durante la eliminación
     */
    void eliminarEmail(int id) throws ServiceException;
}