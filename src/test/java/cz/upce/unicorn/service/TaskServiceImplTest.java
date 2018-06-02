package cz.upce.unicorn.service;

import cz.upce.unicorn.AbstractIntegrationTest;
import cz.upce.unicorn.controller.TaskController;
import cz.upce.unicorn.repository.entity.Task;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TaskServiceImplTest extends AbstractIntegrationTest {

    @Autowired
    private TaskService taskService;

    @Override
    public void setup() throws Exception {
        super.setup();
        initData();
    }

    @Test
    public void findByUncompletedStatus() {
        List<Task> byStatus = taskService.findByStatus(false);
        Assert.assertEquals(3, byStatus.size());
    }

    @Test
    public void findAll() {
        Assert.assertEquals(5, taskService.findAll().size());
    }

    @Test
    public void findByExistsId() {
        Assert.assertNotNull(taskService.findById(5));
    }

    @Test
    public void findByNonExistsId() {
        Assert.assertNull(taskService.findById(50));
    }

    @Test
    public void updateTaskTitle() {
        String newTitle = "task 1 title updated";

        Task task = taskService.findById(1);
        task.setTitle(newTitle);

        taskService.saveOrUpdate(task);

        Task loadedTask = taskService.findById(1);
        Assert.assertEquals(newTitle, loadedTask.getTitle());
        Assert.assertEquals(Integer.valueOf(1), loadedTask.getId());
        Assert.assertEquals(true, loadedTask.getCompleted());

    }

    @Test
    public void updateTaskStatus() {

        Task task = taskService.findById(1);
        task.setCompleted(false);

        taskService.saveOrUpdate(task);

        Task loadedTask = taskService.findById(1);
        Assert.assertEquals("task 1", loadedTask.getTitle());
        Assert.assertEquals(Integer.valueOf(1), loadedTask.getId());
        Assert.assertEquals(false, loadedTask.getCompleted());

    }

    @Test
    public void delete() {
        taskService.delete(1);
        Assert.assertEquals(4, taskService.findAll().size());
    }


    private void initData() {
        Task task1 = new Task();
        task1.setTitle("task 1");
        task1.setCompleted(true);
        taskService.saveOrUpdate(task1);

        Task task2 = new Task();
        task2.setTitle("task 2");
        task2.setCompleted(false);
        taskService.saveOrUpdate(task2);

        Task task3 = new Task();
        task3.setTitle("task 3");
        task3.setCompleted(false);
        taskService.saveOrUpdate(task3);

        Task task4 = new Task();
        task4.setTitle("task 4");
        task4.setCompleted(true);
        taskService.saveOrUpdate(task4);

        Task task5 = new Task();
        task5.setTitle("task 5");
        task5.setCompleted(false);
        taskService.saveOrUpdate(task5);
    }
}
