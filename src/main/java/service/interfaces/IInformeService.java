package service.interfaces;

import exception.ServiceException;
import model.Cliente;
import model.Producto;
import model.ProductoInforme;

import java.util.List;

/**
 * Servicio encargado de generar informes y estadísticas del sistema de gestión de pedidos.
 */
public interface IInformeService {

    /**
     * Obtiene el producto más vendido del sistema. El cálculo se realiza basándose en la
     * cantidad total vendida en los detalles de pedido.
     *
     * @return El producto con mayor número de unidades vendidas
     * @throws ServiceException sí ocurre un error durante el cálculo o acceso a datos
     */
    ProductoInforme obtenerProductoMasVendido() throws ServiceException;

    /**
     * Obtiene el cliente que ha realizado más pedidos.
     *
     * @return El {@link Cliente} con mayor número de pedidos registrados
     * @throws ServiceException sí ocurre un error durante el cálculo o acceso a datos
     */
    Cliente obtenerClienteConMasPedidos() throws ServiceException;

    /**
     * Calcula el total facturado en el sistema. El total se obtiene sumando el importe de todos los pedidos,
     * teniendo en cuenta las cantidades y precios de los productos en cada detalle.
     *
     * @return el importe total facturado
     * @throws ServiceException sí ocurre un error durante el cálculo o acceso a datos
     */
    double obtenerTotalFacturado() throws ServiceException;

    /**
     * Obtiene una lista con los 5 productos más vendidos. Los productos se devuelven
     * ordenados de mayor a menor según la cantidad vendida.
     *
     * @return lista de los 5 productos más vendidos
     * @throws ServiceException sí ocurre un error durante el cálculo o acceso a datos
     */
    List<ProductoInforme> top5ProductosMasVendidos() throws ServiceException;
}