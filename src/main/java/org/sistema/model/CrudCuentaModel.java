package org.sistema.model;

import org.sistema.entity.Cuenta;
import org.sistema.interfaces.RepositoryInterface;
import org.sistema.persistencia.PersistenciaCuenta;
import org.sistema.repository.Repository;
import org.sistema.interfaces.CrudInterface;

import java.util.List;

public class CrudCuentaModel implements CrudInterface<Cuenta, Integer> {
    private RepositoryInterface<Cuenta, Integer> cuentaRepository;

    public CrudCuentaModel() {
        this.cuentaRepository = new Repository<>(new PersistenciaCuenta(), Cuenta::getIdCuenta);
    }

    @Override
    public boolean crear(Cuenta objeto) {
        Integer idCuenta;
        if (cuentaRepository.findAll().isEmpty()) {
            idCuenta = 1;
        } else idCuenta = cuentaRepository.findAll().getLast().getIdCuenta()+1;
        objeto.setIdCuenta(idCuenta);
        return cuentaRepository.save(objeto);
    }

    @Override
    public boolean update(Cuenta objeto) {
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
