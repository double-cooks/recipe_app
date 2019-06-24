package tgreenidge.com.recipe_app.recipes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tgreenidge.com.recipe_app.recipes.repositories.AppUserRepository;

@Controller
@RestController
public class AppUserController {

    @Autowired
    AppUserRepository appUserRepository;

    @GetMapping("/")
    public String getRootPath() {
        return "helloGreeting";
    }
}
