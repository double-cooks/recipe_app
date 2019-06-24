package tgreenidge.com.recipe_app.recipes.repositories;

import org.springframework.data.repository.CrudRepository;
import tgreenidge.com.recipe_app.recipes.models.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
