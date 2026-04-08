package model;

// TODO -> COMPROBAR QUE PASA CUANDO ELIMINAMOS PEDIDO O PRODUCTO (PK) QUE SEA FK EN DETALLE PEDIDOS.
// TODO -> AL INSERT NO PASAR PRECIO SI NO COGERLO DE LA TABLA PRODUCTO

import java.util.Objects;

/**
 * Representa una línea de detalle dentro de un pedido.
 * Contiene la relación entre un pedido y un producto, junto con la cantidad
 * y el precio unitario aplicado en ese momento.
 */
public class DetallePedido {
    private int idPedido;
    private int idProducto;
    private Pedido pedido;
    private Producto producto;
    private int cantidad;
    private double precioUnitario;

    /**
     * Constructor básico sin precio unitario.
     *
     * @param idPedido   ID del pedido
     * @param idProducto ID del producto
     * @param cantidad   Cantidad del producto
     */
    public DetallePedido(int idPedido, int idProducto, int cantidad) {
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    /**
     * Constructor con precio unitario.
     *
     * @param idPedido        ID del pedido
     * @param idProducto      ID del producto
     * @param cantidad        Cantidad del producto
     * @param precioUnitario  Precio por unidad
     */
    public DetallePedido(int idPedido, int idProducto, int cantidad, double precioUnitario) {
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    /**
     * Constructor completo usando objetos.
     *
     * @param pedido          Pedido asociado
     * @param producto        Producto asociado
     * @param cantidad        Cantidad del producto
     * @param precioUnitario  Precio por unidad
     */
    public DetallePedido(Pedido pedido, Producto producto, int cantidad, double precioUnitario) {
        this.pedido = pedido;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    /** @return ID del pedido */
    public int getIdPedido() {
        return idPedido;
    }

    /** @return Precio unitario */
    public double getPrecioUnitario() {
        return precioUnitario;
    }

    /** @return Cantidad del producto */
    public int getCantidad() {
        return cantidad;
    }

    /** @return Producto asociado */
    public Producto getProducto() {
        return producto;
    }

    /** @return Pedido asociado */
    public Pedido getPedido() {
        return pedido;
    }

    /** @return ID del producto */
    public int getIdProducto() {
        return idProducto;
    }

    /**
     * Asigna el pedido.
     *
     * @param pedido Pedido a asociar
     */
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    /**
     * Asigna el producto.
     *
     * @param producto Producto a asociar
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * Establece la cantidad del producto.
     *
     * @param cantidad Nueva cantidad
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Establece el precio unitario.
     *
     * @param precioUnitario Nuevo precio por unidad
     */
    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    /**
     * Compara dos detalles de pedido.
     * Se consideran iguales si tienen el mismo idPedido e idProducto.
     *
     * @param o Objeto a comparar
     * @return true si son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DetallePedido that)) return false;
        return idPedido == that.idPedido && idProducto == that.idProducto;
    }

    /**
     * Genera el código hash basado en idPedido e idProducto.
     *
     * @return Código hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(idPedido, idProducto);
    }

    /**
     * Representación en texto del detalle del pedido.
     *
     * @return Cadena con los datos del detalle
     */
    @Override
    public String toString() {
        return "DetallePedido: " +
                "pedido=" + pedido +
                ", producto=" + producto +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario;
    }
}