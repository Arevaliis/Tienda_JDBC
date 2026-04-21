package model;

public class ProductoInforme {
    private Producto producto;
    private int totalVendido;

    public ProductoInforme(Producto producto, int totalVendido) {
        this.producto = producto;
        this.totalVendido = totalVendido;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getTotalVendido() {
        return totalVendido;
    }
}
