package org.sistema.persistencia;

import org.sistema.entity.Natural;
import org.sistema.interfaces.PersistenceInterface;

import java.io.*;
import java.util.List;

public class PersistenciaClientes implements PersistenceInterface {
    @Override
    public boolean importarListaNaturales(List<Natural> naturales) {
        File archivo = new File("src/main/java/org/sistema/data/naturales.txt");
        boolean existe = archivo.exists();
        naturales.clear();
        if(!existe) {
            return false;
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            br.readLine();
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.trim().split("\\s{2,}");
                if(campos.length < 5) {
                    continue;
                }
                Natural n = new Natural(
                        Integer.parseInt(campos[0]),
                        campos[4],
                        campos[1],
                        campos[2],
                        campos[3],
                        null
                );
                naturales.add(n);
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean exportarListaNaturales(List<Natural> lista) {
        try {
            File archivo = new File("src/main/java/org/sistema/data/naturales.txt");
            PrintWriter writer = new PrintWriter(new FileWriter(archivo, false));
            writer.printf("%-12s %-15s %-15s %-6s %-10s%n"
                    ,"Id Cliente","Nombre","Apellido","DNI","Direccion");
            for (Natural n: lista) {
                writer.printf("%-10s %-12s %-15s %-10s %-20s%n",
                        n.getIdCliente(),
                        n.getNombre(),
                        n.getApellido(),
                        n.getDni(),
                        n.getDireccion()
                );
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
