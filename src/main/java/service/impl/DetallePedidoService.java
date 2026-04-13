package service.impl;

import dao.impl.DetallePedidoDAO;
import dao.impl.ProductoDAO;
import exception.DAOException;
import exception.ServiceException;
import model.DetallePedido;
import model.Pedido;
import model.Producto;
import service.interfaces.IDetallePedidoService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetallePedidoService implements IDetallePedidoService {
    private final Connection connection;
    private final DetallePedidoDAO detallePedidoDAO;
    private final PedidoService pedidoService;
    private final ProductoService productoService;

    public DetallePedidoService(Connection connection) {
        this.connection = connection;
        this.detallePedidoDAO = new DetallePedidoDAO(connection);
        this.pedidoService = new PedidoService(connection, new ClienteService(connection));
        this.productoService = new ProductoService(connection);
    }

    // TODO COMPROBAR QUE PK ES DECIR ID PEDIDO + ID PRODUCTO YA EXISTE
    // TODO "El pedido ingresado ya tiene ese producto"
    @Override
    public void insertarDetallePedido(int id_pedido, int id_producto, int cantidad) throws ServiceException {

        try{

            connection.setAutoCommit(false);

            try {
                Producto producto = productoService.verProductoPorID(id_producto);

                int stockActual = producto.getStock();
                int stockRestante = stockActual - cantidad;

                if (stockRestante < 0){ throw new ServiceException("No hay suficiente stock del producto. Stock actual: " + stockActual); }

                productoService.modificarStock(id_producto, stockRestante);

                DetallePedido detallePedido = new DetallePedido(id_pedido, id_producto, cantidad, producto.getPrecio());

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

            List<DetallePedido> detallesDePedidoConID = detallePedidoDAO.listarDetallesPedido(idPedido);

            if (detallesDePedidoConID.isEmpty()) { throw new ServiceException("No existe ningún pedido dentro de la tabla detalle_pedido con el id: " + idPedido); }

            List<DetallePedido> detallesDePedidoConInstancia = new ArrayList<>();
            Pedido pedido = pedidoService.buscarPedidoID(idPedido);

            for(DetallePedido detalle: detallesDePedidoConID){
                Producto producto = productoService.verProductoPorID(detalle.getIdProducto());

                detallesDePedidoConInstancia.add(
                                                new DetallePedido(pedido, producto, detalle.getCantidad(), detalle.getPrecioUnitario() )
                );
            }

            return detallesDePedidoConInstancia;

        } catch ( DAOException e) { throw new ServiceException("Error Service: Fallo durante select de los detalles del pedido", e); }
    }

    @Override
    public DetallePedido listarDetallePorId(int idPedido, int idProducto) throws ServiceException {
        return null;
    }

    @Override
    public void modificarCantidadProducto(int idPedido, int idProducto, int cantidad) throws ServiceException {

    }

    @Override
    public void eliminarDetallesPedido(int idPedido, int idProducto) throws ServiceException {

    }

    @Override
    public void eliminarPorPedido(int idPedido) throws ServiceException {

    }

    @Override
    public double obtenerTotalPedido(int idPedido) throws ServiceException {
        return 0;
    }
}
