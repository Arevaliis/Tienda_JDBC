package model;

import java.util.Objects;

public class Email {
    private int id;
    private String email;
    private int idCliente;
    private Cliente cliente;

    public Email(String email, int idCliente) {
        this.email = email;
        this.idCliente = idCliente;
    }

    public Email(int id, String email, int idCliente) {
        this.id = id;
        this.email = email;
        this.idCliente = idCliente;
    }

    public Email(int id, String email, Cliente cliente) {
        this.id = id;
        this.email = email;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Email email)) return false;
        return id == email.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Cliente: " + cliente.getNombre() +
                " -> Email= " + email +
                ", id= " + id;
    }
}