package model;

import java.util.Objects;

/**
 * Clase que representa un cliente del sistema.
 */
public class Cliente {
    private int id;
    private String nombre;
    private String apellido;

    /**
     * Constructor completo de Cliente.
     *
     * @param id identificador único del cliente
     * @param nombre nombre del cliente
     * @param apellido apellido del cliente
     */
    public Cliente(int id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    /**
     * Constructor para creación de cliente sin ID (por ejemplo antes de persistir en BD).
     *
     * @param nombre nombre del cliente
     * @param apellido apellido del cliente
     */
    public Cliente(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    /**
     * Devuelve el identificador del cliente.
     *
     * @return id del cliente
     */
    public int getId() { return id;}

    /**
     * Devuelve el nombre del cliente.
     *
     * @return nombre del cliente
     */
    public String getNombre() { return nombre; }

    /**
     * Devuelve el apellido del cliente.
     *
     * @return apellido del cliente
     */
    public String getApellido() { return apellido; }

    /**
     * Modifica el apellido del cliente.
     *
     * @param apellido nuevo apellido
     */
    public void setApellido(String apellido) { this.apellido = apellido; }

    /**
     * Modifica el nombre del cliente.
     *
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) { this.nombre = nombre; }

    /**
     * Compara dos clientes por su identificador.
     *
     * @param o objeto a comparar
     * @return true si los clientes tienen el mismo id
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Cliente cliente)) return false;
        return id == cliente.id;
    }

    /**
     * Genera el código hash basado en el identificador del cliente.
     *
     * @return hash del cliente
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    /**
     * Devuelve una representación en texto del cliente.
     *
     * @return cadena con los datos del cliente
     */
    @Override
    public String toString() {
        return "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido;
    }
}