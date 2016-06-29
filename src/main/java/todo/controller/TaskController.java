package todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import todo.model.Task;
import todo.service.RepositoryService;

import java.util.List;
import java.util.Map;

import static todo.controller.ControllerHelper.*;

/**
 * Created by arvinaboque on 6/27/16.
 */
@RestController
@RequestMapping("/user/{userId}/task")
public class TaskController {

    private RepositoryService repositoryService;

    @Autowired
    TaskController( RepositoryService repositoryService ){

        this.repositoryService = repositoryService;
    }

    @RequestMapping( value="/add", method= RequestMethod.POST )
    public Map<String, Object> addTask( @PathVariable Long userId, @RequestBody Map<String,Object> taskMap ){

        String title = taskMap.get( TASK_TITLE ).toString();
        String description = taskMap.get( TASK_DESCRIPTION ).toString();

        repositoryService.addTask( userId, title, description, getDateNow() );

        return createResponse( "Task created successfully" );
    }

    @RequestMapping( method = RequestMethod.GET )
    public Map<String, Object> getTasks( @PathVariable Long userId ){

        List<Task> tasks = repositoryService.getTasks( Long.valueOf( userId ) );

        Map<String,Object> response = createResponse( "Fetched tasks" );
        response.put( "task", tasks );

        return response;
    }

    @RequestMapping( value="/{taskId}/delete", method = RequestMethod.DELETE )
    public Map<String, Object> deleteTask( @PathVariable Long taskId ){

        repositoryService.deleteTasks( taskId );

        return createResponse( "Task deleted successfully" );
    }

    @RequestMapping( value="/{taskId}/complete", method = RequestMethod.PUT )
    public Map<String, Object> completeTask( @PathVariable Long taskId ){

        repositoryService.completeTask( taskId );

        return createResponse( "Task completed successfully" );
    }

    @RequestMapping( value="/{taskId}/reopen", method = RequestMethod.PUT )
    public Map<String, Object> reopenTask( @PathVariable Long taskId ){

        repositoryService.reopenTask( taskId );

        return createResponse( "Task reopened successfully" );
    }

    @RequestMapping( value="/{taskId}/edit", method = RequestMethod.PUT )
    public Map<String,Object> editTask( @PathVariable Long taskId, @RequestBody Map<String, Object> taskMap ){

        String title = taskMap.get( TASK_TITLE ).toString();
        String description = taskMap.get( TASK_DESCRIPTION ).toString();

        repositoryService.editTask( taskId, title, description, getDateNow() );

        return createResponse( "Task edited successfully" );
    }
}
