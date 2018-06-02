package cz.upce.unicorn.service;

import cz.upce.unicorn.repository.AbstractRepository;
import cz.upce.unicorn.repository.TaskRepository;
import cz.upce.unicorn.repository.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl extends AbstractServiceImpl<Task> implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    protected AbstractRepository<Task> getRepository() {
        return taskRepository;
    }

    @Override
    public List<Task> findByStatus(Boolean completed) {
        return taskRepository.findByStatus(completed);
    }
}
