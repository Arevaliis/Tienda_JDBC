package dao.impl;

import dao.interfaces.IProductoDAO;
import exception.DAOException;
import model.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO implements IProductoDAO {
    private final Connection connection;

    public ProductoDAO(Connection connection) { this.connection = connection; }

    @Override
    public void insertarProducto(Producto producto) throws DAOException {
        String sql = "INSERT INTO producto (nombre, descripcion, precio, stock) VALUES (?,?,?,?)";

        try(PreparedStatement insert = connection.prepareStatement(sql)) {

            insert.setString(1, producto.getNombre());
            insert.setString(2, producto.getDescripcion());
            insert.setDouble(3, producto.getPrecio());
            insert.setInt(4, producto.getStock());

            if (insert.executeUpdate() == 0) { throw new DAOException("No se ha completado el registro del producto"); }

        } catch (SQLException e) { throw new DAOException("Error DAO: Fallo al hacer insert producto", e); }
    }

    @Override
    public Producto buscarProductoPorId(int id) throws DAOException {
        String sql = "SELECT id, nombre, descripcion, precio, stock FROM producto WHERE id = ?";

        try (PreparedStatement selectProducto = connection.prepareStatement(sql)) {
            selectProducto.setInt(1, id);

            try (ResultSet resultado = selectProducto.executeQuery()) {

                if (! resultado.next()){ return null; }

                return new Producto( resultado.getInt("id"),
                                     resultado.getString("nombre"),
                                     resultado.getString("descripcion"),
                                     resultado.getDouble("precio"),
                                     resultado.getInt("stock")
                        );
            }

        } catch (SQLException e) { throw new DAOException("Error DAO: Fallo al hacer select producto", e); }
    }

    @Override
    public List<Producto> listarProductos() throws DAOException{
        String sql = "SELECT id, nombre, descripcion, precio, stock FROM producto ORDER BY id ASC";

        try(PreparedStatement selectProducto = connection.prepareStatement(sql);
            ResultSet resultado = selectProducto.executeQuery()) {

                if (! resultado.next()){ return new ArrayList<>(); }

                List<Producto> productos = new ArrayList<>();

                do{
                    productos.add(new Producto( resultado.getInt("id"),
                                                resultado.getString("nombre"),
                                                resultado.getString("descripcion"),
                                                resultado.getDouble("precio"),
                                                resultado.getInt("stock"))
                    );

                } while (resultado.next());

                return productos;

        } catch (SQLException e) { throw new DAOException("Error DAO: Fallo al hacer select producto", e); }

    }

    @Override
    public void actualizarProducto(Producto producto) throws DAOException{
        String sql = "UPDATE producto " +
                     "SET nombre = ?, descripcion = ?, precio = ?, stock = ? " +
                     "WHERE id = ?";

        try(PreparedStatement update = connection.prepareStatement(sql)) {
            update.setString(1, producto.getNombre());
            update.setString(2, producto.getDescripcion());
            update.setDouble(3, producto.getPrecio());
            update.setInt(4, producto.getStock());
            update.setInt(5, producto.getId());

            if (update.executeUpdate() == 0){ throw new DAOException("No se ha podido actualizar el producto"); }

        } catch (SQLException e) { throw new DAOException("Error DAO: Fallo al hacer update del producto", e); }
    }

    @Override
    public void eliminarProducto(int id) throws DAOException{
        String sql = "DELETE FROM producto WHERE id = ?";

        try(PreparedStatement delete = connection.prepareStatement(sql)) {
            delete.setInt(1, id);

            if (delete.executeUpdate() == 0){ throw new DAOException("No existe el id de producto ingresado: " + id); }

        } catch (SQLException e) { throw new DAOException("Error DAO: Fallo al hacer delete del producto", e); }
    }
}
