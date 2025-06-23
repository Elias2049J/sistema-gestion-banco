package org.sistema.use_case;

public interface GestionUseCase<T, ID> {
    boolean crear(T objeto);
    boolean update(T objeto);
    boolean getById(ID id);
    boolean deleteById(ID id);
}
