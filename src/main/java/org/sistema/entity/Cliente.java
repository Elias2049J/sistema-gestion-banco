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

    public boolean esValido() {
        if (idCliente == null || direccion == null || direccion.trim().isEmpty() ||
                tipoCliente == null || tipoCliente.trim().isEmpty()) {
            return false;
        }

        if (tipoCliente.equalsIgnoreCase("natural")) {
            return esValidoNatural();
        } else if (tipoCliente.equalsIgnoreCase("empresa")) {
            return esValidoEmpresa();
        }

        return false;
    }

    private boolean esValidoNatural() {
        if (nombre == null || nombre.trim().isEmpty() ||
                apellido == null || apellido.trim().isEmpty() ||
                dni == null || dni.trim().isEmpty()) {
            return false;
        }
        return validarDni(dni);
    }

    private boolean esValidoEmpresa() {
        if (razonSocial == null || razonSocial.trim().isEmpty() ||
                ruc == null || ruc.trim().isEmpty()) {
            return false;
        }
        return validarRuc(ruc);
    }

    private boolean validarDni(String dni) {
        return dni.length() == 8 && dni.matches("\\d{8}");
    }

    private boolean validarRuc(String ruc) {
        return ruc.length() == 11 && ruc.matches("\\d{11}") &&
               (ruc.startsWith("10") || ruc.startsWith("20"));
    }
}
