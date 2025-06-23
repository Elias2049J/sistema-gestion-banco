package org.sistema.persistencia;

import org.sistema.entity.Cliente;
import org.sistema.entity.CuentaAhorro;

public class PersistenciaCuentaAhorro extends Persistencia<CuentaAhorro> {
    @Override
    protected String getFilePath() {
        return "src/main/java/org/sistema/data/lista_cuentas_ahorro.txt";
    }

    @Override
    protected CuentaAhorro parsearLinea(String linea) {
        String[] campos = linea.trim().split("\\{2,}");
        return new CuentaAhorro(
                Integer.parseInt(campos[0]),//id cuenta
                Double.parseDouble(campos[1]),//saldo
                campos[2],//tipo moneda
                new Cliente(Integer.parseInt(campos[4]), null, null), //cliente
                Double.parseDouble(campos[3]) //tasa
        );
    }

    @Override
    protected String getCabecera() {
        return String.format("%-12s %-12s %-14s %-14s %-14s", "Id_Cuenta", "Saldo", "Tipo_Moneda", "Tasa_Interes", "Id_Cliente");
    }

    @Override
    protected String formatObjeto(CuentaAhorro c) {
        return String.format("%-12s %-12s %-14s %-14s %-14s",
                c.getIdCuenta(),
                c.getSaldo(),
                c.getTipoMoneda(),
                c.getTasaInteres(),
                c.getCliente().getIdCliente()
        );
    }
}
