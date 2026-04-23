package service.impl;

import dao.impl.DetallePedidoDAO;
import dao.impl.PedidoDAO;
import dao.impl.ProductoDAO;
import exception.DAOException;
import exception.ServiceException;
import model.ClienteInforme;
import model.ProductoInforme;
import service.interfaces.IInformeService;

import java.sql.Connection;
import java.util.List;

public class InformeService implements IInformeService {
    private final ProductoDAO productoDAO;
    private final PedidoDAO pedidoDAO;
    private final DetallePedidoDAO detallePedidoDAO;

    public InformeService(Connection connection) {
        this.productoDAO = new ProductoDAO(connection);
        this.pedidoDAO = new PedidoDAO(connection);
        this.detallePedidoDAO = new DetallePedidoDAO(connection);
    }

    @Override
    public ProductoInforme obtenerProductoMasVendido() throws ServiceException {
        try{
            ProductoInforme producto = productoDAO.obtenerProductoTOP();

            if (producto == null){ throw new ServiceException("No hay ventas de productos");  }

            return producto;

        } catch (DAOException e) { throw new ServiceException("No se puedo obtener el producto mas vendido", e); }
    }

    @Override
    public ClienteInforme obtenerClienteConMasPedidos() throws ServiceException {
        try {
            ClienteInforme cliente = pedidoDAO.buscarClienteConMasPedidos();

            if (cliente == null) { throw new ServiceException("No hay clientes con pedidos"); }

            return cliente;

        } catch (DAOException e) { throw new ServiceException("No se puedo obtener el cliente con mas pedidos", e); }
    }

    @Override
    public double obtenerTotalFacturado() throws ServiceException {
        double total = detallePedidoDAO.obtenerTotalVendido();

        if (total <= 0) {throw new ServiceException("No hay ventas registradas en la base de datos"); }

        return total;
    }

    @Override
    public List<ProductoInforme> top5ProductosMasVendidos() throws ServiceException {
        try{
            List<ProductoInforme> productos = productoDAO.obtenerTop5ProductosMasVendidos();

            if (productos.isEmpty() ){ throw new ServiceException("No se ha vendido ningún producto.");  }

            return productos;

        } catch (DAOException e) { throw new ServiceException("No se puedo obtener los productos mas vendidos", e); }
    }
}
