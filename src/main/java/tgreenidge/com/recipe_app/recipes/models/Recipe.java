package tgreenidge.com.recipe_app.recipes.models;


import javax.persistence.*;
import java.util.List;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String title;
    String prepTime;
    String cookTime;

    @OneToMany(mappedBy = "recipe")
    List<Ingredient> ingredients;

    @OneToMany(mappedBy = "recipe")
    List<Step> steps;

    @ManyToOne
    AppUser appUser;

    public Recipe(){}

    public Recipe(String title, String prepTime, String cookTime, AppUser appUser){
        this.title = title;
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
        this.title = title;
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
