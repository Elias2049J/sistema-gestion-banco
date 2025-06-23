package org.sistema.model;

import org.sistema.entity.*;
import org.sistema.interfaces.PersistenceInterface;
import org.sistema.interfaces.RepositoryInterface;
import org.sistema.persistencia.PersistenciaCuenta;
import org.sistema.repository.CuentaRepository;
import org.sistema.use_case.GestionUseCase;

public class GestionCuentaModel implements GestionUseCase<Cuenta, Integer> {
    protected PersistenceInterface<Cuenta> persistenciaCuenta = new PersistenciaCuenta();
    protected RepositoryInterface<Cuenta, Integer> cuentaRepository = new CuentaRepository();

    @Override
    public boolean crear(Cuenta objeto) {
        return false;
    }

    @Override
    public boolean update(Cuenta objeto) {
        return false;
    }

    @Override
    public boolean getById(Integer integer) {
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        return cuentaRepository.delete(id);
    }
}
