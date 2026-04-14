package service.interfaces;

import exception.ServiceException;
import model.Cliente;
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
     * @return lista de clientes con email o null si no tienen email.
     * @throws ServiceException si ocurre un error al recuperar los datos
     */
    List<Cliente> listarClientes() throws ServiceException;

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

}