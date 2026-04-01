package util;

public class InputValidator {

    public static int verificarNumeroIngresado(String opc) {

        if (opc == null){ return 4; }

        if (opc.isEmpty()){ throw new NumberFormatException("Debe ingresar una opción. No puede dejar el campo vacío."); }
        if (opc.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")) { throw new NumberFormatException("La opción ingresada debe ser un número entero válido."); }
        if (Integer.parseInt(opc) <= 0) { throw new NumberFormatException("Debe ingresar un valor mayor que 0"); }

        return Integer.parseInt(opc);
    }
}