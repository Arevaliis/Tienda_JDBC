package service.impl;

import dao.impl.DetallePedidoDAO;
import exception.DAOException;
import exception.ServiceException;
import model.DetallePedido;
import model.Producto;
import service.interfaces.IDetallePedidoService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DetallePedidoService implements IDetallePedidoService {
    private final Connection connection;
    private final DetallePedidoDAO detallePedidoDAO;
    private final ProductoService productoService;

    public DetallePedidoService(Connection connection) {
        this.connection = connection;
        this.detallePedidoDAO = new DetallePedidoDAO(connection);
        this.productoService = new ProductoService(connection);
    }

    @Override
    public void insertarDetallePedido(int idPedido, int idProducto, int cantidad) throws ServiceException {

        try{

            connection.setAutoCommit(false);

            try {
                DetallePedido detallePedido = detallePedidoDAO.listarDetallePorId(idPedido, idProducto);
                if (detallePedido != null){ throw new ServiceException("El pedido ingresado ya tiene ese producto agregado. Si lo desea puede modificar la cantidad"); }

                Producto producto = productoService.buscarProductoPorId(idProducto);

                int stockActual = producto.getStock();
                int stockRestante = stockActual - cantidad;

                if (stockRestante < 0){ throw new ServiceException("No hay suficiente stock del producto. Stock actual: " + stockActual); }

                productoService.modificarStock(idProducto, stockRestante);

                detallePedido = new DetallePedido(idPedido, idProducto, cantidad, producto.getPrecio());
                detallePedidoDAO.insertar(detallePedido);

                connection.commit();

            } catch ( SQLException | DAOException e) {
                connection.rollback();
                throw new ServiceException("Error Service: Fallo durante insert detalle pedido", e);

            } finally { connection.setAutoCommit(true); }

        } catch ( SQLException e) { throw new ServiceException("Error al configurar la transacción de detalle pedido", e); }
    }

    @Override
    public List<DetallePedido> listarDetallesPedido(int idPedido) throws ServiceException {
        try{

            List<DetallePedido> detallesPedido = detallePedidoDAO.listarDetallesPedido(idPedido);
            if (detallesPedido.isEmpty()) { throw new ServiceException("No existe ningún pedido dentro de la tabla detalle_pedido con el id de pedido: " + idPedido); }

            return detallesPedido;

        } catch ( DAOException e) { throw new ServiceException("Error Service: Fallo durante select de los detalles del pedido", e); }
    }

    @Override
    public DetallePedido listarDetallePorId(int idPedido, int idProducto) throws ServiceException {
        try {

            DetallePedido detallePedido = detallePedidoDAO.listarDetallePorId(idPedido, idProducto);

            if(detallePedido == null) {throw new ServiceException("No existe ningún id dentro de la tabla detalle_pedido con id_pedido: " + idPedido + " y id_producto: "+ idProducto); }

            return detallePedido;

        } catch ( DAOException e) { throw new ServiceException("Error Service: Fallo durante select de un detalle del pedido", e); }
    }

    @Override
    public void modificarCantidadProducto(int idPedido, int idProducto, int cantidad) throws ServiceException {
        try{

            connection.setAutoCommit(false);

            try {

                if ( cantidad <= 0) { throw new ServiceException("No puede ingresar una cantidad menor o igual a 0"); }

                DetallePedido detallePedido = detallePedidoDAO.listarDetallePorId(idPedido, idProducto);
                if (detallePedido == null){ throw new ServiceException("El producto que desea modificar no se encuentra en el pedido"); }

                Producto producto = detallePedido.getProducto();

                int stockActualProducto = producto.getStock();
                int cantidadActualPedido = detallePedido.getCantidad();

                int diferenciaEntreCantidades = cantidad - cantidadActualPedido;
                if (diferenciaEntreCantidades == 0) { throw new ServiceException("La cantidad ingresada es igual a la actual."); }

                // Modificamos la cantidad en detalle_pedido
                detallePedido.setCantidad(cantidad);

                // Aumentar la cantidad
                if (diferenciaEntreCantidades > 0 ){
                    if (stockActualProducto - cantidad < 0){ throw new ServiceException("No hay suficiente stock del producto. Stock actual: " + stockActualProducto); }
                    producto.setStock(stockActualProducto - diferenciaEntreCantidades);
                }

                // Restar la cantidad
                if (diferenciaEntreCantidades < 0 ){ producto.setStock(stockActualProducto + (diferenciaEntreCantidades * -1) ); }

                productoService.modificarStock(producto.getId(), producto.getStock());
                detallePedidoDAO.modificarCantidadProducto(detallePedido);

                connection.commit();

            } catch ( SQLException | DAOException e) {
                connection.rollback();
                throw new ServiceException("Error Service: Fallo durante insert detalle pedido", e);

            } finally { connection.setAutoCommit(true); }

        } catch ( SQLException e) { throw new ServiceException("Error al configurar la transacción de detalle pedido", e); }
    }

    @Override
    public void eliminarDetallesPedido(int idPedido, int idProducto) throws ServiceException {
        try{

            connection.setAutoCommit(false);

            try {
                DetallePedido detallePedido = detallePedidoDAO.listarDetallePorId(idPedido, idProducto);

                Producto producto = detallePedido.getProducto();
                int nuevoStock = detallePedido.getProducto().getStock() + detallePedido.getCantidad();

                productoService.modificarStock(producto.getId(), nuevoStock);
                detallePedidoDAO.eliminarDetallesPedido(idPedido, idProducto);

                connection.commit();

            } catch ( SQLException | DAOException e) {
                connection.rollback();
                throw new ServiceException("Error Service: Fallo durante delete detalle pedido", e);

            } finally { connection.setAutoCommit(true); }

        } catch ( SQLException e) { throw new ServiceException("Error al configurar la transacción de detalle pedido", e); }
    }

    @Override
    public void eliminarPorPedido(int idPedido) throws ServiceException {
        try{

            connection.setAutoCommit(false);

            try {
                List<DetallePedido> detallePedidos = detallePedidoDAO.listarDetallesPedido(idPedido);

                for (DetallePedido detallePedido: detallePedidos){
                    Producto producto = detallePedido.getProducto();
                    int nuevoStock = detallePedido.getProducto().getStock() + detallePedido.getCantidad();

                    productoService.modificarStock(producto.getId(), nuevoStock);
                }

                detallePedidoDAO.eliminarPorPedido(idPedido);

                connection.commit();

            } catch ( SQLException | DAOException e) {
                connection.rollback();
                throw new ServiceException("Error Service: Fallo durante delete detalle pedido", e);

            } finally { connection.setAutoCommit(true); }

        } catch ( SQLException e) { throw new ServiceException("Error al configurar la transacción de detalle pedido", e); }
    }

    @Override
    public double obtenerTotalPedido(int idPedido) throws ServiceException {
        return 0;
    }
}
