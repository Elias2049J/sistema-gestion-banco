package org.sistema.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
public class ClienteEmpresa extends Cliente{
    private String razonSocial;
    private String ruc;

    public ClienteEmpresa(Integer idCliente, String direccion, List<Cuenta> cuentas,String razonSocial, String ruc){
        super(idCliente, direccion, cuentas);
        this.razonSocial = razonSocial;
        this.ruc = ruc;
    }

    @Override
    public boolean esValido() {
        if (ruc.length() != 11) return false;
        return super.esValido();
    }
}
