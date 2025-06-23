package org.sistema.persistencia;

import org.sistema.entity.Cliente;
import org.sistema.entity.Cuenta;

public class PersistenciaCuenta extends Persistencia<Cuenta>{
    @Override
    protected String getFilePath() {
        return "src/main/java/org/sistema/data/lista_cuentas.txt";
    }

    @Override
    protected Cuenta parsearLinea(String linea) {
        String[] campos = linea.trim().split("\\{2,}");
        return new Cuenta(
                Integer.parseInt(campos[0]),//id
                Double.parseDouble(campos[1]),//direccion
                campos[2],//tipo moneda
                new Cliente(Integer.parseInt(campos[3]), null, null)//cliente
        );
    }

    @Override
    protected String getCabecera() {
        return String.format("%-12s %-12s %-14s %-14s", "Id_Cuenta", "Saldo", "Tipo_Moneda", "Id_Cliente");
    }

    @Override
    protected String formatObjeto(Cuenta c) {
        return String.format("%-12s %-12s %-14s %-14s",
                c.getIdCuenta(),
                c.getSaldo(),
                c.getTipoMoneda(),
                c.getCliente().getIdCliente()
        );
    }
}
