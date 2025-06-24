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
        String[] campos = linea.trim().split("\\s{2,}");
        Integer idCuenta = Integer.parseInt(campos[0]);
        double saldo = Double.parseDouble(campos[1]);
        String tipoMoneda = campos[2];
        String tipoCuentaValor = campos[3];
        int idCliente = Integer.parseInt(campos[5]);

        Cliente cliente = new Cliente();
        Cuenta cuenta = new Cuenta();
        cuenta.setIdCuenta(idCuenta);
        cuenta.setSaldo(saldo);
        cuenta.setTipoMoneda(tipoMoneda);
        cuenta.setTipoCuenta(tipoCuentaValor);
        cuenta.setCliente(cliente);
        cliente.setIdCliente(idCliente);

        if (tipoCuentaValor.equalsIgnoreCase("ahorro")) {
            cuenta.setTasaInteres(Double.parseDouble(campos[4]));
            cuenta.setLimiteSobreGiros(null);
        } else if (tipoCuentaValor.equalsIgnoreCase("corriente")) {
            cuenta.setLimiteSobreGiros(Double.parseDouble(campos[4]));
            cuenta.setTasaInteres(null);
        }

        return cuenta;
    }

    @Override
    protected String getCabecera() {
        return String.format("%-12s %-12s %-14s %-14s %-14s %-16s %-14s",
                "Id_Cuenta", "Saldo", "Tipo_Moneda", "Tipo_Cuenta", "Tasa_Interes", "Limite_Sobre_Giros", "Id_Cliente");
    }

    @Override
    protected String formatObjeto(Cuenta c) {
        String tipoCuenta = c.getTipoCuenta();
        String tasaInteres = "";
        String limiteSobreGiros = "";

        if (tipoCuenta.equalsIgnoreCase("ahorro")) {
            tasaInteres = c.getTasaInteres() != null ? c.getTasaInteres().toString() : "";
        } else if (tipoCuenta.equalsIgnoreCase("corriente")) {
            limiteSobreGiros = c.getLimiteSobreGiros() != null ? c.getLimiteSobreGiros().toString() : "";
        }

        return String.format("%-12s %-12s %-14s %-14s %-14s %-16s %-14s",
                c.getIdCuenta(),
                c.getSaldo(),
                c.getTipoMoneda(),
                tipoCuenta,
                tasaInteres,
                limiteSobreGiros,
                c.getCliente().getIdCliente()
        );
    }
}
