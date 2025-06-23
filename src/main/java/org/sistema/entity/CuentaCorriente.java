package org.sistema.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
public class CuentaCorriente extends Cuenta{
    private double limiteSobreGiros;

    public CuentaCorriente(Integer idcuenta, double saldo, String tipoMoneda, Cliente cliente, double limiteSoberGiros){
        super(idcuenta, saldo, tipoMoneda, cliente);
        this.limiteSobreGiros = limiteSoberGiros;
    }

    @Override
    public boolean esValido() {
        if (limiteSobreGiros < 0) return false;
        return super.esValido();
    }
}
