package tgreenidge.com.recipe_app.recipes;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import tgreenidge.com.recipe_app.recipes.models.AppUser;
import tgreenidge.com.recipe_app.recipes.models.Ingredient;
import tgreenidge.com.recipe_app.recipes.models.Recipe;
import tgreenidge.com.recipe_app.recipes.models.Step;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestModels {

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Test
    public void testAppUser() {
        AppUser user = new AppUser();
        String firstName = "testFirstName";
        String lastName = "testLastName";
        String userName = "test";
        String userPassword = "password";
        List<Recipe> emptyList = new ArrayList<>();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(userName);
        user.setPassword(userPassword);
        user.setRecipes(emptyList);

        assertTrue("isAccountNonExpired should return true", user.isAccountNonExpired());
        assertTrue("isAccountNonLocked should return true", user.isAccountNonLocked());
        assertTrue("isCredentialNonExpired should return true", user.isCredentialsNonExpired());
        assertTrue("isEnabled should return true", user.isEnabled());
        assertNull("getAuthorties should return null", user.getAuthorities());
        assertEquals("setFirstName should set firstName to specified value" +
                " while getFirst will return that value", firstName, user.getFirstName());
        assertEquals("setLastName should set lastName to specified value" +
                " while getLastName will return that value", lastName, user.getLastName());
        assertEquals("setUserName should set userName to specified value" +
                " while getUserName will return that value", userName, user.getUsername());
        assertEquals("setPassword should set password to specified value" +
                " while getPassword will return that value", userPassword, user.getPassword());
        assertEquals("setRecipes should set firstName to specified value" +
                " while getRecipes will return that value", emptyList.size(), user.getRecipes().size());
        assertNotNull("id is auto generated and should not be null", user.getId());
    }

    @Test
    public void testRecipe() {
        Recipe recipe = new Recipe();
        String title = "testTitle";
        String prepTime = "testPrepTime";
        String cookTime = "testCookTime";
        List<Ingredient> emptyIngredientList = new ArrayList<>();
        List<Step> emptyStepList = new ArrayList<>();
        AppUser user = new AppUser("testUserName", "testPassword", "testFirstName", "testLastName");

        recipe.setTitle(title);
        recipe.setPreTime(prepTime);
        recipe.setCookTime(cookTime);
        recipe.setIngredients(emptyIngredientList);
        recipe.setSteps(emptyStepList);
        recipe.setAppUser(user);

        assertEquals("The getter method should return what" +
                " was set by the setter method.", title.toLowerCase(), recipe.getTitle());
        assertEquals("The getter method should return what" +
                " was set by the setter method.", prepTime, recipe.getPrepTime());
        assertEquals("The getter method should return what" +
                " was set by the setter method.", cookTime, recipe.getCookTime());
        assertEquals("The getter method should return what" +
                " was set by the setter method.", emptyIngredientList.size(), recipe.getIngredients().size());
        assertEquals("The getter method should return what" +
                " was set by the setter method.", emptyStepList.size(), recipe.getSteps().size());
        assertEquals("The getter method should return what" +
                " was set by the setter method.", user.getUsername(), recipe.getAppUser().getUsername());
    }

    @Test
    public void testIngredient() {
        Ingredient ingredient = new Ingredient();
        String name = "testName";
        String quantity = "testQuantity";
        Recipe recipe = new Recipe();

        recipe.setTitle("testTitle");
        ingredient.setName(name);
        ingredient.setQuantity(quantity);
        ingredient.setRecipe(recipe);

        assertEquals("The getter method should return what" +
                " was set by the setter method.", name, ingredient.getName());
        assertEquals("The getter method should return what" +
                " was set by the setter method.", quantity, ingredient.getQuantity());
        assertEquals("The getter method should return what" +
                " was set by the setter method.", recipe.getTitle(), ingredient.getRecipe().getTitle());
    }

    @Test
    public void testStep() {
        Step step = new Step();
        int stepNumber = 1;
        String description = "testDes";
        Recipe recipe = new Recipe();

        recipe.setTitle("testTitle");
        step.setStepNumber(stepNumber);
        step.setDescription(description);
        step.setRecipe(recipe);

        assertEquals("The getter method should return what" +
                " was set by the setter method.", stepNumber, step.getStepNumber());
        assertEquals("The getter method should return what" +
                " was set by the setter method.", description, step.getDescription());
        assertEquals("The getter method should return what" +
                " was set by the setter method.", recipe.getTitle(), step.getRecipe().getTitle());
    }
}
