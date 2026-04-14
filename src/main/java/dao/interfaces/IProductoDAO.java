package dao.interfaces;

import exception.DAOException;
import model.Producto;
import java.util.List;

/**
 * Interfaz DAO encargada de definir las operaciones de acceso a datos relacionadas con la entidad Producto.
 * <p>
 * Proporciona los métodos básicos CRUD para interactuar con la base de datos.
 */
public interface IProductoDAO {

    /**
     * Inserta un nuevo producto en la base de datos.
     *
     * @param producto objeto Producto que contiene los datos a insertar
     * @throws DAOException si ocurre un error durante la operación de base de datos
     */
    void insertarProducto(Producto producto) throws DAOException;

    /**
     * Obtiene un producto a partir de su identificador.
     *
     * @param id identificador del producto
     * @return producto encontrado
     * @throws DAOException si ocurre un error durante la consulta
     */
    Producto buscarProductoPorId(int id) throws DAOException;

    /**
     * Obtiene todos los productos almacenados en la base de datos.
     *
     * @return lista de productos registrados
     * @throws DAOException si ocurre un error durante la consulta
     */
    List<Producto> listarProductos() throws DAOException;

    /**
     * Actualiza los datos de un producto existente en la base de datos.
     *
     * @param producto objeto Producto con los datos actualizados
     * @throws DAOException sí ocurre un error durante la actualización
     */
    void actualizarProducto(Producto producto) throws DAOException;

    /**
     * Elimina un producto de la base de datos a partir de su identificador.
     *
     * @param id identificador del producto a eliminar
     * @throws DAOException sí ocurre un error durante la eliminación
     */
    void eliminarProducto(int id) throws DAOException;
}