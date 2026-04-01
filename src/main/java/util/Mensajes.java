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
                    4. Salir
            
            Seleccione una opción del 1 al 4:""";

    /**
     * Mensaje del menú de gestión de clientes.
     */
    public final static String MENU_CLIENTES = """
            ==================================
                SISTEMA DE GESTIÓN CLIENTES
            ==================================
            
                1. Crear Cliente
                2. Ver cliente por ID
                3. Listar todos los clientes
                4. Modificar cliente
                5. Eliminar cliente
                6. Salir
            
            Seleccione una opción del 1 al 6:""";
}