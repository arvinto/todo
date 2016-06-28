package todo.controller;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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

    @RequestMapping(value="/users", method = RequestMethod.GET)
    public List<User> getUsers(){
        return null;
    }
}
