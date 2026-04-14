package service.interfaces;

import exception.ServiceException;
import model.Producto;

import java.util.List;

/**
 * Interfaz de la capa de servicio encargada de la lógica de negocio relacionada con productos.
 * <p>
 * Actúa como intermediaria entre la capa de presentación (UI/Gestor) y la capa de acceso a datos (DAO).
 * <p>
 * Se encarga de validar, procesar y coordinar las operaciones sobre productos.
 */
public interface IProductoService {

    /**
     * Inserta un nuevo producto en el sistema.
     *
     * @param nombre nombre del producto
     * @param descripcion descripción del producto
     * @param precio precio del producto
     * @param stock cantidad disponible en stock
     * @throws ServiceException sí ocurre un error en la lógica de negocio o validación
     */
    void insertarProducto(String nombre, String descripcion, double precio, int stock) throws ServiceException;

    /**
     * Obtiene un producto por su identificador.
     *
     * @param id identificador del producto
     * @return producto encontrado
     * @throws ServiceException sí ocurre un error durante la operación
     */
    Producto buscarProductoPorId(int id) throws ServiceException;

    /**
     * Obtiene la lista de todos los productos registrados.
     *
     * @return lista de productos
     * @throws ServiceException sí ocurre un error durante la operación
     */
    List<Producto> listarProductos() throws ServiceException;

    /**
     * Modifica únicamente el nombre de un producto.
     *
     * @param id identificador del producto
     * @param nombre nuevo nombre
     * @throws ServiceException sí ocurre un error durante la operación
     */
    void modificarNombre(int id, String nombre) throws ServiceException;

    /**
     * Modifica únicamente la descripción de un producto.
     *
     * @param id identificador del producto
     * @param descripcion nueva descripción
     * @throws ServiceException sí ocurre un error durante la operación
     */
    void modificarDescripcion(int id, String descripcion) throws ServiceException;

    /**
     * Modifica únicamente el precio de un producto.
     *
     * @param id identificador del producto
     * @param precio nuevo precio
     * @throws ServiceException si ocurre un error durante la operación
     */
    void modificarPrecio(int id, double precio) throws ServiceException;

    /**
     * Modifica únicamente el stock de un producto.
     *
     * @param id identificador del producto
     * @param stock nueva cantidad en stock
     * @throws ServiceException sí ocurre un error durante la operación
     */
    void modificarStock(int id, int stock) throws ServiceException;

    /**
     * Elimina un producto del sistema por su identificador.
     *
     * @param id identificador del producto a eliminar
     * @throws ServiceException sí ocurre un error durante la operación
     */
    void eliminarProducto(int id) throws ServiceException;
}