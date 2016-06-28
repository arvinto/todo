package todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import todo.service.RepositoryService;

import java.time.Instant;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

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
        Date createdDate = Date.from(Instant.now());

        repositoryService.addTask( 1l, title, description, createdDate.toString());

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Task created successfully");

        return response;
    }

}
