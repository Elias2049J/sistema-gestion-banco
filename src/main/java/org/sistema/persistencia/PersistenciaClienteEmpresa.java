package org.sistema.persistencia;

import org.sistema.entity.ClienteEmpresa;

public class PersistenciaClienteEmpresa extends Persistencia<ClienteEmpresa> {

    @Override
    protected String getFilePath() {
        return "src/main/java/org/sistema/data/lista_clientes_empresas.txt";
    }

    @Override
    protected ClienteEmpresa parsearLinea(String linea) {
        String[] campos = linea.trim().split("\\{2,}");
        if (campos.length < 5) return null;
        return new ClienteEmpresa(
                Integer.parseInt(campos[0]), //id
                campos[3],// direccion
                null, //cuentas
                campos[1],// razon social
                campos[2]//ruc
        );
    }

    @Override
    protected String getCabecera() {
        return String.format("%-12s %-30s %-14s %-20s", "Id_Cliente", "Razon Social", "RUC", "Direccion");
    }

    @Override
    protected String formatObjeto(ClienteEmpresa e) {
        return String.format("%-12s %-30s %-14s %-20s",
                e.getIdCliente(),
                e.getRazonSocial(),
                e.getRuc(),
                e.getDireccion()
        );
    }
}
