package tgreenidge.com.recipe_app.recipes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tgreenidge.com.recipe_app.recipes.models.Ingredient;
import tgreenidge.com.recipe_app.recipes.models.Recipe;
import tgreenidge.com.recipe_app.recipes.models.Step;
import tgreenidge.com.recipe_app.recipes.repositories.AppUserRepository;
import tgreenidge.com.recipe_app.recipes.repositories.RecipeRepository;

import java.security.Principal;
import java.util.List;

@RestController
public class AlexaController {
    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    RecipeRepository recipeRepository;

    @RequestMapping(value = "/alexa/recipes", method = RequestMethod.GET)
    public  @ResponseBody String getAlexaRecipes(Principal p) {
        Recipe recipe = recipeRepository.findById((long)(12));
        return recipe.toString();
    }

    @RequestMapping(value = "/alexa/recipes/{title}/ingredients", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public List getAlexaRecipeIngredients(@PathVariable String title) {
        List<Ingredient> ingredients = recipeRepository.findByTitle(title).getIngredients();

        return ingredients;
    }

    @RequestMapping(value = "/alexa/recipes/{title}/steps", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody @GetMapping("alexa/recipes/{title}/steps")
    public List getAlexaRecipeSteps(@PathVariable String title) {
        List<Step> steps = recipeRepository.findByTitle(title).getSteps();

        return steps ;

    }
}
