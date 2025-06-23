package org.sistema.persistencia;

import org.sistema.entity.Cliente;

public class PersistenciaCliente extends Persistencia<Cliente> {
    @Override
    protected String getFilePath() {
        return "src/main/java/org/sistema/data/lista_clientes.txt";
    }

    @Override
    protected Cliente parsearLinea(String linea) {
        String[] campos = linea.trim().split("\\{2,}");
        return new Cliente(
                Integer.parseInt(campos[0]),
                campos[1],
                null
        );
    }

    @Override
    protected String getCabecera() {
        return String.format("%-12s %-30s", "Id_Cliente", "Direccion");
    }

    @Override
    protected String formatObjeto(Cliente c) {
        return String.format("%-12s %-30s",
                c.getIdCliente(),
                c.getDireccion()
        );
    }
}
