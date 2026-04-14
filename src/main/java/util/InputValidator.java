package util;

import exception.ValidationException;

/**
 * Clase utilitaria para comprobar que los valores ingresados por el usuario son adecuados al tipo de dato que se le solicita.
 */
public class InputValidator {

    /**
     * Verifica que el número ingresado por el usuario es un número válido. Comprueba que no se Null, no este vacío, solo contiene números
     * y además, no puede ser menor a 1, en caso de que una de estas condiciones se cumpla, lanzara una excepción de tipo {@NumberFormatException}
     *
     * @param opc número ingresado por el usuario
     * @return número si es válido si no lanzará una excepción de tipo {@NumberFormatException}
     */
    public static int verificarNumeroIngresado(String opc) {

        if (opc == null){ return -1; }
        if (opc.isEmpty()){ throw new NumberFormatException("Debe ingresar una opción. No puede dejar el campo vacío."); }
        if (!opc.matches("^[0-9]+$")) { throw new NumberFormatException("La opción ingresada debe ser un número entero válido."); }

        int numero = Integer.parseInt(opc);
        if (numero < 0) { throw new NumberFormatException("Debe ingresar un valor mayor que 0"); }

        return numero;
    }

    /**
     * Valida que una cadena sea una palabra válida. No puede estar vacía, solo puede contener letras (incluyendo acentos y ñ)
     * y espacios, y por último, debe tener al menos 3 caracteres
     * <p>
     * Además, devuelve la palabra con la primera letra en mayúscula.
     *
     * @param palabra texto a validar
     * @return palabra validada con la primera letra en mayúscula y el resto en minúscula
     * @throws ValidationException si la palabra está vacía, contiene caracteres no válidos o tiene menos de 3 caracteres
     */
    public static String verificarPalabra(String palabra) throws ValidationException {

        if (palabra.isEmpty()){ throw new ValidationException("Debe ingresar una palabra. No puede dejar el campo vacío."); }
        if (!palabra.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")) { throw new ValidationException("La palabra ingresada no es válida. Solo puede contener letras."); }
        if (palabra.length() < 3){ throw new ValidationException("Debe tener al menos 3 caracteres"); }

        return palabra.substring(0, 1).toUpperCase() + palabra.substring(1).toLowerCase();
    }

    /**
     * Valida que una cadena tenga formato de email correcto. No puede estar vacío y
     * debe cumplir un formato básico (texto@texto.dominio)
     *
     * @param email dirección de correo electrónico a validar
     * @return email validado
     * @throws ValidationException si el email está vacío o no cumple el formato requerido
     */
    public static String verificarEmail(String email) throws ValidationException {

        if (email.isEmpty()){throw new ValidationException("Debe ingresar un email. No puede dejar el campo vacío.");}
        if (!email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")){throw new ValidationException("El formato del email no es correcto.");}

        return email;
    }

    /**
     * Valida que una cadena represente un número decimal válido y lo convierte a tipo double. No puede estar vacío, debe ser decimal y
     * debe ser mayor a 0.00
     *
     * @param numero cadena que representa el número decimal a validar
     * @return valor convertido a tipo double si es válido
     * @throws NumberFormatException si el valor está vacío, tiene formato inválido o es negativo
     */
    public static double verificarDecimalIngresado(String numero) {

        if (numero.isEmpty()) { throw new NumberFormatException("Debe ingresar un número decimal. No puede estar vacío."); }

        double decimal = Double.parseDouble(numero);
        if (decimal < 0) { throw new NumberFormatException("El valor no puede ser negativo."); }

        if (!numero.matches("^[0-9]+(\\.[0-9]+)?$")) { throw new NumberFormatException("Debe ingresar un número decimal válido."); }

        return decimal;
    }
}