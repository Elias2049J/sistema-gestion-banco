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

public class CrudCuentaModel implements CrudInterface<Cuenta, Integer> {
    private RepositoryInterface<Cuenta, Integer> cuentaRepository;
    private RepositoryInterface<Cliente, Integer> clienteRepository;

    public CrudCuentaModel() {
        this.cuentaRepository = new Repository<>(new PersistenciaCuenta(), Cuenta::getIdCuenta);
        this.clienteRepository = new Repository<>(new PersistenciaCliente(), Cliente::getIdCliente);
    }

    @Override
    public boolean crear(Cuenta objeto) {
        Integer idCuenta;
        Cliente c = objeto.getCliente();
        List<Cuenta> cuentas;
        if (c.getCuentas() == null || c.getCuentas().isEmpty()) {
            cuentas = new ArrayList<>();
            cuentas.add(objeto);
            c.setCuentas(cuentas);
        } else c.getCuentas().add(objeto);

        if (cuentaRepository.findAll().isEmpty()) {
            idCuenta = 1;
        } else idCuenta = cuentaRepository.findAll().getLast().getIdCuenta()+1;
        objeto.setIdCuenta(idCuenta);
        if (!objeto.esValido()) return false;
        return cuentaRepository.save(objeto);
    }

    @Override
    public boolean update(Cuenta objeto) {
        if (!objeto.esValido()) return false;
        return cuentaRepository.update(objeto);
    }

    @Override
    public Cuenta getById(Integer id) {
        return cuentaRepository.getById(id);
    }

    @Override
    public boolean delete(Integer id) {
        return cuentaRepository.delete(id);
    }

    @Override
    public List<Cuenta> findAll() {
        return cuentaRepository.findAll();
    }
}
