package org.sistema.use_case;

import org.sistema.entity.*;

import java.util.List;

public interface ClienteUseCase {
    boolean crearNatural(String direccion, String nombre, String apellido, String dni);
    boolean crearEmpresa(String direccion, String razonSocial, String ruc, List<Corriente> listaCuentas);
    boolean updateNatural(String direccion, String nombre, String apellido, String dni, List<Ahorro> listaCuentas);
    boolean updateEmpresa(String direccion, String razonSocial, String ruc, List<Corriente> listaCuentas);
    boolean saveNaturalListFromList(List<Natural> lista);
    boolean saveEmpresaListFromlist(List<Empresa> lista);
    Natural getNaturalById(Integer id);
    Empresa getEmpresaById(Integer id);
    boolean deleteEmpresa(Integer id);
    boolean deleteNatural(Integer id);
}
