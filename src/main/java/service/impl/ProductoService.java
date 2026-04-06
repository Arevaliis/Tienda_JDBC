package service.impl;

import dao.impl.ProductoDAO;
import exception.DAOException;
import exception.ServiceException;
import model.Producto;
import service.interfaces.IProductoService;

import java.sql.Connection;
import java.util.List;

public class ProductoService implements IProductoService {
    private final Connection connection;
    private final ProductoDAO productoDAO;

    public ProductoService(Connection connection ) {
        this.connection = connection;
        this.productoDAO = new ProductoDAO(connection);
    }

    @Override
    public void insertarProducto(String nombre, String descripcion, double precio, int stock) throws ServiceException {
        try{
            Producto producto = new Producto(nombre, descripcion, precio, stock);
            productoDAO.insertarProducto(producto);

        } catch (DAOException e) { throw new ServiceException("Error Service: Fallo a la hora de insertar producto en la base de datos", e); }
    }

    @Override
    public Producto verProductoPorID(int id) throws ServiceException {
        try{
            Producto producto = productoDAO.obtenerProductoPorId(id);

            if (producto == null){ throw new ServiceException("No hay productos registrados con el id: " + id); }

            return producto;

        } catch (DAOException e) { throw new ServiceException("Error Service: Fallo a la hora de buscar producto en la base de datos", e); }
}

    @Override
    public List<Producto> listarProductos() throws ServiceException {
        try {
            List<Producto> productos = productoDAO.obtenerTodos();

            if (productos.isEmpty()){ throw new ServiceException("No hay productos registrados en la base de datos"); }

            return productos;

        } catch (DAOException e) { throw new ServiceException("Error Service: Fallo a la hora de buscar todos los productos en la base de datos", e); }
    }

    @Override
    public void modificarNombre(int id, String nombre) throws ServiceException {

    }

    @Override
    public void modificarDescripcion(int id, String descripcion) throws ServiceException {

    }

    @Override
    public void modificarPrecio(int id, double precio) throws ServiceException {

    }

    @Override
    public void modificarStock(int id, int stock) throws ServiceException {

    }

    @Override
    public void eliminarProducto(int id) throws ServiceException {
        try {

            if (verProductoPorID(id) == null){ return; }
            productoDAO.eliminarProducto(id);

        } catch (DAOException e) { throw new ServiceException("Error Service: Fallo a la hora de eliminar el producto de la base de datos", e); }
    }
}