package tgreenidge.com.recipe_app.recipes.repositories;

import org.springframework.data.repository.CrudRepository;
import tgreenidge.com.recipe_app.recipes.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
