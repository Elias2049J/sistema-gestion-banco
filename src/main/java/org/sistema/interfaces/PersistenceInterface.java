package org.sistema.interfaces;

import org.sistema.entity.Natural;

import java.util.List;

public interface PersistenceInterface {
    boolean importarListaNaturales(List<Natural> naturales);
    boolean exportarListaNaturales(List<Natural> lista);
}
