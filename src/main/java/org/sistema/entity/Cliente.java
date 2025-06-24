package org.sistema.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    private Integer idCliente;
    private String direccion;
    private List<Cuenta> cuentas;
    private String tipoCliente;
    private String nombre;
    private String apellido;
    private String dni;
    private String razonSocial;
    private String ruc;
}
