package todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import todo.model.Comment;
import todo.model.Task;
import todo.model.User;
import todo.repository.CommentRepository;
import todo.repository.TaskRepository;
import todo.repository.UserRepository;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by bill.villaflor on 6/27/16.
 */
public class RepositoryDefaultService implements RepositoryService {

    private UserRepository userRepo;

    private TaskRepository taskRepo;

    private CommentRepository commentRepo;

    @Autowired
    public RepositoryDefaultService(UserRepository userRepo, TaskRepository taskRepo, CommentRepository commentRepo){

        this.userRepo = userRepo;
        this.taskRepo = taskRepo;
        this.commentRepo = commentRepo;
    }

    @Override
    public void addUser(String firstName, String lastName, String emailAddress) {

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmailAddress(emailAddress);

        userRepo.save(user);
    }

    @Override
    public void addTask(Long userId, String title, String description, String createdDate) {

        Optional<User> user = Optional.ofNullable(userRepo.findOne(userId));

        user.ifPresent( u -> {

            Task task = new Task();
            task.setTitle(title);
            task.setDescription(description);
            task.setCreatedDate(Date.from(Instant.now()));
            task.setUser(u);

            taskRepo.save(task);
        });
    }

    @Override
    public void addComment(Long userId, Long taskId, String description, String date) {

        Optional<User> user = Optional.ofNullable(userRepo.findOne(userId));
        Optional<Task> task = Optional.ofNullable(taskRepo.findOne(taskId));

        if( user.isPresent() && task.isPresent() ){

            Comment comment = new Comment();
            comment.setDescription(description);
            comment.setDate(Date.from(Instant.now()));
            comment.setUser(user.get());
            comment.setTask(task.get());
        }
    }

    @Override
    public List<Task> getTasks(Long userId) {
        return null;
    }

    @Override
    public List<Comment> getComments(Long taskId) {
        return null;
    }

    @Override
    public void editTask(Long taskId, String title, String description, String editedDate) {

    }
}