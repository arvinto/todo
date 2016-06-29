package todo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import todo.model.Comment;

import java.util.List;

/**
 * Created by arvinaboque on 6/27/16.
 */
@Repository
public interface CommentRepository extends CrudRepository<Comment, Long>{

    List<Comment> findByTaskId(Long taskId);
}
