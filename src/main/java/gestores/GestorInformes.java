package gestores;

import exception.ServiceException;
import exception.ValidationException;
import model.Producto;
import model.ProductoInforme;
import model.Cliente;
import model.ClienteInforme;
import service.impl.InformeService;
import util.ConsoleUI;
import util.DatabaseConnection;
import util.Mensajes;
import util.TablaViewer;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Clase encargada de gestionar la interacción del usuario con el módulo de informes.
 */
public class GestorInformes {

    /**
     * Inicia el gestor de informes
     * <p>
     * Establece la conexión con la base de datos y ejecuta el menú en bucle hasta que el usuario decida salir.
     * Maneja excepciones tanto de acceso a datos {@SQLException} como de lógica de negocio {@ServiceException}.
     */
    public static void ejecutarGestorInformes(){

        boolean seguir = true;

        try (Connection connection = DatabaseConnection.getConnection()) {
            InformeService informeService = new InformeService(connection);

            while (seguir) {
                try {
                    if (ejecutarOpcion(informeService) <= 0) { return; }
                    seguir = ConsoleUI.confirmarContinuacion("¿Desea seguir en la sección de informes?", "Seguir Menu Informes");

                } catch (IllegalArgumentException | ServiceException | ValidationException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

                } catch (NullPointerException ignored) {
                    seguir = ConsoleUI.confirmarContinuacion("¿Desea seguir en la sección de informes?", "Seguir Menu Informes");
                }
            }

        } catch (SQLException e) { JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
    }

    /**
     * Muestra el menú de clientes, solicita una opción al usuario y ejecuta la acción correspondiente.
     *
     * @param informeService servicio encargado de la lógica de negocio de informes
     * @return opción seleccionada por el usuario
     *
     * @throws ServiceException si ocurre un error en la capa de servicio
     * @throws ValidationException si los datos introducidos no son válidos
     * @throws IllegalArgumentException si la opción no está dentro del rango permitido
     */
    private static int ejecutarOpcion(InformeService informeService)  throws ServiceException, ValidationException {

        int opc = ConsoleUI.ingresarNumero(Mensajes.MENU_INFORMES, "Menu Informes");

        switch (opc) {
            case 1 -> obtenerProductoMasVendido(informeService);
            case 2 -> obtenerClienteConMasPedidos(informeService);
            case 3 -> obtenerTotalFacturado(informeService);
            case 4 -> top5ProductosMasVendidos(informeService);

            case 0, -1 -> {}
            default -> throw new IllegalArgumentException("Debe ingresar un número comprendido entre 0-4");
        }

        return opc;
    }

    /**
     * Muestra en una tabla el producto más vendido del sistema.
     *
     * @param informeService servicio encargado de obtener los datos del informe
     */
    private static void obtenerProductoMasVendido(InformeService informeService){
        ProductoInforme productoInforme = informeService.obtenerProductoMasVendido();
        Producto producto = productoInforme.getProducto();

        String[] columnas = {"id", "Nombre", "Descripcion", "Total Vendido"};

        String [][] datosPedido = {
            {
                String.valueOf(producto.getId()),
                producto.getNombre(),
                producto.getDescripcion(),
                String.valueOf(productoInforme.getTotalVendido())
            }
        };

        TablaViewer.crearTabla(datosPedido, columnas, "Producto Mas Vendido");
    }

    /**
     * Muestra en una tabla el cliente con más pedidos realizados.
     *
     * @param informeService servicio encargado de obtener los datos del informe
     */
    private static void obtenerClienteConMasPedidos(InformeService informeService){
        ClienteInforme clienteInforme = informeService.obtenerClienteConMasPedidos();
        Cliente cliente = clienteInforme.getCliente();

        String[] columnas = {"id", "Nombre", "Apellido", "Total Compras"};

        String [][] datos = {
            {
                String.valueOf(cliente.getId()),
                cliente.getNombre(),
                cliente.getApellido(),
                String.valueOf(clienteInforme.getTotalComprado())
            }
        };

        TablaViewer.crearTabla(datos, columnas, "Cliente Con Mas Compras");
    }

    /**
     * Muestra el total facturado en el sistema mediante un cuadro de diálogo.
     *
     * @param informeService servicio encargado de calcular el total facturado
     */
    private static void obtenerTotalFacturado(InformeService informeService){
        double total = informeService.obtenerTotalFacturado();
        String mensaje = "El total total facturado es de " + total + "€" ;

        JOptionPane.showMessageDialog( null, mensaje,  "Calcular Total Facturado",  JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Muestra una tabla con los 5 productos más vendidos del sistema.
     *
     * @param informeService servicio encargado de obtener los datos del informe
     */
    private static void top5ProductosMasVendidos(InformeService informeService){
        List<ProductoInforme> productosInformes = informeService.top5ProductosMasVendidos();

        String[] columnas = {"id", "Nombre", "Descripcion", "Total Vendido"};
        String[][] datosPedido = new String[productosInformes.size()][columnas.length];


        for (int i = 0; i < productosInformes.size(); i++) {
            ProductoInforme productoInforme = productosInformes.get(i);
            Producto producto = productoInforme.getProducto();

            String[] registro = {
                String.valueOf(producto.getId()),
                producto.getNombre(),
                producto.getDescripcion(),
                String.valueOf(productoInforme.getTotalVendido())
            };

            System.arraycopy(registro, 0, datosPedido[i], 0, columnas.length);
        }

        TablaViewer.crearTabla(datosPedido, columnas, "Producto Mas Vendido");
    }
}