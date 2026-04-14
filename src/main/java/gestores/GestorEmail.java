package gestores;

import exception.ValidationException;
import model.Email;
import service.impl.ClienteService;
import service.impl.EmailService;
import util.ConsoleUI;
import util.Mensajes;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.util.List;

public class GestorEmail {

    /**
     * Muestra el menú de opciones relacionadas con la gestión de emails
     * y ejecuta la acción correspondiente según la opción elegida.
     *
     * @param connection conexión a la base de datos
     * @param clientesService servicio de clientes utilizado para realizar las operaciones
     * @throws ValidationException si ocurre un error en la validación de los datos introducidos
     */
    public static void menuEmail(Connection connection, ClienteService clientesService) throws ValidationException {
        EmailService emailService = new EmailService(connection, clientesService);

        int opc = ConsoleUI.ingresarNumero(Mensajes.MENU_EMAIL, "Menu Email");

        switch (opc) {
            case 1 -> agregarEmail(emailService);
            case 2 -> modificarEmail(emailService);
            case 3 -> cambiarIdClienteEmail(emailService);
            case 4 -> verEmailPorCliente(emailService);
            case 5 -> eliminarEmail(emailService);

            case 0, -1 -> {}
            default -> throw new IllegalArgumentException("Debe ingresar un número comprendido entre 0 y 5");
        }
    }

    /**
     * Solicita los datos necesarios y agrega un nuevo email a un cliente.
     *
     * @param emailService servicio de email utilizado para realizar la operación
     * @throws ValidationException si el email introducido no es válido
     */
    private static void agregarEmail(EmailService emailService) throws ValidationException {
        String email = ConsoleUI.ingresarEmail();
        int idCliente = ConsoleUI.ingresarNumero("Ingrese el id del cliente: ", "Ingresar Email");
        if (idCliente == -1){ return;}

        emailService.agregarEmail(email, idCliente);
        JOptionPane.showMessageDialog( null,  "Email agregado con éxito",  "Ingresar Email",  JOptionPane.INFORMATION_MESSAGE);

    }

    /**
     * Solicita los datos necesarios y modifica un email existente.
     *
     * @param emailService servicio de email utilizado para realizar la operación
     * @throws ValidationException si el email introducido no es válido
     */
    private static void modificarEmail(EmailService emailService) throws ValidationException {
        String email = ConsoleUI.ingresarEmail();
        int id = ConsoleUI.ingresarNumero("Ingrese el id del email: ", "Modificar Email");
        if (id == -1){ return;}

        emailService.modificarEmail(email, id);
        JOptionPane.showMessageDialog( null,  "Email modificado con éxito",  "Modificar Email",  JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Cambia el cliente asociado a un email existente.
     *
     * @param emailService servicio de email utilizado para realizar la operación
     */
    private static void cambiarIdClienteEmail(EmailService emailService) {
        int idCliente = ConsoleUI.ingresarNumero("Ingrese el id del cliente: ", "Modificar Email");
        if (idCliente == -1){ return;}

        int idEmail = ConsoleUI.ingresarNumero("Ingrese el id del email: ", "Modificar Email");
        if (idEmail == -1){ return;}

        emailService.cambiarIdClienteEmail(idCliente, idEmail);
        JOptionPane.showMessageDialog( null,  "Cliente email modificado con éxito",  "Modificar Email",  JOptionPane.INFORMATION_MESSAGE);

    }

    /**
     * Muestra todos los emails asociados a un cliente específico.
     *
     * @param emailService servicio de email utilizado para realizar la operación
     */
    private static void verEmailPorCliente(EmailService emailService) {

        int id = ConsoleUI.ingresarNumero("Ingrese el id del cliente: ", "Ver Emails Cliente");
        if (id == -1){ return;}

        List<Email> emails = emailService.verEmailsPorCliente(id);
        List<String> mensaje = emails.stream()
                .map(Email::toString)
                .toList();

        JOptionPane.showMessageDialog( null,  String.join("\n", mensaje),  "Emails Cliente",  JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Elimina un email de la base de datos a partir de su identificador.
     *
     * @param emailService servicio de email utilizado para realizar la operación
     */
    private static void eliminarEmail(EmailService emailService) {
        int id = ConsoleUI.ingresarNumero("Ingrese el id del email: ", "Modificar Email");
        if (id == -1){ return;}

        emailService.eliminarEmail(id);

        JOptionPane.showMessageDialog( null,  "Email eliminado con éxito",  "Eliminar Email",  JOptionPane.INFORMATION_MESSAGE);
    }
}