package todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
@RequestMapping(value="user/{userName}/task/{taskId}/comment")
public class CommentController {

    private RepositoryService repositoryService;

    @Autowired
    CommentController( RepositoryService repositoryService){
        this.repositoryService = repositoryService;
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long taskId){

        List<Comment> comments =  repositoryService.getComments(taskId);
        return new ResponseEntity<List<Comment>>( comments, HttpStatus.OK);
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public ResponseEntity<String> addCommment(@PathVariable String userName, @PathVariable Long taskId, @RequestBody Map<String, Object> commentMap ){

        String description = commentMap.get("description").toString();
        Date date = Date.from(Instant.now());
        repositoryService.addComment(userName, taskId, description, date);

        return new ResponseEntity<String>("Comment Added",HttpStatus.CREATED);
    }

    @RequestMapping(value="/{commentId}/delete", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId){

        repositoryService.deleteComment(commentId);
        return new ResponseEntity<String>("Comment deleted", HttpStatus.OK);
    }

    @RequestMapping(value="/{commentId}/edit", method = RequestMethod.PUT)
    public ResponseEntity<String> editComment(@PathVariable Long commentId, @RequestBody Map<String, Object> commentMap){

        String description = commentMap.get("description").toString();
        repositoryService.editComment( commentId, description );

        return new ResponseEntity<String>("Comment edited", HttpStatus.OK);
    }


}
