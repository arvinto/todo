package todo.service;

import todo.model.Comment;
import todo.model.Task;
import todo.model.User;

import java.util.Date;
import java.util.List;

/**
 * Created by bill.villaflor on 6/27/16.
 */
public interface RepositoryService {

    void addUser( String firstName, String lastName, String emailAddress, String userName, String password );

    void addTask( String userName, String title, String description, Date createdDate );

    void addComment( String userName, Long taskId, String description, Date date );

    void deleteTasks( Long taskId );

    void completeTask( Long taskId );

    void reopenTask( Long taskId );

    void deleteComment( Long commentId );

    List<User> getUsers();

    List<Task> getTasks( String userName );

    List<Comment> getComments( Long taskId );

    void editTask( Long taskId, String title, String description, Date editedDate );

    void editComment( Long commentId, String description );
}
