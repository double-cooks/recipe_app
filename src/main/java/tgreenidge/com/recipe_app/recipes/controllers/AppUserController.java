package tgreenidge.com.recipe_app.recipes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import tgreenidge.com.recipe_app.recipes.models.AppUser;
import tgreenidge.com.recipe_app.recipes.models.Recipe;
import tgreenidge.com.recipe_app.recipes.repositories.AppUserRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AppUserController {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    BCryptPasswordEncoder encoder;

    @GetMapping("/")
    public String getRootPath(Principal p, Model m) {
        if (p != null) {
            m.addAttribute("user", appUserRepository.findByUsername(p.getName()));
        }
        return "index";
    }

    @GetMapping("/signup")
    public String getSignup(Principal p , Model m) {
        if (p != null) {
            m.addAttribute("user", appUserRepository.findByUsername(p.getName()));
        }
        return "signup";
    }

    @PostMapping("/signup")
    public RedirectView createUser(String username, String password, String firstName, String lastName) {
        AppUser newUser = new AppUser(username, encoder.encode(password), firstName, lastName);
        appUserRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/profile");
    }

    @GetMapping("/login")
    public String getLogin(Principal p, Model m) {
        if (p != null) {
            m.addAttribute("user", appUserRepository.findByUsername(p.getName()));
        }
        return "login";
    }

    @GetMapping("/logoutSuccess")
    public String getLogout(Principal p, Model m) {
        if (p != null) {
            m.addAttribute("user", appUserRepository.findByUsername(p.getName()));
        }
        return "logoutsuccess";
    }

    @GetMapping("/profile")
    public String getMyProfile(Principal p, Model m) {
        AppUser user = appUserRepository.findByUsername(p.getName() );
        List<Recipe> recipes = user.recipes;
        Iterable<AppUser> usersToFollowIterable = appUserRepository.findAll();

        m.addAttribute("recipes", recipes);
        m.addAttribute("principal", p.getName());
        if (p != null) {
            m.addAttribute("user", user);
        }

        return "profile";
    }
}
