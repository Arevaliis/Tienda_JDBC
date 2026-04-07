package exception;

/**
 * Excepción personalizada para la capa DAO (Data Access Object).
 * <p>
 * Se utiliza para encapsular errores relacionados con el acceso a datos,
 * como fallos en base de datos, consultas SQL, conexiones, etc.
 * </p>
 */
public class DAOException extends RuntimeException {

    /**
     * Crea una nueva excepción DAO con un mensaje descriptivo.
     *
     * @param message mensaje que describe el error ocurrido
     */
    public DAOException(String message) {
        super(message);
    }

    /**
     * Crea una nueva excepción DAO con un mensaje descriptivo y la causa original.
     *
     * @param message mensaje que describe el error ocurrido
     * @param cause excepción original que provocó este error
     */
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}