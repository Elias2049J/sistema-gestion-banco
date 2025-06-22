package org.sistema.repository;

import lombok.Getter;
import lombok.Setter;
import org.sistema.entity.Ahorro;
import org.sistema.entity.Corriente;
import org.sistema.entity.Empresa;
import org.sistema.entity.Natural;

import java.util.ArrayList;
import java.util.List;

public class Data {
    @Getter
    @Setter
    private static List<Natural> naturales = new ArrayList<>();

    @Getter
    @Setter
    private static List<Empresa> empresas = new ArrayList<>();

    @Getter
    @Setter
    private static List<Corriente> corrientes = new ArrayList<>();

    @Getter
    @Setter
    private static List<Ahorro> ahorros = new ArrayList<>();

    public static void agregarNatural(Natural natural) {
        naturales.add(natural);
    }
}
