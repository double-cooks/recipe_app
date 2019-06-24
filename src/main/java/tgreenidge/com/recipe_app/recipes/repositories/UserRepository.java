package tgreenidge.com.recipe_app.recipes.repositories;

import org.springframework.data.repository.CrudRepository;
import tgreenidge.com.recipe_app.recipes.models.AppUser;

public interface UserRepository extends CrudRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
