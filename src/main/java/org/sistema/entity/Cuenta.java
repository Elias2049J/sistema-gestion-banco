package org.sistema.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cuenta {
    private Integer idCuenta;
    private double saldo;
    private String tipoMoneda;
    private Cliente cliente;

    public boolean esValido() {
        if (saldo < 0) return false;
        return (tipoMoneda.equals("soles") || (tipoMoneda.equals("dolares")));
    }
}
