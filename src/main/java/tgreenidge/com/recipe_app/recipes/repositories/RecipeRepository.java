package tgreenidge.com.recipe_app.recipes.repositories;

import org.springframework.data.repository.CrudRepository;
import tgreenidge.com.recipe_app.recipes.models.Recipe;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    List<Recipe> findByTitle(String title);
    Recipe findById(long id);
    List<Recipe> findAll();
}
