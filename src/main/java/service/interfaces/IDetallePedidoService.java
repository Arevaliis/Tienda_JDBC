package service.interfaces;

import exception.DAOException;
import exception.ServiceException;
import model.DetallePedido;

import java.util.List;

/**
 * Interfaz de servicio para la gestión de detalles de pedidos.
 * Contiene la lógica de negocio relacionada con las líneas de pedido.
 */
public interface IDetallePedidoService {

    /**
     * Inserta un nuevo detalle de pedido.
     *
     * @param id_pedido   Identificador del pedido
     * @param id_producto Identificador del producto
     * @param cantidad    Cantidad del producto en el pedido
     * @throws DAOException Si ocurre un error en la base de datos
     */
    void insertarDetallePedido(int id_pedido, int id_producto, int cantidad) throws ServiceException;

    /**
     * Obtiene todos los detalles de un pedido.
     *
     * @param idPedido ID del pedido
     * @return Lista de detalles del pedido
     * @throws ServiceException Si ocurre un error en la operación
     */
    List<DetallePedido> listarDetallesPedido(int idPedido) throws ServiceException;

    /**
     * Obtiene un detalle concreto de un pedido por su clave compuesta.
     *
     * @param idPedido   ID del pedido
     * @param idProducto ID del producto
     * @return DetallePedido encontrado
     * @throws ServiceException Si ocurre un error en la operación
     */
    DetallePedido listarDetallePorId(int idPedido, int idProducto) throws ServiceException;

    /**
     * Modifica la cantidad de un producto dentro de un pedido.
     *
     * @param idPedido   ID del pedido
     * @param idProducto ID del producto
     * @param cantidad   Nueva cantidad
     * @throws ServiceException Si ocurre un error en la operación
     */
    void modificarCantidadProducto(int idPedido, int idProducto, int cantidad) throws ServiceException;

    /**
     * Elimina un producto concreto de un pedido.
     *
     * @param idPedido   ID del pedido
     * @param idProducto ID del producto
     * @throws ServiceException Si ocurre un error en la operación
     */
    void eliminarDetallesPedido(int idPedido, int idProducto) throws ServiceException;

    /**
     * Elimina todos los detalles de un pedido.
     *
     * @param idPedido ID del pedido
     * @throws ServiceException Si ocurre un error en la operación
     */
    void eliminarPorPedido(int idPedido) throws ServiceException;

    /**
     * Calcula el total de un pedido sumando cantidad * precio unitario.
     *
     * @param idPedido ID del pedido
     * @return Importe total del pedido
     * @throws ServiceException Si ocurre un error en la operación
     */
    double obtenerTotalPedido(int idPedido) throws ServiceException;
}