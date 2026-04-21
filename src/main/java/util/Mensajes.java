package util;

/**
 * Clase utilitaria que contiene los mensajes de texto utilizados en la interfaz de usuario.
 */
public class Mensajes {

    /**
     * Mensaje del menú principal del sistema.
     */
    public final static String MENU_INICIAL = """
            ================================
                SISTEMA DE GESTIÓN PEDIDOS
            ================================
            
                    1. Clientes
                    2. Productos
                    3. Pedidos
                    4. Detalles pedido
                    5. Informes
                    0. Salir
            
            Seleccione una opción del 0 al 5:""";

    /**
     * Mensaje del menú de gestión de clientes.
     */
    public final static String MENU_CLIENTES = """
            ==================================
                SISTEMA DE GESTIÓN CLIENTES
            ==================================
            
                1. Crear cliente
                2. Ver cliente por ID
                3. Listar todos los clientes
                4. Modificar cliente
                5. Eliminar cliente
                6. Gestión emails
                7. Exportar clientes
                0. Volver al menú principal
            
            Seleccione una opción del 0 al 7:""";


    /**
     * Mensaje del menú de gestión de emails de clientes.
     */
    public final static String MENU_EMAIL = """
             ==================================
                SISTEMA DE GESTIÓN EMAIL
             ==================================

                1. Añadir email a cliente
                2. Modificar email
                3. Cambiar cliente
                4. Listar emails de un cliente
                5. Eliminar email
                0. Volver al menú clientes

             Seleccione una opción del 0 al 5:""";

    /**
     * Mensaje del menú de gestión de productos.
     */
    public final static String MENU_PRODUCTOS = """
            ==================================
                SISTEMA DE GESTIÓN PRODUCTOS
            ==================================
            
                1. Crear producto
                2. Buscar producto por ID
                3. Listar todos los productos
                4. Modificar producto
                5. Eliminar producto
                6. Exportar productos
                0. Volver al menú principal
            
            Seleccione una opción del 0 al 6:""";

    /**
     * Mensaje del menú de gestión de pedidos.
     */
    public final static String MENU_PEDIDOS = """
            ==================================
                SISTEMA DE GESTIÓN PEDIDOS
            ==================================
            
                1. Crear pedido
                2. Buscar pedido por ID
                3. Listar todos los pedido
                4. Listar pedidos por cliente
                5. Modificar pedido
                6. Eliminar pedido
                7. Exportar pedidos
                0. Salir
            
            Seleccione una opción del 0 al 7:""";


    /**
     * Mensaje del menú de gestión de los detalles de los pedidos.
     */
    public final static String MENU_DETALLES_PEDIDO = """
        =============================================
              SISTEMA DE GESTIÓN DETALLES PEDIDOS
        =============================================
        
                1. Añadir producto a un pedido
                2. Listar detalles por pedido
                3. Obtener detalle concreto
                4. Modificar cantidad de producto
                5. Eliminar detalle de pedido
                6. Eliminar detalles de pedido
                7. Calcular total del pedido
                8. Exportar detalles pedidos
                0. Salir
        
        Seleccione una opción del 0 al 8:""";

    /**
     * Mensaje del menú de gestión de los informes.
     */
    public final static String MENU_INFORMES = """
            ====================================
                 SISTEMA DE GESTIÓN INFORMES
            ====================================
            
                    1. Producto TOP
                    2. Cliente TOP
                    3. Total facturado
                    4. TOP 5 productos
                    0. Salir
            
            Seleccione una opción del 0 al 4:""";
}