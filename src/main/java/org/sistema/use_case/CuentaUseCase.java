package org.sistema.use_case;

import org.sistema.entity.*;

import java.util.List;

public interface CuentaUseCase {
    boolean crearCorriente(double saldo, String tipoMoneda,double limiteSobreGiro, ClienteEmpresa clienteEmpresa);
    boolean crearAhorro (double saldo, String tipoMoneda, ClienteNatural clienteNatural, double tasaInteres);
    boolean updateCorriente(double saldo, String tipoMoneda,double limiteSobreGiro, ClienteEmpresa clienteEmpresa);
    boolean updateAhorro(double saldo, String tipoMoneda, ClienteNatural clienteNatural, double tasaInteres);
    boolean saveAhorroListFromList(List<CuentaAhorro> lista);
    boolean saveCorrienteListFromList(List<CuentaCorriente> lista);
    CuentaAhorro getAhorroById(Integer id);
    CuentaCorriente getCorrienteById(Integer id);
    boolean deleteAhorro(Integer id);
    boolean deleteCorriente(Integer id);
}
