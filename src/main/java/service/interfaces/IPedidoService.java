package service.interfaces;

import exception.ServiceException;
import model.Pedido;
import java.sql.Timestamp;
import java.util.List;

/**
 * Interfaz que define los servicios relacionados con la gestión de pedidos.
 * Actúa como capa intermedia entre la lógica de negocio y el acceso a datos.
 */
public interface IPedidoService {

    /**
     * Crea un nuevo pedido.
     *
     * @param id_cliente identificador del cliente asociado
     * @throws ServiceException sí ocurre un error durante la creación
     */
    void crearPedido(int id_cliente) throws ServiceException;

    /**
     * Busca un pedido por su identificador.
     *
     * @param id_pedido identificador del pedido
     * @return pedido encontrado
     * @throws ServiceException si ocurre un error o no se encuentra el pedido
     */
    Pedido buscarPedidoID(int id_pedido) throws ServiceException;

    /**
     * Obtiene la lista de todos los pedidos.
     *
     * @return lista de pedidos
     */
    List<Pedido> listarPedidos();

    /**
     * Obtiene todos los pedidos de un cliente específico.
     *
     * @param id_cliente identificador del cliente
     * @return lista de pedidos del cliente
     * @throws ServiceException si ocurre un error durante la consulta
     */
    List<Pedido> listarPedidosPorCliente(int id_cliente) throws ServiceException;

    /**
     * Modifica el cliente asociado a un pedido.
     *
     * @param id_cliente nuevo id del cliente
     * @param id_pedido identificador del pedido
     * @throws ServiceException sí ocurre un error durante la modificación
     */
    void modificarIdCliente(int id_cliente, int id_pedido) throws ServiceException;

    /**
     * Modifica la fecha de un pedido.
     *
     * @param fecha nueva fecha del pedido
     * @param id_pedido identificador del pedido
     * @throws ServiceException sí ocurre un error durante la modificación
     */
    void modificarFecha(Timestamp fecha, int id_pedido) throws ServiceException;

    /**
     * Elimina un pedido por su identificador.
     *
     * @param id_pedido identificador del pedido
     * @throws ServiceException sí ocurre un error durante la eliminación
     */
    void eliminarPedido(int id_pedido) throws ServiceException;
}