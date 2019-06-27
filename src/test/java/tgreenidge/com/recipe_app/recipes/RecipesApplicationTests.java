package tgreenidge.com.recipe_app.recipes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import tgreenidge.com.recipe_app.recipes.models.AppUser;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RecipesApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	// Helper Methods
	public AppUser createTestUser(){
		AppUser testUser = new AppUser("user", "password", "testFirstName", "testLastName");
		return testUser;
	}

	// Route Testing
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

}
