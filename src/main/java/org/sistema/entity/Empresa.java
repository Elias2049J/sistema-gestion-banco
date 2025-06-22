package org.sistema.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
public class Empresa extends Cliente{
    private String razonSocial;
    private String ruc;
    private List<Corriente> listaCuentas;

    public Empresa(Integer idCliente, String direccion, String razonSocial, String ruc, List<Corriente> listaCuentas){
        super(idCliente, direccion);
        this.razonSocial = razonSocial;
        this.ruc = ruc;
        this.listaCuentas = listaCuentas;
    }
}
