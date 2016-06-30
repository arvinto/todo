package todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import todo.model.User;
import todo.service.RepositoryService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Map;

import static todo.controller.ControllerHelper.*;

/**
 * Created by arvinaboque on 6/27/16.
 */
@RestController
@RequestMapping( "/user" )
public class UserController {

    private RepositoryService repositoryService;

    @Autowired
    UserController( RepositoryService repositoryService ){

        this.repositoryService = repositoryService;
    }

    @RequestMapping( value="/add", method = RequestMethod.POST )
    public List<User> addUser(@RequestBody Map<String, Object> userMap){

        String firstName = userMap.get( USER_FIRST_NAME ).toString();
        String lastName  = userMap.get( USER_LAST_NAME ).toString();
        String emailAddress = userMap.get( USER_EMAIL ).toString();
        String userName = userMap.get( USER_USER_NAME ).toString();
        String password = userMap.get( USER_PASSWORD ).toString();

        repositoryService.addUser( firstName, lastName, emailAddress, userName, password );

        return repositoryService.getUsers();
    }

    @RequestMapping( value="/all", method = RequestMethod.GET )
    public Map<String, Object> getUsers(){

        List<User> users = repositoryService.getUsers();

        Map<String, Object> response = createResponse( "Fetched users", "user", users );
        response.put( "user", users );

        return response;
    }

    @RequestMapping( method = RequestMethod.GET )
    public String getUser( HttpServletRequest request ){

        Principal principal = request.getUserPrincipal();

        return principal.getName();
    }
}
