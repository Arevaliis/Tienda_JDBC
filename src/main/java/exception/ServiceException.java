package exception;

/**
 * Excepción personalizada para la capa de servicios (Service).
 * <p>
 * Se utiliza para representar errores de lógica de negocio o fallos
 * en la capa intermedia de la aplicación. Al extender de RuntimeException,
 * no es obligatorio capturarla o declararla.
 * </p>
 */
public class ServiceException extends RuntimeException {

    /**
     * Crea una nueva excepción de servicio con un mensaje descriptivo.
     *
     * @param message mensaje que describe el error ocurrido
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * Crea una nueva excepción de servicio con un mensaje descriptivo y la causa original.
     *
     * @param message mensaje que describe el error ocurrido
     * @param cause excepción original que provocó este error
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}