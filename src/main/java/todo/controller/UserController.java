package todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import todo.model.User;

import java.util.List;

/**
 * Created by arvinaboque on 6/27/16.
 */
@Controller
public class UserController {


    @RequestMapping(value="/users", method= RequestMethod.POST)
    public List<User> getUsers(){
        return null;
    }
}
