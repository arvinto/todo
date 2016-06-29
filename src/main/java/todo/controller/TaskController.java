package todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import todo.model.Task;
import todo.service.RepositoryService;

import java.time.Instant;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.data.jpa.domain.AbstractAuditable_.createdDate;

/**
 * Created by arvinaboque on 6/27/16.
 */
@RestController
public class TaskController {

    private RepositoryService repositoryService;

    @Autowired
    TaskController(RepositoryService repositoryService){
        this.repositoryService = repositoryService;
    }

    @RequestMapping(value="/task/add", method= RequestMethod.POST)
    public Map<String, Object> addTask(@RequestBody Map<String,Object> taskMap){

        String title = taskMap.get("title").toString();
        String description = taskMap.get("description").toString();
        Long userId = Long.valueOf(taskMap.get("userId").toString());
        Date createdDate = Date.from(Instant.now());

        repositoryService.addTask( userId, title, description, createdDate);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Task created successfully");

        return response;
    }

    @RequestMapping(value="/task/delete", method = RequestMethod.POST)
    public Map<String, Object> deleteTask(@RequestBody Map<String, Object> taskMap){

        Long taskId = Long.valueOf(taskMap.get("taskId").toString());

        repositoryService.deleteTasks(taskId);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Task deleted successfully");

        return response;
    }

    @RequestMapping(value="/task/complete", method = RequestMethod.POST)
    public Map<String, Object> completeTask(@RequestBody Map<String, Object> taskMap){

        Long taskId = Long.valueOf(taskMap.get("taskId").toString());
        repositoryService.completeTask(taskId);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Task completed successfully");

        return response;
    }

    @RequestMapping(value="/task/reopen", method = RequestMethod.POST)
    public Map<String, Object> reopenTask(@RequestBody Map<String, Object> taskMap){

        Long taskId = Long.valueOf(taskMap.get("taskId").toString());
        repositoryService.reopenTask(taskId);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Task reopened successfully");

        return response;
    }

    @RequestMapping(value="/task", method = RequestMethod.POST)
    public Map<String, Object> getTasks(@RequestBody Map<String,Object> taskMap){

        Long userId = Long.valueOf(taskMap.get("userId").toString());
        List<Task> tasks = repositoryService.getTasks(Long.valueOf(userId));

        Map<String,Object> response = new LinkedHashMap<>();
        response.put("message", "Tasks");
        response.put("task", tasks);

        return response;
    }
}
