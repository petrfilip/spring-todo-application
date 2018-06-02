package cz.upce.unicorn.controller;

import cz.upce.unicorn.repository.entity.Task;
import cz.upce.unicorn.service.AbstractService;
import cz.upce.unicorn.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController extends AbstractController<Task> {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    protected AbstractService<Task> getService() {
        return taskService;
    }

    @RequestMapping(value = "", method = {RequestMethod.PUT})
    @CrossOrigin
    public Task update(@RequestBody Task item) {
        Task byId = getById(item.getId());

        if (item.getTitle() != null) {
            byId.setTitle(item.getTitle());
        }

        if (item.getCompleted() != null) {
            byId.setCompleted(item.getCompleted());
        }

        return getService().saveOrUpdate(byId);
    }

    @RequestMapping(value = "/by/status/{completed}", method = RequestMethod.GET)
    @CrossOrigin
    public List<Task> findByStatus(@PathVariable String completed) {
        return taskService.findByStatus(Boolean.valueOf(completed));
    }
}