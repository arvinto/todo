package todo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import todo.model.User;
/**
 * Created by arvinaboque on 6/27/16.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmailAddress(String emailAddress);
}
