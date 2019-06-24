package tgreenidge.com.recipe_app.recipes.repositories;

import org.springframework.data.repository.CrudRepository;
import tgreenidge.com.recipe_app.recipes.models.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
