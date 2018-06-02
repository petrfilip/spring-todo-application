package cz.upce.unicorn.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import cz.upce.unicorn.AbstractIntegrationTest;
import cz.upce.unicorn.repository.entity.Task;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TaskControllerTest extends AbstractIntegrationTest {

    @Autowired
    private TaskController taskController;

    private Gson gson;

    private final Type listTasksType = new TypeToken<ArrayList<Task>>() {
    }.getType();

    @Override
    public void setup() throws Exception {
        super.setup();
        gson = new Gson();
        initData();
    }

    @Test
    public void checkIfServiceBeanIsNotNull() {
        Assert.assertNotNull(taskController.getService());
    }

//    @Test
//    public void updateTaskTitle() throws Exception {
//        String newTitle = "task 1 updated";
//        Task task = new Task();
//        task.setId(1);
//        task.setTitle(newTitle);
//
//        this.mockMvc.perform(MockMvcRequestBuilders.put("/tasks/", task))
//                .andDo(print()).andExpect(status().isOk())
//                .andReturn();
//
//        MvcResult mvcResult = this.mockMvc.perform(get("/tasks/1"))
//                .andDo(print()).andExpect(status().isOk())
//                .andReturn();
//
//        Assert.assertEquals(newTitle, ((Task) mvcResult.getModelAndView().getModel().get("task")).getTitle());
//
//    }

    @Test
    public void findUncompletedTasks() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/tasks/by/status/false"))
                .andDo(print()).andExpect(status().isOk())
                .andReturn();

        Assert.assertEquals(3, ((ArrayList) mvcResult.getModelAndView().getModel().get("taskList")).size());

    }

    @Test
    public void findCompletedTasks() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/tasks/by/status/true"))
                .andDo(print()).andExpect(status().isOk())
                .andReturn();

        Assert.assertEquals(2, ((ArrayList) mvcResult.getModelAndView().getModel().get("taskList")).size());

    }

    @Test
    public void getAllFiveTaskAndCheckSize() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/tasks/"))
                .andDo(print()).andExpect(status().isOk())
                .andReturn();

        Assert.assertEquals(5, ((ArrayList) mvcResult.getModelAndView().getModel().get("identifiableList")).size());
    }

    @Test
    public void findTaskByExistsId() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/tasks/1"))
                .andDo(print()).andExpect(status().isOk())
                .andReturn();

        Assert.assertNotNull(mvcResult.getModelAndView().getModel().get("task"));
    }

    @Test
    public void findTaskByNonExistId() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/tasks/10"))
                .andDo(print()).andExpect(status().isOk())
                .andReturn();

        Assert.assertNull(mvcResult.getModelAndView().getModel().get("task"));
    }

//    @Test
//    public void insertNewTaskAndCheckSizeOfTaskCollection() throws Exception {
//
//        Task newTask = new Task();
//        newTask.setTitle("task 6");
//        newTask.setCompleted(false);
//
//
//        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/tasks/");
//        request.contentType(MediaType.APPLICATION_JSON);
//        request.content(new Gson().toJson(newTask));
//        request.accept(MediaType.APPLICATION_JSON);
//        mockMvc.perform(request).andDo(print()).andExpect(status().isOk());
//
//
//        MvcResult mvcResult2 = this.mockMvc.perform(get("/tasks/"))
//                .andDo(print()).andExpect(status().isOk())
//                .andReturn();
//
//        Assert.assertEquals(6, ((ArrayList) mvcResult2.getModelAndView().getModel().get("identifiableList")).size());
//    }

    @Test
    public void deleteOneTaskAndCheckSize() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/tasks/1"))
                .andDo(print()).andExpect(status().isOk())
                .andReturn();

        MvcResult mvcResult2 = this.mockMvc.perform(get("/tasks/"))
                .andDo(print()).andExpect(status().isOk())
                .andReturn();

        Assert.assertEquals(4, ((ArrayList) mvcResult2.getModelAndView().getModel().get("identifiableList")).size());

    }

    private void initData() {
        Task task1 = new Task();
        task1.setTitle("task 1");
        task1.setCompleted(true);
        taskController.saveOrUpdate(task1);

        Task task2 = new Task();
        task2.setTitle("task 2");
        task2.setCompleted(false);
        taskController.saveOrUpdate(task2);

        Task task3 = new Task();
        task3.setTitle("task 3");
        task3.setCompleted(false);
        taskController.saveOrUpdate(task3);

        Task task4 = new Task();
        task4.setTitle("task 4");
        task4.setCompleted(true);
        taskController.saveOrUpdate(task4);

        Task task5 = new Task();
        task5.setTitle("task 5");
        task5.setCompleted(false);
        taskController.saveOrUpdate(task5);
    }
}