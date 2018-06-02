package cz.upce.unicorn.repository;


import cz.upce.unicorn.repository.entity.Identifiable;

import java.util.List;

public interface AbstractRepository<T extends Identifiable> {

    T findById(Integer id);

    T save(T item);

    List<T> findAll();

    void delete(Integer id);

}
