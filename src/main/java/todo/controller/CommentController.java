package todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import todo.model.Comment;
import todo.service.RepositoryService;

import java.time.Instant;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by arvinaboque on 6/28/16.
 */
@RestController
public class CommentController {

    private RepositoryService repositoryService;

    @Autowired
    CommentController( RepositoryService repositoryService){
        this.repositoryService = repositoryService;
    }

    @RequestMapping(value="/comment", method = RequestMethod.GET)
    public List<Comment> getComments(){

        return repositoryService.getComments(1L);
    }

    @RequestMapping(value="/comment/add", method = RequestMethod.POST)
    public Map<String, Object> addCommment(@RequestBody Map<String, Object> commentMap ){

        String description = commentMap.get("description").toString();
        Date date = Date.from(Instant.now());

        //TODO supply user and task id
        repositoryService.addComment(1L, 1L, description, date);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Comment added successfully");

        return response;
    }


}
