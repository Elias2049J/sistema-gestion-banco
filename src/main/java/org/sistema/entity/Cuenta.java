package org.sistema.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cuenta {
    private Integer idCuenta;
    private Double saldo;
    private String tipoMoneda;
    private Cliente cliente;
    private String tipoCuenta;
    private Double tasaInteres;
    private Double limiteSobreGiros;

    public boolean esValido() {
        if (idCuenta == null || saldo == null ||
                tipoMoneda == null || tipoMoneda.trim().isEmpty() ||
                cliente == null || !cliente.esValido() ||
                tipoCuenta == null || tipoCuenta.trim().isEmpty()) {
            return false;
        }

        if (tipoCuenta.equalsIgnoreCase("ahorro")) {
            return esValidoAhorro();
        } else if (tipoCuenta.equalsIgnoreCase("corriente")) {
            return esValidoCorriente();
        }
        return false;
    }

    private boolean esValidoAhorro() {
        return tasaInteres != null && tasaInteres > 0;
    }

    private boolean esValidoCorriente() {
        return limiteSobreGiros != null && limiteSobreGiros >= 0;
    }
}
