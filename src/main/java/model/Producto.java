package model;

import com.google.gson.annotations.Expose;
import java.util.Objects;

/**
 * Clase que representa un producto
 */
public class Producto {
    @Expose
    private int id;

    @Expose
    private String nombre;

    @Expose
    private String descripcion;

    @Expose
    private double precio;

    @Expose
    private int stock;

    /**
     * Constructor para crear un producto sin ID (por ejemplo, antes de insertarlo en BD).
     *
     * @param nombre       nombre del producto
     * @param descripcion  descripción del producto
     * @param precio       precio del producto
     * @param stock        cantidad en stock
     */
    public Producto(String nombre, String descripcion, double precio, int stock) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }

    /**
     * Constructor para crear un producto con ID (por ejemplo, al leer de la base de datos).
     *
     * @param id           identificador del producto
     * @param nombre       nombre del producto
     * @param descripcion  descripción del producto
     * @param precio       precio del producto
     * @param stock        cantidad en stock
     */
    public Producto(int id, String nombre, String descripcion, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }

    /**
     * Obtiene el ID del producto.
     *
     * @return ID del producto
     */
    public int getId() { return id;}

    /**
     * Obtiene el nombre del producto.
     *
     * @return nombre del producto
     */
    public String getNombre() { return nombre; }

    /**
     * Obtiene la descripción del producto.
     *
     * @return descripción del producto
     */
    public String getDescripcion() {  return descripcion; }

    /**
     * Obtiene el precio del producto.
     *
     * @return precio del producto
     */
    public double getPrecio() { return precio; }

    /**
     * Obtiene el stock del producto.
     *
     * @return cantidad en stock
     */
    public int getStock() { return stock; }

    /**
     * Modifica el nombre del producto.
     *
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) { this.nombre = nombre; }

    /**
     * Modifica la descripcion del producto.
     *
     * @param descripcion nueva descripción
     */
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    /**
     * Modifica el precio del producto.
     *
     * @param precio nuevo precio
     */
    public void setPrecio(double precio) { this.precio = precio; }

    /**
     * Modifica el stock del producto.
     *
     * @param stock nueva cantidad en stock
     */
    public void setStock(int stock) { this.stock = stock; }

    /**
     * Compara dos productos por su ID.
     *
     * @param o objeto a comparar
     * @return true si tienen el mismo ID
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Producto producto)) return false;
        return id == producto.id;
    }

    /**
     * Genera el hash del producto basado en su ID.
     *
     * @return hash del producto
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    /**
     * Representación en texto del producto.
     *
     * @return cadena con los datos del producto
     */
    @Override
    public String toString() {
        return "ID: " + id +
                "- Nombre='" + nombre +
                "- descripcion='" + descripcion +
                "- precio=" + precio +
                "- stock=" + stock;
    }
}