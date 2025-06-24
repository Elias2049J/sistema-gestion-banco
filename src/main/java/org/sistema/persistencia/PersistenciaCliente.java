package org.sistema.persistencia;

import org.sistema.entity.Cliente;

public class PersistenciaCliente extends Persistencia<Cliente> {
    @Override
    protected String getFilePath() {
        return "src/main/java/org/sistema/data/lista_clientes.txt";
    }

    @Override
    protected Cliente parsearLinea(String linea) {
        String[] campos = linea.trim().split("\\s{2,}");
        Integer idCliente = Integer.parseInt(campos[0]);
        String direccion = campos[1];
        String tipoClienteValor = campos[2];

        Cliente c = new Cliente();
        c.setIdCliente(idCliente);
        c.setDireccion(direccion);
        c.setTipoCliente(tipoClienteValor);
        // setea atributos de natural
        if (tipoClienteValor.equalsIgnoreCase("natural")) {
            c.setNombre(campos[3]);
            c.setApellido(campos[4]);
            c.setDni(campos[5]);
            c.setRazonSocial(null);
            c.setRuc(null);
            // setea atributos de empresa
        } else if (tipoClienteValor.equalsIgnoreCase("empresa")) {
            c.setNombre(null);
            c.setApellido(null);
            c.setDni(null);
            c.setRazonSocial(campos[3]);
            c.setRuc(campos[4]);
        }
        return c;
    }

    @Override
    protected String getCabecera() {
        return String.format("%-12s %-30s %-12s %-15s %-15s %-10s %-30s %-15s",
                "Id_Cliente", "Direccion", "Tipo_Cliente", "Nombre", "Apellido", "DNI", "Razon_Social", "RUC");
    }

    @Override
    protected String formatObjeto(Cliente c) {
        String tipoCliente = c.getTipoCliente();
        String nombre = "";
        String apellido = "";
        String dni = "";
        String razonSocial = "";
        String ruc = "";
        if (tipoCliente.equalsIgnoreCase("natural")) {
            nombre = c.getNombre() != null ? c.getNombre() : "";
            apellido = c.getApellido() != null ? c.getApellido() : "";
            dni = c.getDni() != null ? c.getDni() : "";
        } else if (tipoCliente.equalsIgnoreCase("empresa")) {
            razonSocial = c.getRazonSocial() != null ? c.getRazonSocial() : "";
            ruc = c.getRuc() != null ? c.getRuc() : "";
        }
        return String.format("%-12s %-30s %-12s %-15s %-15s %-10s %-30s %-15s",
                c.getIdCliente(),
                c.getDireccion(),
                tipoCliente,
                nombre,
                apellido,
                dni,
                razonSocial,
                ruc
        );
    }
}
