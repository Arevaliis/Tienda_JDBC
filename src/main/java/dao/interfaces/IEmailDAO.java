package dao.interfaces;

import exception.DAOException;
import model.Email;

import java.util.List;

/**
 * Interfaz DAO que define las operaciones de acceso a datos para la entidad Email.
 */
public interface IEmailDAO {

    /**
     * Inserta un nuevo email en la base de datos asociado a un cliente.
     *
     * @param email objeto Email que contiene la dirección y el id del cliente
     * @throws DAOException sí ocurre un error durante la inserción
     */
    void agregarEmail(Email email) throws DAOException;

    /**
     * Obtiene todas las direcciones de email registradas en la base de datos. Permite comprobar que el
     * email que vamos a ingresar no se encuentra ya registrado en la base de datos.
     *
     * @return lista de emails en formato String
     * @throws DAOException si ocurre un error durante la consulta
     */
    List<String> obtenerNombreEmails() throws DAOException;

    /**
     * Modifica la dirección de un email existente en la base de datos.
     *
     * @param email objeto Email con el ID y la nueva dirección
     * @throws DAOException si ocurre un error durante la actualización o el email no existe
     */
    void actualizarEmail(Email email) throws DAOException;

    /**
     * Busca un email en la base de datos por su identificador.
     *
     * @param id identificador único del email
     * @return objeto Email encontrado
     * @throws DAOException si ocurre un error durante la consulta
     */
    Email buscarEmail(int id) throws DAOException;

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
