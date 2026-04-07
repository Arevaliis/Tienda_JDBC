package model;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * Clase que representa un pedido en el sistema.
 * Contiene la información básica del pedido y su relación con el cliente.
 */
public class Pedido {
    private int id;
    private int id_cliente;
    private Cliente cliente;
    private Timestamp fecha;

    /**
     * Constructor sin ID (útil para inserciones en base de datos).
     *
     * @param id_cliente id del cliente
     */
    public Pedido(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    /**
     * Constructor completo con ID y cliente por ID.
     *
     * @param id identificador del pedido
     * @param id_cliente id del cliente
     * @param fecha fecha del pedido
     */
    public Pedido(int id, int id_cliente, Timestamp fecha) {
        this.id = id;
        this.id_cliente = id_cliente;
        this.fecha = fecha;
    }

    /**
     * Constructor completo con objeto cliente.
     *
     * @param id identificador del pedido
     * @param cliente objeto cliente
     * @param fecha fecha del pedido
     */
    public Pedido(int id, Cliente cliente, Timestamp fecha) {
        this.id = id;
        this.cliente = cliente;
        this.fecha = fecha;
    }

    /**
     * @return id del pedido
     */
    public int getId() {
        return id;
    }

    /**
     * @return id del cliente
     */
    public int getId_cliente() {
        return id_cliente;
    }

    /**
     * @return objeto cliente asociado
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @return fecha del pedido
     */
    public Timestamp getFecha() {
        return fecha;
    }

    /**
     * Establece el ID del cliente.
     *
     * @param id_cliente nuevo id del cliente
     */
    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    /**
     * Establece el cliente.
     *
     * @param cliente objeto cliente
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Establece la fecha del pedido.
     *
     * @param fecha nueva fecha
     */
    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    /**
     * Compara pedidos por su id.
     *
     * @param o objeto a comparar
     * @return true si tienen el mismo id
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pedido pedido)) return false;
        return id == pedido.id;
    }

    /**
     * Genera el hash basado en el id.
     *
     * @return hash del objeto
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    /**
     * Representación en texto del pedido.
     *
     * @return cadena con los datos del pedido
     */
    @Override
    public String toString() {
        return "id=" + id +
                ", cliente=" + cliente +
                ", fecha=" + fecha;
    }
}
