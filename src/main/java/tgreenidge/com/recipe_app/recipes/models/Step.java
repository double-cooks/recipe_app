package tgreenidge.com.recipe_app.recipes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @JsonBackReference // necessary for JSON response for RestController for Alexa
    @ManyToOne
    Recipe recipe;

    int stepNumber;
    String description;

    public Step(){}

    public Step(int stepNumber, String description, Recipe recipe) {
        this.stepNumber = stepNumber;
        this.description = description;
        this.recipe = recipe;
    }

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
