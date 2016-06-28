package todo.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import todo.model.User;

import java.util.List;
import java.util.Map;

/**
 * Created by arvinaboque on 6/27/16.
 */
@RestController
public class UserController {


    @RequestMapping(value="/user/add", method = RequestMethod.POST)
    public Map<String, Object> addUser(@RequestBody Map<String, Object> userMap){


        return null;
    }

    @RequestMapping(value="/users", method = RequestMethod.GET)
    public List<User> getUsers(){
        return null;
    }
}
