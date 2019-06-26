package tgreenidge.com.recipe_app.recipes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import tgreenidge.com.recipe_app.recipes.models.AppUser;
import tgreenidge.com.recipe_app.recipes.models.Ingredient;
import tgreenidge.com.recipe_app.recipes.models.Recipe;
import tgreenidge.com.recipe_app.recipes.models.Step;
import tgreenidge.com.recipe_app.recipes.repositories.AppUserRepository;
import tgreenidge.com.recipe_app.recipes.repositories.IngredientRepository;
import tgreenidge.com.recipe_app.recipes.repositories.RecipeRepository;
import tgreenidge.com.recipe_app.recipes.repositories.StepRepository;

import java.security.Principal;

@Controller
public class RecipeController {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    StepRepository stepRepository;

    @GetMapping("/recipes/create")
    public String getRecipeForm(Principal p, Model m) {
        if (p != null) {
            m.addAttribute("user", appUserRepository.findByUsername(p.getName()));
        }
        return "newrecipe";
    }

    @PostMapping("/recipes/create")
    public RedirectView createRecipe(Principal p, String title, String prepTime, String cookTime) {
        AppUser user = appUserRepository.findByUsername(p.getName());
        Recipe newRecipe = new Recipe(title, prepTime, cookTime, user);

        recipeRepository.save(newRecipe);
        return new RedirectView("/recipes/" + newRecipe.getId() + "/ingredients/new");
    }

    @GetMapping("/recipes/{id}")
    public String getRecipeIngredients(@PathVariable Long id, Model m, Principal p) {
        Recipe recipe = recipeRepository.findById(id).get();
        m.addAttribute("recipe", recipe);
        m.addAttribute("ingredients",recipe.getIngredients());
        m.addAttribute("steps", recipe.getSteps());
        if (p != null) {
            m.addAttribute("user", appUserRepository.findByUsername(p.getName()));
        }

        return "recipeingredients";
    }

    @GetMapping("/recipes/{id}/ingredients/new")
    public String getNewIngredients(@PathVariable Long id, Model m, Principal p) {
        Recipe recipe = recipeRepository.findById(id).get();
        m.addAttribute("recipe", recipe);
        m.addAttribute("ingredients", recipe.getIngredients());
        if (p != null) {
            m.addAttribute("user", appUserRepository.findByUsername(p.getName()));
        }
        return "newingredients";
    }

    //recipe _id
    @GetMapping("/confirmation/{id}")
    public String getConfirmationPage(@PathVariable Long id, Model m, Principal p) {
        Recipe recipe = recipeRepository.findById(id).get();
        m.addAttribute("recipe", recipe);
        m.addAttribute("ingredients", recipe.getIngredients());
        if (p != null) {
            m.addAttribute("user", appUserRepository.findByUsername(p.getName()));
        }
        return "confirmation";
    }

    @PostMapping("/recipes/{id}/ingredients/new")
    public RedirectView createNewIngredient(@PathVariable Long id, String name, String quantity) {
        Recipe recipe = recipeRepository.findById(id).get();
        Ingredient newIngredient = new Ingredient(name, quantity, recipe);
        ingredientRepository.save(newIngredient);

        return new RedirectView("/recipes/" + id + "/ingredients/new");

    }

    @GetMapping("/recipes/{id}/steps/new")
    public String getNewStepForm(@PathVariable Long id, Model m, Principal p) {
        Recipe recipe = recipeRepository.findById(id).get();
        m.addAttribute("recipe", recipe);
        m.addAttribute("steps", recipe.getSteps());
        if (p != null) {
            m.addAttribute("user", appUserRepository.findByUsername(p.getName()));
        }
        return "addstep";

    }

    @PostMapping("/recipes/{id}/steps/new")
    public RedirectView createNewStep(@PathVariable Long id, int stepNumber, String description) {
        Recipe recipe = recipeRepository.findById(id).get();
        Step newStep = new Step(stepNumber, description, recipe);
        stepRepository.save(newStep);

        return new RedirectView("/recipes/" + id + "/steps/new");
    }

    @GetMapping("/recipes/{id}/ingredients/{id2}/update")
    public String getEditIngredientForm(@PathVariable Long id, @PathVariable Long id2, Model m) {
        Recipe recipe = recipeRepository.findById(id).get();
        Ingredient ingredientToUpdate = ingredientRepository.findById(id2).get();

        m.addAttribute("recipe", recipe);
        m.addAttribute("ingredient", ingredientToUpdate);

        return "updateingredient";

    }

    @PostMapping("/recipes/{id}/ingredients/{id2}/saveUpdate")
    public RedirectView editIngredient(@PathVariable Long id, @PathVariable Long id2, String name, String quantity, Model m) {
        Recipe recipe = recipeRepository.findById(id).get();
        Ingredient ingredientToUpdate = ingredientRepository.findById(id2).get();
        boolean flag = false;
        if(!ingredientToUpdate.getName().equals(name)) {
            ingredientToUpdate.setName(name);
            flag = true;
        }
        if(!ingredientToUpdate.getQuantity().equals(quantity)) {
            ingredientToUpdate.setQuantity(quantity);
            flag = true;
        }

        if(flag) {
            ingredientRepository.save(ingredientToUpdate);
        }

        return new RedirectView("/recipes/" + id);
    }

    @PostMapping("/recipes/{id}/ingredients/{id2}/delete")
    public RedirectView deleteIngredient(@PathVariable Long id, @PathVariable Long id2, Model m) {
        Recipe recipe = recipeRepository.findById(id).get();
        Ingredient ingredientToDelete = ingredientRepository.findById(id2).get();

        ingredientRepository.delete(ingredientToDelete);

        return new RedirectView("/recipes/" + id);
    }

}
