package org.sistema.interfaces;

import java.util.List;

public interface RepositoryInterface<T, ID> {
    boolean save(T entity);
    T getById(ID id);
    boolean delete(ID id);
    List<T> findAll();
}