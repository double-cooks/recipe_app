package tgreenidge.com.recipe_app.recipes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import tgreenidge.com.recipe_app.recipes.controllers.AlexaController;
import tgreenidge.com.recipe_app.recipes.controllers.AppUserController;
import tgreenidge.com.recipe_app.recipes.controllers.RecipeController;
import tgreenidge.com.recipe_app.recipes.models.AppUser;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RecipesApplicationTests {

	@Autowired
	AppUserController appUserControllerTest;
	@Autowired
	RecipeController recipeControllerTest;

	@Autowired
    AlexaController alexaController;

	@Autowired
	private MockMvc mockMvc;


	// Helper Methods
	public AppUser createTestUser(){
		AppUser testUser = new AppUser("user", "password", "testFirstName", "testLastName");
		return testUser;
	}

	// Unit/Integration Testing
	@Test
	public void testControllerIsAutowired() {
		assertNotNull(appUserControllerTest);
	}

	@Test
	@WithMockUser(username = "dd", password = "dd", roles = "USER")
	public void testIntegrationProfile() throws Exception {
		mockMvc.perform(get("/profile"))
				.andExpect(content().string(containsString("My Profile")));
	}

	@Test
	public void testIntegrationLogin() throws Exception{
		mockMvc.perform(formLogin("/profile").user("user").password("password"));

	}

	@Test
	public void testRequestToRootGivesWelcome() throws Exception {
		mockMvc.perform(get("/")).andExpect(content().string(containsString("Welcome")));
	}

	@Test
	public void testRequestToLogoutGivesSuccess() throws Exception {
		mockMvc.perform(get("/logoutSuccess")).andExpect(content().string(containsString("Logout success!")));
	}

	@Test
	public void testRequestToLogInGivesUsername() throws Exception {
		mockMvc.perform(get("/login")).andExpect(content().string(containsString("Username")));
	}

	@Test
	public void testRequestToSignUpGivesUsername() throws Exception {
		mockMvc.perform(get("/login")).andExpect(content().string(containsString("Username")));
	}


	@Test
	public void testSlashNotSignedInRoutePass() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().isOk());
	}

	@Test
	public void testRequestToSignUp() throws Exception {
		mockMvc.perform(get("/signup")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void testRequestToLogIn() throws Exception {
		mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void testCreateActualUser(){
		AppUser testUser = createTestUser();
		assertEquals("User First Name should be testFirstName", "testFirstName", testUser.getFirstName());
		assertEquals("User Last Name should be testLastName", "testLastName", testUser.getLastName());
		assertEquals("User name should be user", "user", testUser.getUsername());
	}

	@Test
	public void testLogoutRoutePass() throws Exception {
		mockMvc.perform(get("/logout")).andExpect(status().is3xxRedirection());
	}

	@Test
	public void testLogoutSuccessRoutePass() throws Exception {
		mockMvc.perform(get("/logoutsuccess")).andExpect(status().is3xxRedirection());
	}

	@Test
	public void testAboutUsNotSignedInRoutePass() throws Exception {
		mockMvc.perform(get("/aboutus")).andExpect(status().is3xxRedirection());
	}

	@Test
	public void testCreateRecipeNotSignedInRoutePass() throws Exception {
		mockMvc.perform(get("/recipes/create")).andExpect(status().is3xxRedirection());
	}




	// Recipe Controller tests
	//tests with @WithMockUser annotation must be a user that exists on the server this test code
	// runs against, and id's must correlate to what exists on the db the server connects to

	//get routes
	@Test
	public void testRecipeControllerIsAutowired() {
		assertNotNull(recipeControllerTest);
	}

	@Test
	public void getRecipeCreateTest() throws Exception {
		mockMvc.perform(get("/recipes/create"))
				.andDo(print()).andExpect(status().is3xxRedirection());
	}
	@Test
	@WithMockUser(username = "dd", password = "dd", roles = "USER")
	public void getRecipeCreateTestAuth() throws Exception {
		mockMvc.perform(get("/recipes/create"))
				.andExpect(content().string(containsString("Create Recipe")));
	}

	@Test

	public void getIngredientCreateTest() throws Exception {
		mockMvc.perform(get("/recipes/{id}/ingredients/new/{isInitialCreation}","135", "true"))
				.andDo(print()).andExpect(status().is3xxRedirection());
	}
	@Test
	@WithMockUser(username = "dd", password = "dd", roles = "USER")
	public void getIngredientCreateTest2() throws Exception {
		mockMvc.perform(get("/recipes/{id}/ingredients/new/{isInitialCreation}","135", "true"))
				.andExpect(content().string(containsString("Create Ingredient")));
	}

	@Test
	public void getIngredientUpdate() throws Exception {
		mockMvc.perform(get("/recipes/{id}/ingredients/{id2}/update/{isInitialCreation}","135", "136", "true"))
				.andDo(print()).andExpect(status().is3xxRedirection());
	}

	@Test
	@WithMockUser(username = "dd", password = "dd", roles = "USER")
	public void getIngredientUpdate2() throws Exception {
		mockMvc.perform(get("/recipes/{id}/ingredients/{id2}/update/{isInitialCreation}", "135", "136", "false"))
				.andExpect(content().string(containsString("Update Ingredient")));
	}

	@Test
	public void getStepCreateTest() throws Exception {
		mockMvc.perform(get("/recipes/{id}/steps/new/{isInitialCreation}","135", "true"))
				.andDo(print()).andExpect(status().is3xxRedirection());

	}

	@Test
	@WithMockUser(username = "dd", password = "dd", roles = "USER")
	public void getStepCreateTest2() throws Exception {
		mockMvc.perform(get("/recipes/{id}/steps/new/{isInitialCreation}","135", "false"))
				.andExpect(content().string(containsString("Add Step")));
	}

	@Test
	public void getStepUpdate() throws Exception {
		mockMvc.perform(get("/recipes/{id}/steps/{id2}/update/{isInitialCreation}","135", "140", "true"))
				.andDo(print()).andExpect(status().is3xxRedirection());
	}

	@Test
	@WithMockUser(username = "dd", password = "dd", roles = "USER")
	public void getStepUpdate2() throws Exception {
		mockMvc.perform(get("/recipes/{id}/steps/{id2}/update/{isInitialCreation}","135", "140", "false"))
				.andExpect(content().string(containsString("Update Step")));
	}


	//post routes
	@Test
	@WithMockUser(username = "dd", password = "dd", roles = "USER")
	public void recipesCreateTest() throws Exception {
		mockMvc.perform(post("/recipes/create")
				.param("title", "IntegrationTestTitle")
				.param("prepTime", "1 hour")
				.param("cookTime", "2 hours"))
				.andDo(print()).andExpect(status().is3xxRedirection());

	}

	@Test
	@WithMockUser(username = "dd", password = "dd", roles = "USER")
	public void ingredientCreateTest() throws Exception {
		mockMvc.perform(post("/recipes/{id}/ingredients/new/{isInitialCreation}","135", "true")
				.param("name", "IntegName")
				.param("quantity", "IntegQuantity"))
				.andDo(print()).andExpect(status().is3xxRedirection());

	}

	@Test
	@WithMockUser(username = "dd", password = "dd", roles = "USER")
	public void ingredientCreateTest2() throws Exception {
		mockMvc.perform(post("/recipes/{id}/ingredients/new/{isInitialCreation}","135", "false")
				.param("name", "IntegNameFRoute")
				.param("quantity", "IntegQuantityFRoute"))
				.andDo(print()).andExpect(status().is3xxRedirection());

	}

	@Test
	@WithMockUser(username = "dd", password = "dd", roles = "USER")
	public void ingredientUpdate() throws Exception {
		mockMvc.perform(post("/recipes/{id}/ingredients/{id2}/update/{isInitialCreation}","135", "136", "true")
				.param("name", "updateIntegName")
				.param("quantity", "updateIntegQuantity"))
				.andDo(print()).andExpect(status().is3xxRedirection());
	}

	@Test
	@WithMockUser(username = "dd", password = "dd", roles = "USER")
	public void ingredientUpdate2() throws Exception {
		mockMvc.perform(post("/recipes/{id}/ingredients/{id2}/update/{isInitialCreation}","135", "136", "false")
				.param("name", "updateIntegNameFRoute")
				.param("quantity", "updateIntegQuantityFRoute"))
				.andDo(print()).andExpect(status().is3xxRedirection());
	}

	@Test
	@WithMockUser(username = "dd", password = "dd", roles = "USER")
	public void stepCreateTest() throws Exception {
		mockMvc.perform(post("/recipes/{id}/steps/new/{isInitialCreation}","135", "true")
				.param("stepNumber", "1")
				.param("description", "IntegDescription"))
				.andDo(print()).andExpect(status().is3xxRedirection());

	}

	@Test
	@WithMockUser(username = "dd", password = "dd", roles = "USER")
	public void stepCreateTest2() throws Exception {
		mockMvc.perform(post("/recipes/{id}/steps/new/{isInitialCreation}","135", "false")
				.param("stepNumber", "1")
				.param("description", "IntegDescriptionFRoute"))
				.andDo(print()).andExpect(status().is3xxRedirection());

	}

	@Test
	@WithMockUser(username = "dd", password = "dd", roles = "USER")
	public void stepUpdate() throws Exception {
		mockMvc.perform(post("/recipes/{id}/steps/{id2}/update/{isInitialCreation}","135", "140", "true")
				.param("stepNumber", "2")
				.param("description", "updateIntegDesc"))
				.andDo(print()).andExpect(status().is3xxRedirection());
	}

	@Test
	@WithMockUser(username = "dd", password = "dd", roles = "USER")
	public void stepUpdate2() throws Exception {
		mockMvc.perform(post("/recipes/{id}/steps/{id2}/update/{isInitialCreation}","135", "140", "false")
				.param("stepNumber", "2")
				.param("description", "updateIntegDescFRoute"))
				.andDo(print()).andExpect(status().is3xxRedirection());
	}
	///recipes/{id}/steps/{id2}/delete
	///recipes/{id}/ingredients/{id2}/delete

	// alexa controller tests
    @Test
    public void testAlexaControllerIsAutowired() {
	    assertNotNull(alexaController);
    }

    @Test
    public void alexaRecipesViewJsonTest() throws Exception {
        this.mockMvc.perform(get("/alexa/recipes"))
                .andDo(print()).andExpect(status().isOk())
                .andDo(print()).andExpect(content().contentType("application/json;charset=UTF-8"));

    }


    @Test
    public void alexaRouteTestRecipe() throws Exception {
        this.mockMvc.perform(get("/alexa/recipes/test"))
                .andDo(print()).andExpect(content().contentType("application/json;charset=UTF-8"));
//                .andExpect(jsonPath("$.title", is("test")));
    }

    @Test
    public void alexaRouteTestStep() throws Exception {
	    this.mockMvc.perform(get("/alexa/recipes/test/steps"))
                .andDo(print()).andExpect(content().contentType("application/json;charset=UTF-8"));
//                .andExpect(jsonPath("$.description", is("test")));
    }

    @Test
    public void alexaRouteTestIngredient() throws Exception {
        this.mockMvc.perform(get("/alexa/recipes/test/steps"))
                .andDo(print()).andExpect(content().contentType("application/json;charset=UTF-8"));
//                .andExpect(jsonPath("$.name", is("ingredient1")));
    }

}
