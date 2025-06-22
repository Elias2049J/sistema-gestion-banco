package org.sistema.use_case;

import org.sistema.entity.*;

import java.util.List;

public interface CuentaUseCase {
    boolean crearCorriente(double saldo, String tipoMoneda,double limiteSobreGiro, Empresa empresa);
    boolean crearAhorro (double saldo, String tipoMoneda, Natural natural, double tasaInteres);
    boolean updateCorriente(double saldo, String tipoMoneda,double limiteSobreGiro, Empresa empresa);
    boolean updateAhorro(double saldo, String tipoMoneda, Natural natural, double tasaInteres);
    boolean saveAhorroListFromList(List<Ahorro> lista);
    boolean saveCorrienteListFromList(List<Corriente> lista);
    Ahorro getAhorroById(Integer id);
    Corriente getCorrienteById(Integer id);
    boolean deleteAhorro(Integer id);
    boolean deleteCorriente(Integer id);
}
