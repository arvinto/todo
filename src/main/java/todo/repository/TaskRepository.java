package todo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import todo.model.Task;

import java.util.List;


/**
 * Created by arvinaboque on 6/27/16.
 */
@Repository
public interface TaskRepository extends CrudRepository<Task, Long>{

    Task findByTitle(String title);

    List<Task> findByUserId(Long userId);
}
