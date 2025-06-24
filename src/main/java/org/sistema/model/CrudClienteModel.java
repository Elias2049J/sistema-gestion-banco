package org.sistema.model;

import org.sistema.entity.Cliente;
import org.sistema.entity.Cuenta;
import org.sistema.interfaces.RepositoryInterface;
import org.sistema.persistencia.PersistenciaCliente;
import org.sistema.persistencia.PersistenciaCuenta;
import org.sistema.repository.Repository;
import org.sistema.interfaces.CrudInterface;

import java.util.ArrayList;
import java.util.List;

public class CrudClienteModel implements CrudInterface<Cliente, Integer> {
    private RepositoryInterface<Cliente, Integer> clienteRepository;
    private RepositoryInterface<Cuenta, Integer> cuentaRepository;

    public CrudClienteModel() {
        this.clienteRepository = new Repository<>(new PersistenciaCliente(), Cliente::getIdCliente);
        this.cuentaRepository = new Repository<>(new PersistenciaCuenta(), Cuenta::getIdCuenta);
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
        List<Integer> lista = new ArrayList<>();

        for (Cuenta c : cuentaRepository.findAll()) {
            if (c.getCliente() != null && c.getCliente().getIdCliente() != null &&
                    c.getCliente().getIdCliente().equals(id)) {
                lista.add(c.getIdCuenta());
            }
        }

        for (Integer idCuenta : lista) {
            cuentaRepository.delete(idCuenta);
        }
        return clienteRepository.delete(id);
    }

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }
}
