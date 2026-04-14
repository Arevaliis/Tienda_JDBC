package service.interfaces;

import exception.ServiceException;
import model.Email;
import java.util.List;

/**
 * Interfaz que define las operaciones básicas de gestión de emails.
 */
public interface IEmailService {
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
