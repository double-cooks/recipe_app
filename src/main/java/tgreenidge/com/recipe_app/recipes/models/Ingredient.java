package tgreenidge.com.recipe_app.recipes.models;

import javax.persistence.*;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String name;
    String quantity;

    @ManyToOne
    Recipe recipe;

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
