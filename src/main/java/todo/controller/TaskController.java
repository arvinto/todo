package todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import todo.model.Task;
import todo.service.RepositoryService;

import java.util.List;
import java.util.Map;

import static todo.controller.ControllerHelper.*;

/**
 * Created by arvinaboque on 6/27/16.
 */
@RestController
public class TaskController {

    private RepositoryService repositoryService;

    @Autowired
    TaskController( RepositoryService repositoryService ){

        this.repositoryService = repositoryService;
    }

    @RequestMapping( value="/task/add", method= RequestMethod.POST )
    public Map<String, Object> addTask( @RequestBody Map<String,Object> taskMap ){

        String title = taskMap.get( TASK_TITLE ).toString();
        String description = taskMap.get( TASK_DESCRIPTION ).toString();
        Long userId = Long.valueOf( taskMap.get( USER_ID ).toString() );

        repositoryService.addTask( userId, title, description, getDateNow() );

        return createResponse( "Task created successfully" );
    }

    @RequestMapping( value="/task/delete", method = RequestMethod.POST )
    public Map<String, Object> deleteTask( @RequestBody Map<String, Object> taskMap ){

        Long taskId = Long.valueOf( taskMap.get( TASK_ID ).toString() );

        repositoryService.deleteTasks( taskId );

        return createResponse( "Task deleted successfully" );
    }

    @RequestMapping( value="/task/complete", method = RequestMethod.POST )
    public Map<String, Object> completeTask( @RequestBody Map<String, Object> taskMap ){

        Long taskId = Long.valueOf( taskMap.get( TASK_ID ).toString() );
        repositoryService.completeTask( taskId );

        return createResponse( "Task completed successfully" );
    }

    @RequestMapping( value="/task/reopen", method = RequestMethod.POST )
    public Map<String, Object> reopenTask( @RequestBody Map<String, Object> taskMap ){

        Long taskId = Long.valueOf( taskMap.get( TASK_ID ).toString() );
        repositoryService.reopenTask( taskId );

        return createResponse( "Task reopened successfully" );
    }

    @RequestMapping( value="/task", method = RequestMethod.POST )
    public Map<String, Object> getTasks( @RequestBody Map<String,Object> taskMap ){

        Long userId = Long.valueOf( taskMap.get( USER_ID ).toString() );
        List<Task> tasks = repositoryService.getTasks(Long.valueOf( userId ));


        Map<String,Object> response = createResponse( "Fetched tasks" );
        response.put("task", tasks);

        return response;
    }

    @RequestMapping( value="/task/edit", method = RequestMethod.POST )
    public Map<String,Object> editTask( @RequestBody Map<String, Object> taskMap ){

        Long taskId = Long.valueOf(taskMap.get( TASK_ID ).toString());
        String title = taskMap.get( TASK_TITLE ).toString();
        String description = taskMap.get( TASK_DESCRIPTION ).toString();

        repositoryService.editTask( taskId, title, description, getDateNow() );

        return createResponse( "Task edited successfully" );
    }
}
