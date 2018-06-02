package cz.upce.unicorn.service;


import cz.upce.unicorn.repository.entity.Task;

import java.util.List;

public interface TaskService extends AbstractService<Task> {
    List<Task> findByStatus(Boolean completed);


}
