package com.plopcas.bb.controller;

import com.github.javafaker.Faker;
import com.plopcas.bb.model.UserSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Welcome page controller.
 */
@Controller
public class WelcomeController {
    private final UserSession userSession;
    private final Faker faker;

    public WelcomeController(UserSession userSession) {
        this.userSession = userSession;
        faker = new Faker();
    }

    @GetMapping("/")
    public String getWelcomePage(Model model) {
        String username = getOrSetUsername();
        return "index";
    }

    private String getOrSetUsername() {
        String username = userSession.getUsername();
        if (username == null || username.isBlank()) {
            username = faker.harryPotter().character();
            userSession.setUsername(username);
        }
        return username;
    }
}
