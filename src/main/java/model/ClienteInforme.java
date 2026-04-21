package model;

public class ClienteInforme {
    private Cliente cliente;
    private int totalComprado;

    public ClienteInforme(Cliente cliente, int totalVendido) {
        this.cliente = cliente;
        this.totalComprado = totalVendido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getTotalComprado() {
        return totalComprado;
    }
}
