package org.sistema.repository;

import org.sistema.interfaces.RepositoryInterface;

import java.util.ArrayList;
import java.util.List;

public abstract class Repository<T, ID> implements RepositoryInterface<T, ID> {
    protected List<T> elementos = new ArrayList<>();

    protected abstract ID getId(T entity);

    @Override
    public boolean save(T entity) {
        return elementos.add(entity);
    }

    @Override
    public T getById(ID id) {
        for (T e: elementos) {
            if (getId(e).equals(id)) {
                return e;
            }
        }
        return null;
    }

    @Override
    public boolean delete(ID id) {
        return elementos.removeIf(e -> getId(e).equals(id));
    }

    @Override
    public List<T> findAll() {
        return elementos;
    }
}
