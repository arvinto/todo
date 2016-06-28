package todo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by arvinaboque on 6/27/16.
 */
@RestController
public class ApplicationController {

    @RequestMapping(value="/")
    public String index(){
        return "index";
    }
}
