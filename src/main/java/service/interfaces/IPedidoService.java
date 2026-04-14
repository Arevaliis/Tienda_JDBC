package service.interfaces;

import exception.ServiceException;
import model.Pedido;
import java.util.List;

/**
 * Interfaz que define los servicios relacionados con la gestión de pedidos.
 * Actúa como capa intermedia entre la lógica de negocio y el acceso a datos.
 */
public interface IPedidoService {

    /**
     * Crea un nuevo pedido.
     *
     * @param idCliente identificador del cliente asociado
     * @throws ServiceException sí ocurre un error durante la creación
     */
    void crearPedido(int idCliente) throws ServiceException;

    /**
     * Busca un pedido por su identificador.
     *
     * @param idPedido identificador del pedido
     * @return pedido encontrado
     * @throws ServiceException si ocurre un error o no se encuentra el pedido
     */
    Pedido buscarPedidoID(int idPedido) throws ServiceException;

    /**
     * Obtiene la lista de todos los pedidos.
     *
     * @return lista de pedidos
     */
    List<Pedido> listarPedidos();

    /**
     * Obtiene todos los pedidos de un cliente específico.
     *
     * @param idCliente identificador del cliente
     * @return lista de pedidos del cliente
     * @throws ServiceException si ocurre un error durante la consulta
     */
    List<Pedido> listarPedidosPorCliente(int idCliente) throws ServiceException;

    /**
     * Modifica el cliente asociado a un pedido.
     *
     * @param idCliente nuevo id del cliente
     * @param idPedido identificador del pedido
     * @throws ServiceException sí ocurre un error durante la modificación
     */
    void modificarIdCliente(int idCliente, int idPedido) throws ServiceException;

    /**
     * Modifica la fecha de un pedido por la fecha actual.
     *
     * @param idPedido identificador del pedido
     * @throws ServiceException sí ocurre un error durante la modificación
     */
    void modificarFecha(int idPedido) throws ServiceException;

    /**
     * Elimina un pedido por su identificador.
     *
     * @param idPedido identificador del pedido
     * @throws ServiceException sí ocurre un error durante la eliminación
     */
    void eliminarPedido(int idPedido) throws ServiceException;
}