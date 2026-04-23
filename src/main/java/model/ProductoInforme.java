package model;

/**
 * Clase auxiliar utilizada para generar informes de productos.
 *
 * <p>Asocia un objeto {@link Producto} con la cantidad total vendida,
 * permitiendo representar de forma resumida la información necesaria
 * para reportes, estadísticas o rankings de ventas.</p>
 */
public class ProductoInforme {

    /**
     * Producto asociado al informe.
     */
    private final Producto producto;

    /**
     * Cantidad total vendida del producto.
     */
    private final int totalVendido;

    /**
     * Constructor que inicializa el producto y su total vendido.
     *
     * @param producto Producto del informe
     * @param totalVendido Cantidad total vendida del producto
     */
    public ProductoInforme(Producto producto, int totalVendido) {
        this.producto = producto;
        this.totalVendido = totalVendido;
    }

    /**
     * Devuelve el producto asociado al informe.
     *
     * @return Producto del informe
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Devuelve la cantidad total vendida del producto.
     *
     * @return Total vendido
     */
    public int getTotalVendido() {
        return totalVendido;
    }
}