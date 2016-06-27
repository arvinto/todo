package todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by arvinaboque on 6/27/16.
 */
@Controller
public class ApplicationController {

    @RequestMapping(value="/")
    public String index(){
        return "index";
    }
}
