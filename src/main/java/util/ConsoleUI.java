package util;

import javax.swing.JOptionPane;

public class ConsoleUI {

    public static boolean continuarGestorPedidos(String mensaje, String titulo){

        int opc = JOptionPane.showConfirmDialog(
                null,
                mensaje,
                titulo,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        return opc == 0;
    }

    public static int ingresarNumero(String mensaje, String titulo){
        String numero = JOptionPane.showInputDialog(null, mensaje, titulo, JOptionPane.QUESTION_MESSAGE);

        return InputValidator.verificarNumeroIngresado(numero);
    }
}