package cz.upce.unicorn.service;


import cz.upce.unicorn.repository.entity.Identifiable;

import java.util.List;

public interface AbstractService<T extends Identifiable> {
    List<T> findAll();

    T findById(Integer id);

    T saveOrUpdate(T item);

    void delete(Integer id);


}
