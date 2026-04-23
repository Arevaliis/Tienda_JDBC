package service.impl;

import dao.impl.PedidoDAO;
import exception.DAOException;
import exception.ServiceException;
import model.Cliente;
import model.Pedido;
import service.interfaces.IPedidoService;
import java.sql.Connection;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class PedidoService implements IPedidoService {
    private final PedidoDAO pedidoDAO;
    private final ClienteService clienteService;
    private final DetallePedidoService detallePedidoService;

    public PedidoService(Connection connection, ClienteService clienteService) {
        this.pedidoDAO = new PedidoDAO(connection);
        this.clienteService = clienteService;
        this.detallePedidoService = new DetallePedidoService(connection);
    }

    @Override
    public void crearPedido(int idCliente) throws ServiceException {
        try{
            if (clienteService.buscarClienteID(idCliente) == null){ throw new ServiceException("No existe el cliente"); }
            pedidoDAO.crearPedido( new Pedido(idCliente));

        } catch (DAOException e) { throw new ServiceException("No se puedo registrar el pedido", e); }
    }

    @Override
    public Pedido buscarPedidoID(int idPedido) throws ServiceException {
        try{
            Pedido pedidoIdCliente = pedidoDAO.buscarPedidoID(idPedido);
            if(pedidoIdCliente == null){ throw new ServiceException("No existe el pedido con id: " + idPedido ); }

            Cliente cliente = clienteService.buscarClienteID(pedidoIdCliente.getId_cliente());

            return new Pedido( idPedido, cliente, pedidoIdCliente.getFecha());

        } catch (DAOException e) { throw new ServiceException("No se puedo buscar el pedido", e); }
    }

    @Override
    public List<Pedido> listarPedidos() {
        try{
            List<Pedido> pedidoIdCliente = pedidoDAO.listarPedidos();
            if(pedidoIdCliente.isEmpty()){ throw new ServiceException("No hay registros de pedidos en la base de datos." ); }

            List<Pedido> pedidos = new ArrayList<>();

            for (Pedido pedido: pedidoIdCliente){

                Cliente cliente = clienteService.buscarClienteID(pedido.getId_cliente());

                pedidos.add( new Pedido( pedido.getId(),
                                         cliente,
                                         pedido.getFecha())
                );
            }

           return pedidos;

        } catch (DAOException e) { throw new ServiceException("No se puedo listar los pedidos", e); }
    }

    @Override
    public List<Pedido> listarPedidosPorCliente(int idCliente) throws ServiceException {

        try{
            List<Pedido> pedidos = pedidoDAO.listarPedidosPorCliente(idCliente);
            if(pedidos.isEmpty()){ throw new ServiceException("No hay registro de pedidos del cliente en la base de datos"); }

            return pedidos;

        } catch (DAOException e) { throw new ServiceException("No se puedo listar los pedidos del cliente", e); }
    }

    @Override
    public void modificarIdCliente(int idCliente, int idPedido) throws ServiceException {
        try{
            Pedido pedido = buscarPedidoID(idPedido);
            Cliente cliente = clienteService.buscarClienteID(idCliente);

            pedido.setId_cliente(cliente.getId());
            pedidoDAO.actualizarPedido(pedido);

        } catch (DAOException e) { throw new ServiceException("No se puedo modificar el ID del cliente", e); }
    }

    @Override
    public void modificarFecha(int idPedido) throws ServiceException {
        try{
            Pedido pedido = pedidoDAO.buscarPedidoID(idPedido);
            if(pedido == null){ throw new ServiceException("No existe el pedido con id: " + idPedido ); }

            pedido.setFecha( Timestamp.from(Instant.now()) );
            pedidoDAO.actualizarPedido(pedido);

        } catch (DAOException e) { throw new ServiceException("No se puedo modificar la fecha del pedido", e); }
    }

    @Override
    public void eliminarPedido(int idPedido) throws ServiceException {
        try {
            buscarPedidoID(idPedido);

            // Eliminar el pedido de la tabla detalle pedido y se asegura que el stock de los productos eliminados aumente en la cantidad correspondiente
            detallePedidoService.eliminarPorPedido(idPedido);
            pedidoDAO.eliminarPedido(idPedido);

        } catch (DAOException e) { throw new ServiceException("No se puedo eliminar el pedido", e); }
    }
}