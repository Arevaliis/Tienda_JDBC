package app;

import gestores.GestorCliente;
import gestores.GestorProducto;
import util.ConsoleUI;
import util.Mensajes;

import javax.swing.JOptionPane;

/**
 * Clase principal de la aplicación.
 * Se encarga de iniciar el sistema y controlar el flujo del menú principal.
 */
public class Main {

    /**
     * Método principal que inicia la ejecución del programa.
     * Controla el bucle principal del sistema y gestiona la continuidad del usuario.
     */
    public static void main(String[] args) {
        boolean seguir = true;

        while (seguir) {
            try {

                if (ejecutarOpcion() <= 0 ) { return; }
                seguir = ConsoleUI.confirmarContinuacion("¿Desea seguir en el gestor? S/N: ", "Seguir Menu Principal");

            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

            } finally {
                if (!seguir){
                    JOptionPane.showMessageDialog(null, "Saliendo", "Salir", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    /**
     * Solicita al usuario una opción del menú principal y ejecuta la acción correspondiente.
     *
     * @return el número de la opción seleccionada por el usuario
     * @throws IllegalArgumentException si la opción no está entre 0-3
     */
    private static int ejecutarOpcion() {
        int opc = ConsoleUI.ingresarNumero(Mensajes.MENU_INICIAL, "Menu Inicial");

        switch (opc){
            case 1 -> GestorCliente.ejecutarGestorClientes();
            case 2 -> GestorProducto.ejecutarMenuProducto();
            case 3 -> System.out.println("Gestor Pedidos"); // TODO IMPLEMENTAR

            case 0, -1-> JOptionPane.showMessageDialog(null, "Saliendo", "Salir", JOptionPane.INFORMATION_MESSAGE);
            default -> throw new IllegalArgumentException("Debe ingresar un número comprendido entre 0-3");
        }

        return opc;
    }
}