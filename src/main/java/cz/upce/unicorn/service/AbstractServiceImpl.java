package cz.upce.unicorn.service;


import cz.upce.unicorn.repository.AbstractRepository;
import cz.upce.unicorn.repository.entity.Identifiable;

import java.util.List;

public abstract class AbstractServiceImpl<T extends Identifiable> implements AbstractService<T> {

    protected abstract AbstractRepository<T> getRepository();

    public List<T> findAll() {
        return getRepository().findAll();
    }

    public T findById(Integer id) {
        return getRepository().findById(id);
    }

    public T saveOrUpdate(T item) {
        return getRepository().save(item);
    }

    public void delete(Integer id) {
        getRepository().delete(id);
    }


}
