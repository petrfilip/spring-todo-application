package cz.upce.unicorn.repository;


import cz.upce.unicorn.repository.entity.Task;

import java.util.List;

public interface TaskRepository extends AbstractRepository<Task> {

    List<Task> findByStatus(Boolean completed);
}
