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

        if (opc == null){ return 4; }

        if (opc.isEmpty()){ throw new NumberFormatException("Debe ingresar una opción. No puede dejar el campo vacío."); }
        if (!opc.matches("^[0-9]+$")) { throw new NumberFormatException("La opción ingresada debe ser un número entero válido."); }
        if (Integer.parseInt(opc) <= 0) { throw new NumberFormatException("Debe ingresar un valor mayor que 0"); }

        return Integer.parseInt(opc);
    }


    public static String verificarPalabra(String palabra) throws ValidationException {

        if (palabra.isEmpty()){ throw new ValidationException("Debe ingresar una palabra. No puede dejar el campo vacío."); }
        if (!palabra.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")) { throw new ValidationException("La palabra ingresada no es válida. Solo puede contener letras."); }
        if (palabra.length() < 3){ throw new ValidationException("Debe tener al menos 3 caracteres"); }

        return palabra.substring(0, 1).toUpperCase() + palabra.substring(1);
    }
}