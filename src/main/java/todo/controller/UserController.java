package todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import todo.model.User;
import todo.service.RepositoryService;

import java.util.List;
import java.util.Map;

import static todo.controller.ControllerHelper.*;

/**
 * Created by arvinaboque on 6/27/16.
 */
@RestController
public class UserController {

    private RepositoryService repositoryService;

    @Autowired
    UserController( RepositoryService repositoryService ){

        this.repositoryService = repositoryService;
    }

    @RequestMapping( value="/user/add", method = RequestMethod.POST )
    public Map<String, Object> addUser(@RequestBody Map<String, Object> userMap){

        String firstName = userMap.get( USER_FIRST_NAME ).toString();
        String lastName  = userMap.get( USER_LAST_NAME ).toString();
        String emailAddress = userMap.get( USER_EMAIL ).toString();

        repositoryService.addUser( firstName, lastName, emailAddress );

        return createResponse( "User added successfully" );
    }

    @RequestMapping( value="/user", method = RequestMethod.GET )
    public Map<String, Object> getUsers(){

        List<User> users = repositoryService.getUsers();

        Map<String, Object> response = createResponse( "Fetched users" );
        response.put( "user", users );

        return response;
    }
}
