package service.impl;

import dao.impl.PedidoDAO;
import exception.DAOException;
import exception.ServiceException;
import model.Cliente;
import model.Pedido;
import service.interfaces.IPedidoService;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;

public class PedidoService implements IPedidoService {
    private final Connection connection;
    private final PedidoDAO pedidoDAO;
    private final ClienteService clienteService;

    public PedidoService(Connection connection, ClienteService clienteService) {
        this.connection = connection;
        this.pedidoDAO = new PedidoDAO(connection);
        this.clienteService = clienteService;
    }

    @Override
    public void crearPedido(int id_cliente) throws ServiceException {
        try{
            pedidoDAO.crearPedido( new Pedido(id_cliente));

        } catch (DAOException e) { throw new ServiceException("Error Service: Fallo durante insert pedido", e); }
    }

    @Override
    public Pedido buscarPedidoID(int id_pedido) throws ServiceException {
        try{
            Pedido pedido_id_cliente = pedidoDAO.buscarPedidoID(id_pedido);
            if(pedido_id_cliente == null){ throw new ServiceException("No existe el pedido con id: " + id_pedido ); }

            Cliente cliente = clienteService.buscarClienteID(pedido_id_cliente.getId_cliente());

            return new Pedido( pedido_id_cliente.getId(), cliente, pedido_id_cliente.getFecha());

        } catch (DAOException e) { throw new ServiceException("Error Service: Fallo durante insert pedido", e); }
    }

    @Override
    public List<Pedido> listarPedidos() {
        return List.of();
    }

    @Override
    public List<Pedido> listarPedidosPorCliente(int id_cliente) throws ServiceException {
        return List.of();
    }

    @Override
    public void modificarIdCliente(int id_cliente, int id_pedido) throws ServiceException {

    }

    @Override
    public void modificarFecha(Timestamp fecha, int id_pedido) throws ServiceException {

    }

    @Override
    public void eliminarPedido(int id_pedido) throws ServiceException {

    }
}