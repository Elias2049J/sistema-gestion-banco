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
}
