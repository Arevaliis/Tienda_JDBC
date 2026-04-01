package service.impl;

import dao.impl.ClienteDAO;

public class ClienteService {
    private final ClienteDAO clienteDAO;

    public ClienteService(ClienteDAO clienteDAO) { this.clienteDAO = clienteDAO; }
}
