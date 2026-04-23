package dao.impl;

import dao.interfaces.IProductoDAO;
import exception.DAOException;
import model.Producto;
import model.ProductoInforme;
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

            if (insert.executeUpdate() == 0){throw new DAOException("No se insertó ningún producto en la base de datos.");}

        } catch (SQLException e) { throw new DAOException("Error al insertar el producto en la base de datos", e); }
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

        } catch (SQLException e) { throw new DAOException("Error al buscar el producto en la base de datos", e); }
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

        } catch (SQLException e) { throw new DAOException("Error al buscar los productos en la base de datos", e); }

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

            if (update.executeUpdate() == 0){throw new DAOException("No se actualizó ningún producto en la base de datos.");}

        } catch (SQLException e) { throw new DAOException("Error al actualizar el producto en la base de datos", e); }
    }

    @Override
    public void eliminarProducto(int id) throws DAOException{
        String sql = "DELETE FROM producto WHERE id = ?";

        try(PreparedStatement delete = connection.prepareStatement(sql)) {
            delete.setInt(1, id);

            if (delete.executeUpdate() == 0){throw new DAOException("No se insertó eliminó producto en la base de datos.");}

        } catch (SQLException e) { throw new DAOException("Error al eliminar el producto en la base de datos", e); }
    }

    /**
     * Obtiene el producto más vendido
     *
     * @return Producto más vendido
     * @throws DAOException si ocurre un error durante la consulta a la base de datos
     */
    public ProductoInforme obtenerProductoTOP() throws DAOException {
        String sql =
                "SELECT p.id, p.nombre, p.descripcion, p.precio, p.stock, SUM(dp.cantidad) AS total_vendido " +
                "FROM producto p " +

                "INNER JOIN detalle_pedido dp ON p.id = dp.id_producto " +

                "GROUP BY p.id, p.nombre, p.descripcion, p.precio, p.stock " +
                "ORDER BY total_vendido DESC " +
                "LIMIT 1;";

        try (PreparedStatement selectPedido = connection.prepareStatement(sql);
             ResultSet resultado = selectPedido.executeQuery()){

            if (! resultado.next()) { return null; }

            Producto producto = new Producto( resultado.getInt("id"),
                    resultado.getString("nombre"),
                    resultado.getString("descripcion"),
                    resultado.getDouble("precio"),
                    resultado.getInt("stock")
            );

            return new ProductoInforme(producto, resultado.getInt("total_vendido"));

        } catch (SQLException e) { throw new DAOException("Error al obtener el producto mas vendido en la base de datos", e); }
    }

    /**
     * Obtiene los 5 productos más vendidos
     *
     * @return Lista con los productos más vendidos
     * @throws DAOException si ocurre un error durante la consulta a la base de datos
     */
    public List<ProductoInforme> obtenerTop5ProductosMasVendidos() throws DAOException {
        List<ProductoInforme> productos = new ArrayList<>();
        String sql =
                "SELECT p.id, p.nombre, p.descripcion, p.precio, p.stock, SUM(dp.cantidad) AS total_vendido " +
                        "FROM producto p " +

                        "INNER JOIN detalle_pedido dp ON p.id = dp.id_producto " +

                        "GROUP BY p.id, p.nombre, p.descripcion, p.precio, p.stock " +
                        "ORDER BY total_vendido DESC " +
                        "LIMIT 5;";

        try (PreparedStatement selectPedido = connection.prepareStatement(sql);
             ResultSet resultado = selectPedido.executeQuery()){

            while (resultado.next()){

                Producto producto = new Producto( resultado.getInt("id"),
                        resultado.getString("nombre"),
                        resultado.getString("descripcion"),
                        resultado.getDouble("precio"),
                        resultado.getInt("stock")
                );

                productos.add(new ProductoInforme(producto, resultado.getInt("total_vendido")));
            }

            return productos;

        } catch (SQLException e) { throw new DAOException("Error al obtener los cinco productos mas vendidos en la base de datos", e); }
    }
}
