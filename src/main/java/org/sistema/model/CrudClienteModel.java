package org.sistema.model;

import org.sistema.entity.Cliente;
import org.sistema.interfaces.RepositoryInterface;
import org.sistema.persistencia.PersistenciaCliente;
import org.sistema.repository.Repository;
import org.sistema.interfaces.CrudInterface;

import java.util.List;

public class CrudClienteModel implements CrudInterface<Cliente, Integer> {
    private RepositoryInterface<Cliente, Integer> clienteRepository;

    public CrudClienteModel() {
        this.clienteRepository = new Repository<>(new PersistenciaCliente(), Cliente::getIdCliente);
    }

    @Override
    public boolean crear(Cliente objeto) {
        Integer idCliente;
        if (clienteRepository.findAll().isEmpty()) {
            idCliente = 1;
        } else idCliente = clienteRepository.findAll().getLast().getIdCliente()+1;
        objeto.setIdCliente(idCliente);
        return clienteRepository.save(objeto);
    }

    @Override
    public boolean update(Cliente objeto) {
        return clienteRepository.update(objeto);
    }

    @Override
    public Cliente getById(Integer id) {
        return clienteRepository.getById(id);
    }

    @Override
    public boolean delete(Integer id) {
        return clienteRepository.delete(id);
    }

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }
}
