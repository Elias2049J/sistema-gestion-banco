package org.sistema.persistencia;

import org.sistema.entity.Cliente;
import org.sistema.entity.ClienteNatural;
import org.sistema.entity.CuentaCorriente;

public class PersistenciaCuentaCorriente extends Persistencia<CuentaCorriente> {
    @Override
    protected String getFilePath() {
        return "src/main/java/org/sistema/data/lista_cuentas_corriente.txt";
    }

    @Override
    protected CuentaCorriente parsearLinea(String linea) {
        String[] campos = linea.trim().split("\\{2,}");
        return new CuentaCorriente(
                Integer.parseInt(campos[0]),//id
                Double.parseDouble(campos[1]),//saldo
                campos[2],//tipomoneda
                new Cliente(Integer.parseInt(campos[4]), null, null),//cliente
                Double.parseDouble(campos[3])//limite sobre giros
        );
    }

    @Override
    protected String getCabecera() {
        return String.format("%-12s %-12s %-14s %-14s %-14s", "Id_Cuenta", "Saldo", "Tipo_Moneda", "Limite_Sobre_Giros", "Id_Cliente");
    }

    @Override
    protected String formatObjeto(CuentaCorriente c) {
        return String.format("%-12s %-12s %-14s %-14s %-14s",
                c.getIdCuenta(),
                c.getSaldo(),
                c.getTipoMoneda(),
                c.getLimiteSobreGiros(),
                c.getCliente().getIdCliente()
        );
    }
}
