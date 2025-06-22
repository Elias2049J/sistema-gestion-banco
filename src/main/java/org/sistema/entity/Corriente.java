package org.sistema.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
public class Corriente extends Cuenta{
    private double limiteSobreGiros;
    private Empresa empresa;

    public Corriente(Integer idcuenta, double saldo, String tipoMoneda, double limiteSoberGiros, Empresa empresa){
        super(idcuenta, saldo, tipoMoneda);
        this.empresa = empresa;
        this.limiteSobreGiros = limiteSoberGiros;
    }
}
