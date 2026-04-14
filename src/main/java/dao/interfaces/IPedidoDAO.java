package dao.interfaces;

import exception.DAOException;
import model.Pedido;
import java.util.List;

/**
 * Interfaz que define las operaciones de acceso a datos para la entidad Pedido.
 * Se encarga de la comunicación con la base de datos.
 */
public interface IPedidoDAO {

    /**
     * Inserta un nuevo pedido en la base de datos.
     *
     * @param pedido objeto pedido a crear
     * @throws DAOException sí ocurre un error durante la inserción
     */
    void crearPedido(Pedido pedido) throws DAOException;

    /**
     * Busca un pedido por su identificador.
     *
     * @param idPedido identificador del pedido
     * @return pedido encontrado
     * @throws DAOException si ocurre un error o no se encuentra el pedido
     */
    Pedido buscarPedidoID(int idPedido) throws DAOException;

    /**
     * Obtiene todos los pedidos almacenados.
     *
     * @return lista de pedidos
     * @throws DAOException si ocurre un error durante la consulta
     */
    List<Pedido> listarPedidos() throws DAOException;

    /**
     * Obtiene los pedidos asociados a un cliente.
     *
     * @param idCliente identificador del cliente
     * @return lista de pedidos del cliente
     * @throws DAOException si ocurre un error durante la consulta
     */
    List<Pedido> listarPedidosPorCliente(int idCliente) throws DAOException;

    /**
     * Actualiza un pedido existente.
     *
     * @param pedido objeto pedido con los datos actualizados
     * @throws DAOException sí ocurre un error durante la actualización
     */
    void actualizarPedido(Pedido pedido) throws DAOException;

    /**
     * Elimina un pedido por su identificador.
     *
     * @param idPedido identificador del pedido
     * @throws DAOException sí ocurre un error durante la eliminación
     */
    void eliminarPedido(int idPedido) throws DAOException;

}