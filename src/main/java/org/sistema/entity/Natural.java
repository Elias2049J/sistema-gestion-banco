package org.sistema.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
public class Natural extends Cliente {
    private String nombre;
    private String apellido;
    private String dni;
    private Ahorro cuentaAhorro;

    public  Natural(Integer idCliente, String direccion, String nombre, String apellido, String dni, Ahorro cuentaAhorro){
        super(idCliente, direccion);
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.cuentaAhorro = cuentaAhorro;
    }
}
