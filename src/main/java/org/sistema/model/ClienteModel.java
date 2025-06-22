package org.sistema.model;

import lombok.Getter;
import org.sistema.entity.Ahorro;
import org.sistema.entity.Corriente;
import org.sistema.entity.Empresa;
import org.sistema.entity.Natural;
import org.sistema.repository.Data;
import org.sistema.repository.PersistenceInterface;
import org.sistema.repository.PersistenciaClientes;
import org.sistema.use_case.ClienteUseCase;

import java.util.List;
@Getter
public class ClienteModel implements ClienteUseCase {
    private PersistenceInterface persistenciaCliente = new PersistenciaClientes();

    public ClienteModel (){
        this.persistenciaCliente.importarListaNaturales(Data.getNaturales());
    }
    @Override
    public boolean crearNatural(String nombre, String apellido, String dni, String direccion) {
        persistenciaCliente.importarListaNaturales(Data.getNaturales());

        Integer id;
        if (Data.getNaturales().isEmpty()) {
            id = 1;
        } else {
            id = Data.getNaturales().getLast().getIdCliente()+1;
        }
        Natural natural = new Natural(id, direccion, nombre, apellido,  dni, null);
        Data.agregarNatural(natural);
        persistenciaCliente.exportarListaNaturales(Data.getNaturales());
        persistenciaCliente.importarListaNaturales(Data.getNaturales());
        return true;
    }

    @Override
    public boolean crearEmpresa(String direccion, String razonSocial, String ruc, List<Corriente> listaCuentas) {
        return false;
    }

    @Override
    public boolean updateNatural(String direccion, String nombre, String apellido, String dni, List<Ahorro> listaCuentas) {
        return false;
    }

    @Override
    public boolean updateEmpresa(String direccion, String razonSocial, String ruc, List<Corriente> listaCuentas) {
        return false;
    }

    @Override
    public boolean saveNaturalListFromList(List<Natural> lista) {
        return false;
    }

    @Override
    public boolean saveEmpresaListFromlist(List<Empresa> lista) {
        return false;
    }

    @Override
    public Natural getNaturalById(Integer id) {
        return null;
    }

    @Override
    public Empresa getEmpresaById(Integer id) {
        return null;
    }

    @Override
    public boolean deleteEmpresa(Integer id) {
        return false;
    }

    @Override
    public boolean deleteNatural(Integer id) {
        return false;
    }
}
