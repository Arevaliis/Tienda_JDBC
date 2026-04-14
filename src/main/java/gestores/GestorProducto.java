package gestores;

import exception.ServiceException;
import exception.ValidationException;
import model.Producto;
import service.impl.ProductoService;
import util.ConsoleUI;
import util.DatabaseConnection;
import util.Mensajes;
import util.TablaViewer;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Clase encargada de gestionar el menú de productos.
 */
public class GestorProducto {

    /**
     * Ejecuta el menú principal de gestión de productos.
     * <p>
     * Abre la conexión con la base de datos, inicializa el servicio de productos
     * y permite al usuario realizar operaciones de forma repetida hasta que decida salir.
     * <p>
     * Maneja excepciones de SQL, servicio y validación mostrando mensajes al usuario.
     */
    public static void ejecutarMenuProducto(){
        boolean seguir = true;

        try (Connection connection = DatabaseConnection.getConnection()) {
            ProductoService productoService = new ProductoService(connection);

            while (seguir) {
                try {

                    if (ejecutarOpcion(productoService) <= 0) { return; }
                    seguir = ConsoleUI.confirmarContinuacion("¿Desea seguir en la sección de productos? S/N: ", "Seguir Menu Productos");

                } catch (IllegalArgumentException | ServiceException | ValidationException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

                } catch (NullPointerException ignored) {
                    seguir = ConsoleUI.confirmarContinuacion("¿Desea seguir en la sección de productos? S/N: ", "Seguir Menu Productos");
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Ejecuta la opción seleccionada por el usuario en el menú de productos.
     *
     * @param productoService servicio encargado de la lógica de productos
     * @return opción seleccionada por el usuario
     * @throws ServiceException si ocurre un error en la capa de servicio
     * @throws ValidationException si los datos introducidos no son válidos
     * @throws IllegalArgumentException si la opción no está dentro del rango permitido
     */
    private static int ejecutarOpcion(ProductoService productoService)  throws ServiceException, ValidationException {

        int opc = ConsoleUI.ingresarNumero(Mensajes.MENU_PRODUCTOS, "Menu Productos");

        switch (opc) {
            case 1 -> crearProducto(productoService);
            case 2 -> buscarProducto(productoService);
            case 3 -> listarProducto(productoService);
            case 4 -> menuModificarProducto(productoService);
            case 5 -> eliminarProducto(productoService);

            case 0, -1 -> {}
            default -> throw new IllegalArgumentException("Debe ingresar un número comprendido entre 0-5");
        }

        return opc;
    }

    /**
     * Solicita los datos del producto al usuario y lo inserta en el sistema.
     *
     * @param productoService servicio encargado de gestionar los productos
     * @throws ValidationException si alguno de los datos introducidos no es válido
     */
    private static void crearProducto(ProductoService productoService) throws ValidationException {
        String nombre = ConsoleUI.ingresarPalabra("Ingrese el nombre del producto: ", "Ingresar Producto");
        String descripcion = ConsoleUI.ingresarPalabra("Ingrese una descripción del producto: ", "Ingresar Producto");

        double precio = ConsoleUI.ingresarDecimal("Ingrese el precio del producto: ", "Ingresar Producto");


        int stock = ConsoleUI.ingresarNumero("Ingrese el stock del producto: ", "Ingresar Producto");
        if (stock == -1){ return;}

        productoService.insertarProducto(nombre, descripcion, precio, stock);
        JOptionPane.showMessageDialog( null,  "Producto ingresado con éxito",  "Ingresar Producto",  JOptionPane.INFORMATION_MESSAGE );

    }

    /**
     * Busca un producto por su identificador y lo muestra en pantalla.
     *
     * @param productoService servicio encargado de gestionar productos
     */
    private static void buscarProducto(ProductoService productoService) {
        int id = ConsoleUI.ingresarNumero("Ingrese el id del producto", "Ver Producto");
        if (id == -1){ return;}

        Producto producto = productoService.buscarProductoPorId(id);
        String[] columnas = {"id", "Nombre", "Descripcion", "Precio", "Stock"};

        String [][] datosCliente = {
                {
                    String.valueOf(producto.getId()),
                    producto.getNombre(),
                    producto.getDescripcion(),
                    String.valueOf(producto.getPrecio()),
                    String.valueOf(producto.getStock())
                }
        };

        TablaViewer.crearTabla(datosCliente, columnas, "Ver Producto", 650, 75);
    }

    /**
     * Obtiene y muestra la lista completa de productos registrados.
     * <p>
     * Los productos se formatean en texto utilizando el método {@code toString()}
     * y se muestran en una ventana de información.
     *
     * @param productoService servicio encargado de gestionar productos
     */
    private static void listarProducto(ProductoService productoService) {
        List<Producto> productos = productoService.listarProductos();

        String[] columnas = {"id", "Nombre", "Descripcion", "Precio", "Stock"};
        String [][] datosClientes = new String[productos.size()][columnas.length];

        for (int i = 0; i < productos.size(); i++) {
            Producto producto = productos.get(i);

            String [] datosProducto = {
                            String.valueOf(producto.getId()),
                            producto.getNombre(),
                            producto.getDescripcion(),
                            String.valueOf(producto.getPrecio()),
                            String.valueOf(producto.getStock())
            };

            System.arraycopy(datosProducto, 0, datosClientes[i], 0, columnas.length);
        }

        TablaViewer.crearTabla(datosClientes, columnas, "Ver Productos", 825, 150);
    }

    /**
     * Elimina un producto del sistema a partir de su identificador.
     * <p>
     * Solicita el ID al usuario, lo valida y delega la operación al servicio.
     *
     * @param productoService servicio encargado de gestionar productos
     */
    private static void eliminarProducto(ProductoService productoService) {
        int id = ConsoleUI.ingresarNumero("Ingrese el id del producto", "Eliminar Producto");
        if (id == -1){ return;}

        productoService.eliminarProducto(id);
        JOptionPane.showMessageDialog( null,  "Producto eliminado de la base de datos",  "Eliminar Producto",  JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Muestra el menú de modificación de productos.
     * <p>
     * Permite seleccionar qué atributo del producto se desea modificar.
     *
     * @param productoService servicio encargado de gestionar productos
     */
    private static void menuModificarProducto(ProductoService productoService) throws ValidationException {

        int opc = ConsoleUI.seleccionarOpcion( new String[]{"Nombre", "Descripcion", "Precio", "Stock"},  "Modificar Producto" ) + 1;

        switch (opc) {
            case 1 -> modificarNombreProducto(productoService);
            case 2 -> modificarDescripcionProdcto(productoService);
            case 3 -> modificarPrecioProducto(productoService);
            case 4 -> modificarStockProducto(productoService);

            default -> { }
        }
    }

    /**
     * Modifica el nombre de un producto existente.
     *
     * @param productoService servicio que gestiona las operaciones de producto
     * @throws ValidationException si ocurre un error de validación en la entrada
     */
    private static void modificarNombreProducto(ProductoService productoService) throws ValidationException {
        int id = ConsoleUI.ingresarNumero("Ingrese el id del producto", "Modificar Nombre");
        if (id == -1) { return; }

        String nombre = ConsoleUI.ingresarPalabra("Ingrese el nombre del producto:", "Modificar Nombre");
        productoService.modificarNombre(id, nombre);

        JOptionPane.showMessageDialog(null,  "Nombre producto actualizado correctamente",  "Modificar Nombre",  JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Modifica la descripción de un producto existente.
     *
     * @param productoService servicio que gestiona las operaciones de producto
     * @throws ValidationException si ocurre un error de validación en la entrada
     */
    private static void modificarDescripcionProdcto(ProductoService productoService) throws ValidationException {
        int id = ConsoleUI.ingresarNumero("Ingrese el id del producto", "Modificar Descripción");
        if (id == -1) { return; }

        String descripcion = ConsoleUI.ingresarPalabra("Ingrese la descripción del producto:", "Modificar Descripción");
        productoService.modificarDescripcion(id, descripcion);

        JOptionPane.showMessageDialog(null,  "Descripción del producto actualizada correctamente",  "Modificar Descripción",  JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Modifica el precio de un producto existente.
     *
     * @param productoService servicio que gestiona las operaciones de producto
     */
    private static void modificarPrecioProducto(ProductoService productoService) {
        int id = ConsoleUI.ingresarNumero("Ingrese el id del producto", "Modificar Precio");
        if (id == -1) { return; }

        double precio = ConsoleUI.ingresarDecimal("Ingrese el precio del producto: ", "Modificar Precio");
        productoService.modificarPrecio(id, precio);

        JOptionPane.showMessageDialog(null,  "Precio del producto actualizado correctamente",  "Modificar Precio",  JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Modifica el stock de un producto existente.
     *
     * @param productoService servicio que gestiona las operaciones de producto
     */
    private static void modificarStockProducto(ProductoService productoService) {
        int id = ConsoleUI.ingresarNumero("Ingrese el id del producto", "Modificar Stock");
        if (id == -1) { return; }

        int stock = ConsoleUI.ingresarNumero("Ingrese el stock del producto: ", "Modificar Stock");
        if (stock == -1) {return;}

        productoService.modificarStock(id, stock);

        JOptionPane.showMessageDialog(null,  "Stock del producto actualizado correctamente",  "Modificar Stock",  JOptionPane.INFORMATION_MESSAGE);
    }
}