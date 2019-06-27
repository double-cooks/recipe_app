package tgreenidge.com.recipe_app.recipes.models;


import javax.persistence.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String title;
    String prepTime;
    String cookTime;

    @JsonManagedReference // necessary for JSON response for RestController for Alexa
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Ingredient> ingredients;

    @JsonManagedReference // necessary for JSON response for RestController for Alexa
    @OneToMany(mappedBy = "recipe", cascade = {CascadeType.ALL})
    List<Step> steps;

    @JsonBackReference // necessary for JSON response for RestController for Alexa
    @ManyToOne
    AppUser appUser;

    public Recipe(){}

    public Recipe(String title, String prepTime, String cookTime, AppUser appUser){
        this.title = title.toLowerCase();
        this.appUser = appUser;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
    }

    public long getId() {
        return this.id;
    }

    public List<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public List<Step> getSteps() {
        return this.steps;
    }

    public String getTitle() {
        return this.title;
    }

    public AppUser getAppUser() {
        return this.appUser;
    }

    public String getPrepTime() { return this.prepTime; }

    public String getCookTime() { return this.cookTime; }

    public void setCookTime(String cookTime) { this.cookTime = cookTime; }

    public void setPreTime(String prepTime) { this.prepTime = prepTime; }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title.toLowerCase();
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

}
