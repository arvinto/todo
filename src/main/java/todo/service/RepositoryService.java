package todo.service;

import todo.model.Comment;
import todo.model.Task;
import todo.model.User;

import java.util.List;

import static com.sun.tools.doclets.formats.html.markup.HtmlStyle.title;

/**
 * Created by bill.villaflor on 6/27/16.
 */
public interface RepositoryService {

    void addUser( String firstName, String lastName, String emailAddress );

    void addTask( Long userId, String title, String description, String createdDate );

    void addComment( Long userId, Long taskId, String description, String date );

    List<User> getUsers();

    List<Task> getTasks( Long userId );

    List<Comment> getComments( Long taskId );

    void editTask( Long taskId, String title, String description, String editedDate );
}
