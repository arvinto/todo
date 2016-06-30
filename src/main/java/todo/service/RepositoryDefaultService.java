package todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import todo.model.Comment;
import todo.model.Task;
import todo.model.User;
import todo.repository.CommentRepository;
import todo.repository.TaskRepository;
import todo.repository.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by bill.villaflor on 6/27/16.
 */
@Component
public class RepositoryDefaultService implements RepositoryService {

    private UserRepository userRepo;

    private TaskRepository taskRepo;

    private CommentRepository commentRepo;

    @Autowired
    public RepositoryDefaultService( UserRepository userRepo, TaskRepository taskRepo, CommentRepository commentRepo ){

        this.userRepo = userRepo;
        this.taskRepo = taskRepo;
        this.commentRepo = commentRepo;
    }

    @Override
    public void addUser( String firstName, String lastName, String emailAddress, String userName, String password ) {

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmailAddress(emailAddress);
        user.setUserName( userName );
        user.setPassword( password );

        userRepo.save(user);
    }

    @Override
    public void addTask( String userName, String title, String description, Date createdDate ) {

        Optional<User> user = Optional.ofNullable( userRepo.findByUserName( userName ) );

        user.ifPresent( u -> {

            Task task = new Task();
            task.setTitle( title );
            task.setDescription( description );
            task.setCreatedDate( createdDate );
            task.setUser( u );
            task.setCompleted( false );

            taskRepo.save(task);
        });
    }

    @Override
    public void addComment( Long userId, Long taskId, String description, Date date ) {

        Optional<User> user = Optional.ofNullable(userRepo.findOne(userId));
        Optional<Task> task = Optional.ofNullable(taskRepo.findOne(taskId));

        if( user.isPresent() && task.isPresent() ){

            Comment comment = new Comment();
            comment.setDescription(description);
            comment.setDate(date);
            comment.setUser(user.get());
            comment.setTask(task.get());

            commentRepo.save(comment);
        }
    }

    @Override
    public void deleteTasks( Long taskId ){

        taskRepo.delete(taskId);
    }

    @Override
    public void completeTask( Long taskId ) {

        setTaskCompletion( taskId, true );
    }

    @Override
    public void reopenTask( Long taskId ) {

        setTaskCompletion( taskId, false );
    }

    @Override
    public void deleteComment( Long commentId ){

        commentRepo.delete( commentId );
    }

    @Override
    public List<User> getUsers() {

        return StreamSupport.stream( userRepo.findAll().spliterator(), false )
                            .collect( Collectors.toList() );
    }

    @Override
    public List<Task> getTasks( String userName ) {

        Optional<User> user = Optional.ofNullable( userRepo.findByUserName( userName ) );

        return user.map( u -> taskRepo.findByUserId( ( u.getId() ) ) )
                   .orElse( new ArrayList<>() );
    }

    @Override
    public List<Comment> getComments( Long taskId ) {

        return commentRepo.findByTaskId( taskId );
    }

    @Override
    public void editTask( Long taskId, String title, String description, Date editedDate ) {

        Optional<Task> task = Optional.ofNullable( taskRepo.findOne(taskId) );

        task.ifPresent( t -> {

            t.setTitle(title);
            t.setDescription(description);
            t.setEditedDate(editedDate);

            taskRepo.save( t );
        });
    }

    private void setTaskCompletion( Long taskId, boolean isCompleted ) {
        Optional<Task> task = Optional.ofNullable( taskRepo.findOne( taskId ) );

        task.ifPresent( t -> {

            t.setCompleted( isCompleted );
            taskRepo.save( t );
        });
    }
}
