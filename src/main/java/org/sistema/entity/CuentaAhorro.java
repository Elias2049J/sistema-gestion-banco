package org.sistema.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
public class CuentaAhorro extends Cuenta {
    private double tasaInteres;

    public CuentaAhorro(Integer idCuenta, double saldo, String tipoMoneda,Cliente cliente, double tasaInteres){
        super(idCuenta, saldo, tipoMoneda, cliente);
        this.tasaInteres = tasaInteres;
    }

    @Override
    public boolean esValido(){
        if (tasaInteres < 0) return false;
        return super.esValido();
    }
}
