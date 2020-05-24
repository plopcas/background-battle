package com.plopcas.bb.controller;

import com.plopcas.bb.model.Player;
import com.plopcas.bb.model.Tournament;
import com.plopcas.bb.model.UserSession;
import com.plopcas.bb.service.TournamentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Controller
public class WaitingRoomController {

    private final UserSession userSession;
    private final TournamentService tournamentService;

    public WaitingRoomController(UserSession userSession, TournamentService tournamentService) {
        this.userSession = userSession;
        this.tournamentService = tournamentService;
    }

    @GetMapping("waiting")
    public String getWaitingRoom(Model model) {
        if (userSession.getUsername() == null) {
            return "redirect:/";
        }
        Tournament tournament = tournamentService.findTournamentById(userSession.getTournamentId());
        Map<String, Player> players = tournament.getPlayers();
        List<Player> notReadyPlayers = players.values().stream().filter(p -> p.getImages().size() < 3).collect(toList());
        List<Player> readyPlayers = players.values().stream().filter(p -> p.getImages().size() == 3).collect(toList());
        model.addAttribute("notReadyPlayers", notReadyPlayers);
        model.addAttribute("readyPlayers", readyPlayers);
        model.addAttribute("round", tournament.getRound());
        if (notReadyPlayers.size() == 0 && readyPlayers.size() >= 2) {
            model.addAttribute("allowBattle", true);
        } else {
            model.addAttribute("allowBattle", false);
        }
        if (tournament.getRound() >= 1) {
            return "redirect:/battle";
        } else {
            return "waitingRoom";
        }
    }
}
