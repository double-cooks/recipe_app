package tgreenidge.com.recipe_app.recipes.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class AppUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    // Should make username etc private
    @Column(unique = true)
    String username;
    String password;
    String firstname;
    String lastname;

    @JsonManagedReference // necessary for JSON response for RestController for Alexa
    @OneToMany(mappedBy = "appUser")
    public List<Recipe> recipes;

    public AppUser(){}

    public AppUser(String username, String password, String firstName, String lastName){
        this.username = username;
        this.password = password;
        this.firstname = firstName;
        this.lastname = lastName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getUsername() {
        return this.username;
    }

    public long getId() {
        return this.id;
    }
    public String getPassword() {
        return this.password;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public String getFirstName() {
        return this.firstname;
    }

    public String getLastName() {
        return this.lastname;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
