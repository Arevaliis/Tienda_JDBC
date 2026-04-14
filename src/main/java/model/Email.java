package model;

import java.util.Objects;

/**
 * Clase que representa la entidad Email.
 */
 public class Email {
    private int id;
    private String email;
    private int idCliente;
    private Cliente cliente;

    /**
     * Constructor para crear un email sin id (para inserciones).
     */
    public Email(String email, int idCliente) {
        this.email = email;
        this.idCliente = idCliente;
    }

    /**
     * Constructor completo con id y cliente por id int
     */
    public Email(int id, String email, int idCliente) {
        this.id = id;
        this.email = email;
        this.idCliente = idCliente;
    }

    /**
     * Constructor completo con objeto Cliente.
     */
    public Email(int id, String email, Cliente cliente) {
        this.id = id;
        this.email = email;
        this.cliente = cliente;
    }

    /**
     * Obtiene el identificador del email.
     *
     * @return id del email
     */
    public int getId() { return id; }

    /**
     * Obtiene la dirección de correo electrónico.
     *
     * @return email
     */
    public String getEmail() { return email; }

    /**
     * Modifica la dirección de correo electrónico.
     *
     * @param email nueva dirección de email
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Obtiene el identificador del cliente asociado.
     *
     * @return id del cliente
     */
    public int getIdCliente() { return idCliente; }

    /**
     * Modifica el identificador del cliente asociado.
     *
     * @param idCliente nuevo id del cliente
     */
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    /**
     * Obtiene el objeto Cliente asociado.
     *
     * @return cliente asociado
     */
    public Cliente getCliente() { return cliente; }

    /**
     * Establece el objeto Cliente asociado.
     *
     * @param cliente cliente a asociar
     */
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    /**
     * Compara dos objetos Email por su identificador.
     *
     * @param o objeto a comparar
     * @return true si tienen el mismo id, false en caso contrario
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Email email)) return false;
        return id == email.id;
    }

    /**
     * Genera el código hash basado en el ID del email.
     *
     * @return hash del objeto
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    /**
     * Devuelve una representación en forma de texto del email.
     *
     * @return cadena con información del cliente y email
     */
    @Override
    public String toString() {
        return "Cliente: " + cliente.getNombre() +
                " -> Email= " + email +
                ", id= " + id;
    }
}