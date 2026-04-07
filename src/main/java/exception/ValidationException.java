package exception;

/**
 * Excepción personalizada para errores de validación.
 * <p>
 * Se utiliza cuando los datos proporcionados por el usuario o por el sistema
 * no cumplen las reglas de validación definidas (por ejemplo, campos vacíos,
 * formatos incorrectos, valores fuera de rango, etc.).
 * </p>
 */
public class ValidationException extends RuntimeException {

    /**
     * Crea una nueva excepción de validación con un mensaje descriptivo.
     *
     * @param message mensaje que describe el error de validación
     */
    public ValidationException(String message) {
        super(message);
    }

    /**
     * Crea una nueva excepción de validación con un mensaje descriptivo
     * y la causa original.
     *
     * @param message mensaje que describe el error de validación
     * @param cause excepción original que provocó este error
     */
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}