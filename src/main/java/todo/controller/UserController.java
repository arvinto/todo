package todo.controller;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import todo.model.User;
import todo.service.RepositoryService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by arvinaboque on 6/27/16.
 */
@RestController
public class UserController {

    private RepositoryService repositoryService;

    @Autowired
    UserController(RepositoryService repositoryService){
        this.repositoryService = repositoryService;
    }

    @RequestMapping(value="/user/add", method = RequestMethod.POST)
    public Map<String, Object> addUser(@RequestBody Map<String, Object> userMap){

        String firstName = userMap.get("firstName").toString();
        String lastName  = userMap.get("lastName").toString();
        String emailAddress = userMap.get("emailAddress").toString();

        repositoryService.addUser( firstName, lastName, emailAddress);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "User added successfully");

        return response;
    }

    @RequestMapping(value="/user", method = RequestMethod.GET)
    public Map<String, Object> getUsers(){
        List<User> users = repositoryService.getUsers();

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Users");
        response.put("user", users);

        return response;
    }
}
