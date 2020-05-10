package com.plopcas.bb.controller;

import com.github.javafaker.Faker;
import com.plopcas.bb.model.UserSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Tournament page controller.
 */
@Controller
public class TournamentController {
    private final UserSession userSession;
    private final Faker faker;

    public TournamentController(UserSession userSession) {
        this.userSession = userSession;
        faker = new Faker();
    }

    @GetMapping("tournament/{id}")
    public String getTournamentPage(@PathVariable String id, Model model) {
        if (userSession.getUsername() == null) {
            return "redirect:/";
        }
        userSession.setTournamentId(id);
        return "tournament";
    }
}
