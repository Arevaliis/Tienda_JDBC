package service.interfaces;

import exception.ServiceException;
import model.ClienteInforme;
import model.ProductoInforme;
import java.util.List;

/**
 * Servicio encargado de generar informes y estadísticas del sistema de gestión de pedidos.
 */
public interface IInformeService {

    /**
     * Obtiene el producto más vendido del sistema.
     *
     * @return El producto con mayor número de unidades vendidas
     * @throws ServiceException sí ocurre un error durante el cálculo o acceso a datos
     */
    ProductoInforme obtenerProductoMasVendido() throws ServiceException;

    /**
     * Obtiene el cliente que ha realizado más pedidos.
     *
     * @return El cliente con mayor número de pedidos registrados
     * @throws ServiceException sí ocurre un error durante el cálculo o acceso a datos
     */
    ClienteInforme obtenerClienteConMasPedidos() throws ServiceException;

    /**
     * Calcula el total facturado en el sistema.
     *
     * @return el importe total facturado
     * @throws ServiceException sí ocurre un error durante el cálculo o acceso a datos
     */
    double obtenerTotalFacturado() throws ServiceException;

    /**
     * Obtiene una lista con los 5 productos más vendidos.
     *
     * @return lista de los 5 productos más vendidos
     * @throws ServiceException sí ocurre un error durante el cálculo o acceso a datos
     */
    List<ProductoInforme> top5ProductosMasVendidos() throws ServiceException;
}