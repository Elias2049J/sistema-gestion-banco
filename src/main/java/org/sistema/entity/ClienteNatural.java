package org.sistema.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
public class ClienteNatural extends Cliente {
    private String nombre;
    private String apellido;
    private String dni;

    public ClienteNatural(Integer idCliente, String direccion, List<Cuenta> cuentas, String nombre, String apellido, String dni){
        super(idCliente, direccion, cuentas);
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    @Override
    public boolean esValido() {
        if (apellido == null) return false;
        if (nombre == null) return false;
        if (dni.length() != 8) return false;
        return super.esValido();
    }
}
