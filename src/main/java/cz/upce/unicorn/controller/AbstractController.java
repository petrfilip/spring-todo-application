package cz.upce.unicorn.controller;

import cz.upce.unicorn.repository.entity.Identifiable;
import cz.upce.unicorn.service.AbstractService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class AbstractController<T extends Identifiable> {

    protected abstract AbstractService<T> getService();

    @RequestMapping(value = "", method = RequestMethod.GET)
    @CrossOrigin
    public List<T> getAll() {
        return getService().findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @CrossOrigin
    public T getById(@PathVariable Integer id) {
        return getService().findById(id);
    }

    @RequestMapping(value = "", method = {RequestMethod.POST})
    @CrossOrigin
    public T saveOrUpdate(@RequestBody T item) {
        return getService().saveOrUpdate(item);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @CrossOrigin
    public void delete(@PathVariable Integer id) {
        getService().delete(id);
    }
}
