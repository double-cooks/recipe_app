package tgreenidge.com.recipe_app.recipes.models;

import javax.persistence.*;

@Entity
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @ManyToOne
    Recipe recipe;

    int stepNumber;
    String description;

    public long getId() { return this.id; }

    public int getStepNumber() {
        return this.stepNumber;
    }

    public String getDescription() {
        return this.description;
    }

    public Recipe getRecipe() { return this.recipe; }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
