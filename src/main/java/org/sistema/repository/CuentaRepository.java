package org.sistema.repository;

import org.sistema.entity.Cuenta;

public class CuentaRepository extends Repository<Cuenta, Integer> {
    public CuentaRepository(){}

    @Override
    protected Integer getId(Cuenta entity) {
        return entity.getIdCuenta();
    }
}
