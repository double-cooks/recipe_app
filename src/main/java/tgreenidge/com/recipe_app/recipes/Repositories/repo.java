package tgreenidge.com.recipe_app.recipes.Repositories;

import org.springframework.data.repository.CrudRepository;
import tgreenidge.com.recipe_app.recipes.Models.model;

public interface repo extends CrudRepository<model, Long> {
}
