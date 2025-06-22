package org.sistema.model;

import org.sistema.entity.Ahorro;
import org.sistema.entity.Corriente;
import org.sistema.entity.Empresa;
import org.sistema.entity.Natural;
import org.sistema.use_case.CuentaUseCase;

import java.util.List;

public class CuentaModel implements CuentaUseCase {
    @Override
    public boolean crearCorriente(double saldo, String tipoMoneda, double limiteSobreGiro, Empresa empresa) {
        return false;
    }

    @Override
    public boolean crearAhorro(double saldo, String tipoMoneda, Natural natural, double tasaInteres) {
        return false;
    }

    @Override
    public boolean updateCorriente(double saldo, String tipoMoneda, double limiteSobreGiro, Empresa empresa) {
        return false;
    }

    @Override
    public boolean updateAhorro(double saldo, String tipoMoneda, Natural natural, double tasaInteres) {
        return false;
    }

    @Override
    public boolean saveAhorroListFromList(List<Ahorro> lista) {
        return false;
    }

    @Override
    public boolean saveCorrienteListFromList(List<Corriente> lista) {
        return false;
    }

    @Override
    public Ahorro getAhorroById(Integer id) {
        return null;
    }

    @Override
    public Corriente getCorrienteById(Integer id) {
        return null;
    }

    @Override
    public boolean deleteAhorro(Integer id) {
        return false;
    }

    @Override
    public boolean deleteCorriente(Integer id) {
        return false;
    }
}
