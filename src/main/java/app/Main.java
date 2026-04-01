package app;

import gestores.GestorCliente;
import util.ConsoleUI;
import util.Mensajes;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        boolean seguir = true;

        while (seguir) {
            try {

                if (ejecutarOpcion() == 4 ) { return; }
                seguir = ConsoleUI.continuarGestorPedidos("¿Desea seguir en el gestor? S/N: ", "Seguir Menu Principal");

            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

            } finally {
                if (!seguir){
                    JOptionPane.showMessageDialog(null, "Saliendo", "Salir", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private static int ejecutarOpcion() {
        int opc = ConsoleUI.ingresarNumero(Mensajes.MENSAJE_MENU_INICIAL, "Menu Inicial");

        switch (opc){
            case 1 -> GestorCliente.ejecutarGestorClientes();
            case 2 -> System.out.println("Gestor Productos"); // TODO IMPLEMENTAR
            case 3 -> System.out.println("Gestor Pedidos"); // TODO IMPLEMENTAR

            case 4 -> JOptionPane.showMessageDialog(null, "Saliendo", "Salir", JOptionPane.INFORMATION_MESSAGE);
            default -> throw new IllegalArgumentException("Debe ingresar un número comprendido entre 1 y 4");
        }

        return opc;
    }
}