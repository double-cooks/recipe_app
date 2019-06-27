package tgreenidge.com.recipe_app.recipes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tgreenidge.com.recipe_app.recipes.models.Ingredient;
import tgreenidge.com.recipe_app.recipes.models.Recipe;
import tgreenidge.com.recipe_app.recipes.models.Step;
import tgreenidge.com.recipe_app.recipes.repositories.AppUserRepository;
import tgreenidge.com.recipe_app.recipes.repositories.RecipeRepository;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*
    RestController for Alexa routes
 */
@RestController
public class AlexaController {
    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    RecipeRepository recipeRepository;

    /*
        Returns all the recipes that are created in the app
     */
    @RequestMapping(value = "/alexa/recipes", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<Recipe>> getAlexaRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();
        return new ResponseEntity<List<Recipe>>(recipes, HttpStatus.OK);
    }

    /*
        Returns the first recipe that is created in the app with a given title
     */
    @RequestMapping(value = "/alexa/recipes/{title}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<Recipe>> getAlexaRecipesByTitle(@PathVariable String title) {
        List<Recipe> recipes = recipeRepository.findByTitle(title.toLowerCase());
        //get the first recipe with that title
        Recipe recipe = recipes.get(0);
        return new ResponseEntity<List<Recipe>>(recipes, HttpStatus.OK);
    }

    /*
        Returns the cooktime that are created in the app
     */
    @RequestMapping(value = "/alexa/recipes/{title}/cooktime", method = RequestMethod.GET)
    public @ResponseBody String getAlexaRecipeCookTimeByTitle(@PathVariable String title) {
        List<Recipe> recipes = recipeRepository.findByTitle(title.toLowerCase());
        //get the first recipe with that title
        Recipe recipe = recipes.get(0);
        return recipe.getCookTime();
    }

    /*
        Returns the preptime that are created in the app
     */
    @RequestMapping(value = "/alexa/recipes/{title}/preptime", method = RequestMethod.GET)
    public @ResponseBody String getAlexaRecipePrepTimeByTitle(@PathVariable String title) {
        List<Recipe> recipes = recipeRepository.findByTitle(title.toLowerCase());
        //get the first recipe with that title
        Recipe recipe = recipes.get(0);
        return recipe.getPrepTime();
    }

    /*
        Returns the ingredients for a given recipe by ingredient in JSON format
     */
    @RequestMapping(value = "/alexa/recipes/{title}/ingredients", produces = "application/json", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<Ingredient>> getAlexaRecipeIngredients(@PathVariable String title) {
        //get the first recipe with that title
        List<Ingredient> ingredients = recipeRepository.findByTitle(title.toLowerCase()).get(0).getIngredients();
        return new ResponseEntity<List<Ingredient>>(ingredients, HttpStatus.OK);
    }

    /*
        Returns the steps for a given recipe by ingredient in JSON format
     */
    @RequestMapping(value = "/alexa/recipes/{title}/steps", produces = "application/json", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<Step>> getAlexaRecipeSteps(@PathVariable String title) {
        //get the first recipe with that title
        List<Step> steps = recipeRepository.findByTitle(title.toLowerCase()).get(0).getSteps().stream().sorted(Comparator.comparing(Step::getStepNumber)).collect(Collectors.toList());;
        return new ResponseEntity<List<Step>>(steps, HttpStatus.OK);
    }
}
