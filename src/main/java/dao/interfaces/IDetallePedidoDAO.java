package dao.interfaces;

import exception.DAOException;
import model.DetallePedido;

import java.util.List;

/**
 * Interfaz DAO para gestionar los detalles de pedidos.
 */
public interface IDetallePedidoDAO {

    /**
     * Inserta un nuevo detalle de pedido.
     *
     * @param detalle Objeto DetallePedido a insertar
     * @throws DAOException Si ocurre un error en la base de datos
     */
    void insertar(DetallePedido detalle) throws DAOException;

    /**
     * Obtiene todos los detalles de un pedido.
     *
     * @param idPedido ID del pedido
     * @return Lista de detalles
     * @throws DAOException Si ocurre un error en la base de datos
     */
    List<DetallePedido> listarDetallesPedido(int idPedido) throws DAOException;

    /**
     * Obtiene un detalle concreto por su clave primaria.
     *
     * @param idPedido   ID del pedido
     * @param idProducto ID del producto
     * @return DetallePedido o null si no existe
     * @throws DAOException Si ocurre un error en la base de datos
     */
    DetallePedido listarDetallePorId(int idPedido, int idProducto) throws DAOException;

    /**
     * Actualiza la cantidad de un producto en un pedido.
     *
     * @param detallePedido objeto DetallePedido con la nueva cantidad
     * @throws DAOException Si ocurre un error en la base de datos
     */
    void modificarCantidadProducto(DetallePedido detallePedido) throws DAOException;

    /**
     * Elimina un detalle concreto de un pedido.
     *
     * @param idPedido   ID del pedido
     * @param idProducto ID del producto
     * @throws DAOException Si ocurre un error en la base de datos
     */
    void eliminarDetallesPedido(int idPedido, int idProducto) throws DAOException;

    /**
     * Elimina todos los detalles de un pedido.
     *
     * @param idPedido ID del pedido
     * @throws DAOException Si ocurre un error en la base de datos
     */
    void eliminarPorPedido(int idPedido) throws DAOException;

    /**
     * Calcula el total de un pedido.
     *
     * @param idPedido ID del pedido
     * @return Importe total
     * @throws DAOException Si ocurre un error en la base de datos
     */
    double obtenerTotalPedido(int idPedido) throws DAOException;

    /**
     * Obtiene todos los detalles de todos los pedidos.
     *
     * @return Lista con todos los detalles de todos los pedidos
     * @throws DAOException Si ocurre un error en la base de datos
     */
    List<DetallePedido> listarDetallesPedidos() throws DAOException;
}