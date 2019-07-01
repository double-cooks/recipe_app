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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//add documentation in readme for developing with Alexa on this app

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
        Returns the recipes that is created in the app with a given title
     */
    @RequestMapping(value = "/alexa/recipes/{title}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<Recipe>> getAlexaRecipesByTitle(@PathVariable String title) {
        List<Recipe> recipes = recipeRepository.findByTitle(title.toLowerCase());
        return new ResponseEntity<List<Recipe>>(recipes, HttpStatus.OK);
    }

    /*
        Returns the cook time for a recipe with a specific title
     */
    @RequestMapping(value = "/alexa/recipes/{title}/cooktime", method = RequestMethod.GET)
    public @ResponseBody String getAlexaRecipeCookTimeByTitle(@PathVariable String title) {
        List<Recipe> recipes = recipeRepository.findByTitle(title.toLowerCase());
        //get the first recipe with that title
        return recipes.size() > 0 ? recipes.get(0).getCookTime() : "No recipe was found with that name";
    }

    /*
        Returns the prep time for a recipe with a specific title
     */
    @RequestMapping(value = "/alexa/recipes/{title}/preptime", method = RequestMethod.GET)
    public @ResponseBody String getAlexaRecipePrepTimeByTitle(@PathVariable String title) {
        List<Recipe> recipes = recipeRepository.findByTitle(title.toLowerCase());
        //get the first recipe with that title
        return recipes.size() > 0 ? recipes.get(0).getPrepTime() : "No recipe was found with that name";
    }

    /*
        Returns the ingredients for a given recipe by title in JSON format
     */
    @RequestMapping(value = "/alexa/recipes/{title}/ingredients", produces = "application/json", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<Ingredient>> getAlexaRecipeIngredients(@PathVariable String title) {
        List<Recipe> recipes = recipeRepository.findByTitle(title.toLowerCase());

        //get the first recipe with that title
        List<Ingredient> ingredients = recipes.size() > 0 ? recipes.get(0).getIngredients() : Collections.emptyList();
        return new ResponseEntity<List<Ingredient>>(ingredients, HttpStatus.OK);
    }

    /*
        Returns the steps for a given recipe by by title in JSON format
     */
    @RequestMapping(value = "/alexa/recipes/{title}/steps", produces = "application/json", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<Step>> getAlexaRecipeSteps(@PathVariable String title) {
        List<Recipe> recipes = recipeRepository.findByTitle(title.toLowerCase());

        //get the first recipe with that title
        List<Step> steps = recipes.size() > 0 ?
                recipes.get(0).getSteps().stream().sorted(Comparator.comparing(Step::getStepNumber)).collect(Collectors.toList()) :
                Collections.emptyList();

        return new ResponseEntity<List<Step>>(steps, HttpStatus.OK);
    }
}
