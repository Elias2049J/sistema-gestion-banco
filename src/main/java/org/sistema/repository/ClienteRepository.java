package org.sistema.repository;

import org.sistema.entity.Cliente;

public class ClienteRepository extends Repository<Cliente, Integer> {
    public ClienteRepository(){}

    @Override
    protected Integer getId(Cliente entity) {
        return entity.getIdCliente();
    }
}
