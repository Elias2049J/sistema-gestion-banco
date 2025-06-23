package org.sistema.model;

import org.sistema.entity.Cliente;
import org.sistema.interfaces.PersistenceInterface;
import org.sistema.interfaces.RepositoryInterface;
import org.sistema.persistencia.PersistenciaCliente;
import org.sistema.repository.ClienteRepository;
import org.sistema.use_case.GestionUseCase;

public class GestionClienteModel implements GestionUseCase<Cliente, Integer> {
    protected PersistenceInterface<Cliente> persistenciaCliente = new PersistenciaCliente();
    protected RepositoryInterface<Cliente, Integer> clienteRepository = new ClienteRepository();

    @Override
    public boolean crear(Cliente objeto) {
        return false;
    }

    @Override
    public boolean update(Cliente objeto) {
        return false;
    }

    @Override
    public boolean getById(Integer integer) {
        return false;
    }

    @Override
    public boolean deleteById(Integer integer) {
        return false;
    }
}
