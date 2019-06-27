package tgreenidge.com.recipe_app.recipes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import tgreenidge.com.recipe_app.recipes.controllers.AppUserController;
import tgreenidge.com.recipe_app.recipes.controllers.RecipeController;
import tgreenidge.com.recipe_app.recipes.models.AppUser;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RecipesApplicationTests {

	@Autowired
	AppUserController appUserControllerTest;
	@Autowired
	RecipeController recipeControllerTest;

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
	@WithMockUser(username = "greg", password = "password", roles = "USER")
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
		mockMvc.perform(get("/")).andExpect(content().string(containsString("Welcome to Doubly")));
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




	// Recipe Controller
	@Test
	public void testRecipeControllerIsAutowired() {
		assertNotNull(recipeControllerTest);
	}

	@Test
	public void givenGreetURIWithPostAndFormData_whenMockMVC_thenResponseOK() {
		this.mockMvc.perform(post("/greetWithPostAndFormData").param("id", "1")
				.param("name", "John Doe")).andDo(print()).andExpect(status().isOk())

				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.message").value("Hello World John Doe!!!"))
				.andExpect(jsonPath("$.id").value(1));
	}


}
