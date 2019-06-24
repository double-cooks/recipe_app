package tgreenidge.com.recipe_app.recipes.models;


import javax.persistence.*;
import java.util.List;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String title;

    @OneToMany(mappedBy = "recipe")
    List<Ingredient> ingredients;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    List<Step> steps;

    @ManyToOne
    User user;

    @ManyToOne
    Recipe recipe;

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

    public User getUser() {
        return this.user;
    }

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

    public void setUser(User user) {
        this.user = user;
    }
}
