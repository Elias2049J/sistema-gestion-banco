package org.sistema.persistencia;

import org.sistema.entity.ClienteNatural;

public class PersistenciaClienteNatural extends Persistencia<ClienteNatural> {

    @Override
    protected String getFilePath() {
        return "src/main/java/org/sistema/data/lista_clientes_naturales.txt";
    }

    @Override
    protected ClienteNatural parsearLinea(String linea) {
        String[] campos = linea.trim().split("\\{2,}");
        if (campos.length < 5) return null;
        return new ClienteNatural(
                Integer.parseInt(campos[0]), //id
                campos[4], // dni
                null, // cuentas
                campos[2], //nombre
                campos[3], //apellido
                campos[1] //direccion
        );
    }

    @Override
    protected String getCabecera() {
        return String.format("%-12s %-10s %-15s %-15s %-20s", "Id_Cliente", "DNI", "Nombre", "Apellido", "Direccion");
    }

    @Override
    protected String formatObjeto(ClienteNatural c) {
        return String.format("%-12s %-10s %-15s %-15s %-20s",
                c.getIdCliente(),
                c.getDni(),
                c.getNombre(),
                c.getApellido(),
                c.getDireccion()
        );
    }
}
