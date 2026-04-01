package util;

import exception.ValidationException;
import model.Cliente;

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

    public static Cliente crearCliente() throws ValidationException {
        String nombre = ingresarPalabra("Ingrese el nombre del cliente: ", "Crear Cliente");
        String apellido = ingresarPalabra("Ingrese el apellido del cliente: ", "Crear Cliente");

        return new Cliente(nombre, apellido);

    }

    private static String ingresarPalabra(String mensaje, String titulo) throws ValidationException {
        String palabra = JOptionPane.showInputDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE );

        return InputValidator.verificarPalabra(palabra.trim());
    }
}