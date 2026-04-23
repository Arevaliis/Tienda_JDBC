package model;

/**
 * Clase auxiliar utilizada para generar informes de clientes.
 *
 * <p>Asocia un objeto {@link Cliente} con el total de compras realizadas,
 * permitiendo representar de forma sencilla la información resumida
 * de cada cliente en reportes o estadísticas.</p>
 */
public class ClienteInforme {

    /**
     * Cliente asociado al informe.
     */
    private final Cliente cliente;

    /**
     * Total de compras realizadas por el cliente.
     */
    private final int totalComprado;

    /**
     * Constructor que inicializa el cliente y su total comprado.
     *
     * @param cliente Cliente del informe
     * @param totalVendido Total de compras realizadas por el cliente
     */
    public ClienteInforme(Cliente cliente, int totalVendido) {
        this.cliente = cliente;
        this.totalComprado = totalVendido;
    }

    /**
     * Devuelve el cliente asociado al informe.
     *
     * @return Cliente del informe
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Devuelve el total de compras realizadas por el cliente.
     *
     * @return Total comprado
     */
    public int getTotalComprado() {
        return totalComprado;
    }
}