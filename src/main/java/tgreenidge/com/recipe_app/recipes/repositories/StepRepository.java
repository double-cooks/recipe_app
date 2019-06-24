package tgreenidge.com.recipe_app.recipes.repositories;

import org.springframework.data.repository.CrudRepository;
import tgreenidge.com.recipe_app.recipes.models.Step;

public interface StepRepository extends CrudRepository<Step, Long> {
}
