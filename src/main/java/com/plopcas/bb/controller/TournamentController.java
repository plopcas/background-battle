package com.plopcas.bb.controller;

import com.github.javafaker.Faker;
import com.plopcas.bb.model.Tournament;
import com.plopcas.bb.model.UserSession;
import com.plopcas.bb.service.TournamentService;
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
    private final TournamentService tournamentService;
    private final Faker faker;

    public TournamentController(UserSession userSession, TournamentService tournamentService) {
        this.userSession = userSession;
        this.tournamentService = tournamentService;
        faker = new Faker();
    }

    @GetMapping("tournament/{id}")
    public String getTournamentPage(@PathVariable String id, Model model) {
        if (userSession.getUsername() == null) {
            return "redirect:/";
        }
        userSession.setTournamentId(id);
        setHost(id);
        return "tournament";
    }

    private void setHost(@PathVariable String id) {
        // First player is the host
        Tournament tournament = tournamentService.findTournamentById(id);
        if (tournament.getPlayers().size() == 1) {
            userSession.setHost(true);
        }
    }
}
