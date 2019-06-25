package tgreenidge.com.recipe_app.recipes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import tgreenidge.com.recipe_app.recipes.models.AppUser;
import tgreenidge.com.recipe_app.recipes.models.Ingredient;
import tgreenidge.com.recipe_app.recipes.models.Recipe;
import tgreenidge.com.recipe_app.recipes.repositories.AppUserRepository;
import tgreenidge.com.recipe_app.recipes.repositories.IngredientRepository;
import tgreenidge.com.recipe_app.recipes.repositories.RecipeRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RecipeController {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @GetMapping("/recipes")
    public String getAllRecipes(Model m) {
        Iterable<Recipe> recipes = recipeRepository.findAll();
        m.addAttribute("recipes", recipes);

        return "recipes";
    }

    @GetMapping("/recipes/create")
    public String getRecipeForm() {
        return "newrecipe";
    }

    @PostMapping("/recipes/create")
    public RedirectView createRecipe(Principal p, String title) {
        AppUser user = appUserRepository.findByUsername(p.getName());
        Recipe newRecipe = new Recipe(title, user);

        recipeRepository.save(newRecipe);

        return new RedirectView("/recipes");
    }

    @GetMapping("/recipes/{id}/ingredients")
    public String getRecipeIngredients(@PathVariable Long id, Model m) {
        Recipe recipe = recipeRepository.findById(id).get();
        m.addAttribute("recipe", recipe);
        m.addAttribute("ingredients",recipe.getIngredients());

        return "recipeingredients";
    }

    @GetMapping("/recipes/{id}/ingredients/new")
    public String getIngredientsForm(@PathVariable Long id, Model m) {
        Recipe recipe = recipeRepository.findById(id).get();
        m.addAttribute("recipe", recipe);
        m.addAttribute("id", recipe.getId());

        return "newingredients";
    }

    @PostMapping("/recipes/{id}/ingredients/new")
    public RedirectView createNewIngredient(@PathVariable Long id, String name, String quantity) {
        Recipe recipe = recipeRepository.findById(id).get();
        Ingredient newIngredient = new Ingredient(name, quantity, recipe);
        ingredientRepository.save(newIngredient);

        return new RedirectView("/recipes");
    }

    @PostMapping("/recipes/{id}/addToProfile")
    public RedirectView addRecipeToProfile(@PathVariable Long id, Principal p) {
        Recipe recipe = recipeRepository.findById(id).get();
        AppUser user = appUserRepository.findByUsername(p.getName());
        user.getRecipes().add(recipe);
        appUserRepository.save(user);

        return new RedirectView("/profile");
    }
}
