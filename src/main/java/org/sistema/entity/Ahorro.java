package org.sistema.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
public class Ahorro extends Cuenta {
    private Natural natural;
    private double tasaInteres;

    public Ahorro(Integer idCuenta, double saldo, String tipoMoneda, double tasaInteres, Natural natural){
        super(idCuenta, saldo,tipoMoneda);
        this.natural = natural;
        this.tasaInteres = tasaInteres;
    }
}
