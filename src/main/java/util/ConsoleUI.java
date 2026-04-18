package util;

import exception.ValidationException;
import javax.swing.JOptionPane;

/**
 * Clase utilitaria para la interacción con el usuario mediante cuadros de diálogo (JOptionPane).
 */
public class ConsoleUI {

    /**
     * Muestra una ventana emergente de SI/NO al usuario para continuar en la aplicación
     *
     * @param mensaje mensaje que se muestra al usuario
     * @param titulo título de la ventana emergente
     * @return True si la opción elegida es igual a 'SÍ' o False si la opción elegida es 'NO'
     */
    public static boolean confirmarContinuacion(String mensaje, String titulo){

        int opc = JOptionPane.showConfirmDialog(
                null,
                mensaje,
                titulo,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        return opc == 0;
    }

    /**
     * Solicita al usuario que ingrese un número mediante una ventana emergente
     *
     * @param mensaje mensaje que se muestra al usuario
     * @param titulo título de la ventana emergente
     * @return numero ingresado
     */
    public static int ingresarNumero(String mensaje, String titulo){
        String numero = JOptionPane.showInputDialog(null, mensaje, titulo, JOptionPane.QUESTION_MESSAGE);

        return InputValidator.verificarNumeroIngresado(numero);
    }

    /**
     * Solicita al usuario una palabra mediante una ventana de entrada y la valida.
     *
     * @param mensaje texto mostrado al usuario en el cuadro de diálogo
     * @param titulo título de la ventana de entrada
     * @return palabra introducida y validada por el sistema
     * @throws ValidationException si la palabra es nula, vacía o no cumple las reglas de validación
     */
    public static String ingresarPalabra(String mensaje, String titulo) throws ValidationException {
        String palabra = JOptionPane.showInputDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
        return InputValidator.verificarPalabra(palabra.trim());
    }

    /**
     * Muestra un menú de opciones y devuelve la opción seleccionada por el usuario.
     *
     * @param opciones array de opciones disponibles
     * @param mensaje texto que identifica el menú mostrado
     * @return índice de la opción seleccionada por el usuario, o -1 si cancela
     */
    public static int seleccionarOpcion(String[] opciones, String mensaje) {

        return JOptionPane.showOptionDialog(
                null,
                "Seleciona una opción",
                mensaje,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );
    }

    /**
     * Solicita al usuario que ingrese un email mediante
     *
     * @return email validado introducido por el usuario
     * @throws ValidationException si el campo está vacío o el formato del email no es válido
     */
    public static String ingresarEmail() throws ValidationException {
        String palabra = JOptionPane.showInputDialog(null, "Ingrese el email del cliente: ", "Ingresar Email", JOptionPane.INFORMATION_MESSAGE);
        return InputValidator.verificarEmail(palabra.trim());
    }

    /**
     * Solicita al usuario un número decimal
     *
     * @param mensaje texto que se muestra al usuario para indicar qué debe introducir
     * @param titulo  título de la ventana de entrada
     * @return número decimal validado introducido por el usuario
     * @throws NumberFormatException si el valor introducido no es un número decimal válido
     */
    public static double ingresarDecimal(String mensaje, String titulo) {
        String decimal = JOptionPane.showInputDialog(null, mensaje, titulo, JOptionPane.QUESTION_MESSAGE);
        return InputValidator.verificarDecimalIngresado(decimal);
    }
}