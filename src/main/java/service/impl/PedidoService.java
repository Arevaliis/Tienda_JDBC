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
    private final Connection connection;
    private final PedidoDAO pedidoDAO;
    private ClienteService clienteService;

    public PedidoService(Connection connection, ClienteService clienteService) {
        this.connection = connection;
        this.pedidoDAO = new PedidoDAO(connection);
        this.clienteService = clienteService;
    }

    @Override
    public void crearPedido(int idCliente) throws ServiceException {
        try{
            pedidoDAO.crearPedido( new Pedido(idCliente));

        } catch (DAOException e) { throw new ServiceException("Error Service: Fallo durante insert pedido", e); }
    }

    @Override
    public Pedido buscarPedidoID(int idPedido) throws ServiceException {
        try{
            Pedido pedidoIdCliente = pedidoDAO.buscarPedidoID(idPedido);
            if(pedidoIdCliente == null){ throw new ServiceException("No existe el pedido con id: " + idPedido ); }

            Cliente cliente = clienteService.buscarClienteID(pedidoIdCliente.getId_cliente());

            return new Pedido( idPedido, cliente, pedidoIdCliente.getFecha());

        } catch (DAOException e) { throw new ServiceException("Error Service: Fallo durante insert pedido", e); }
    }

    @Override
    public List<Pedido> listarPedidos() {
        try{
            List<Pedido> pedidoIdCliente = pedidoDAO.listarPedidos();
            if(pedidoIdCliente.isEmpty()){ throw new ServiceException("No hay registro alguno de pedidos en la base de datos." ); }

            List<Pedido> pedidos = new ArrayList<>();

            for (Pedido pedido: pedidoIdCliente){

                Cliente cliente = clienteService.buscarClienteID(pedido.getId_cliente());

                pedidos.add( new Pedido( pedido.getId(),
                                         cliente,
                                         pedido.getFecha())
                );
            }

           return pedidos;

        } catch (DAOException e) { throw new ServiceException("Error Service: Fallo durante insert pedido", e); }
    }

    @Override
    public List<Pedido> listarPedidosPorCliente(int idCliente) throws ServiceException {

        try{
            List<Pedido> pedidosCliente = pedidoDAO.listarPedidosPorCliente(idCliente);
            if(pedidosCliente.isEmpty()){ throw new ServiceException("No hay registro alguno de pedidos en la base de datos del cliente con id: " + idCliente ); }

            List<Pedido> pedidos = new ArrayList<>();

            for (Pedido pedido: pedidosCliente){

                Cliente cliente = clienteService.buscarClienteID(pedido.getId_cliente());

                pedidos.add(
                            new Pedido( pedido.getId(),
                                        cliente,
                                        pedido.getFecha())
                );
            }

            return pedidos;

        } catch (DAOException e) { throw new ServiceException("Error Service: Fallo durante insert pedido", e); }
    }

    @Override
    public void modificarIdCliente(int idCliente, int idPedido) throws ServiceException {
        try{
            Pedido pedido = buscarPedidoID(idPedido);
            Cliente cliente = clienteService.buscarClienteID(idCliente);

            pedido.setId_cliente(cliente.getId());
            pedidoDAO.actualizarPedido(pedido);

        } catch (DAOException e) { throw new ServiceException("Error Service: Fallo durante update de pedido", e); }
    }

    @Override
    public void modificarFecha(int idPedido) throws ServiceException {
        try{
            Pedido pedido = pedidoDAO.buscarPedidoID(idPedido);
            if(pedido == null){ throw new ServiceException("No existe el pedido con id: " + idPedido ); }

            pedido.setFecha( Timestamp.from(Instant.now()) );
            pedidoDAO.actualizarPedido(pedido);

        } catch (DAOException e) { throw new ServiceException("Error Service: Fallo durante update de pedido", e); }
    }

    @Override
    public void eliminarPedido(int idPedido) throws ServiceException {
        try {
            buscarPedidoID(idPedido);
            pedidoDAO.eliminarPedido(idPedido);

        } catch (DAOException e) { throw new ServiceException("Error Service: Fallo durante delete de pedido", e); }
    }
}