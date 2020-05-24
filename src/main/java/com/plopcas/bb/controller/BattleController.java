package com.plopcas.bb.controller;

import com.plopcas.bb.model.PlayerImage;
import com.plopcas.bb.model.Round;
import com.plopcas.bb.model.Tournament;
import com.plopcas.bb.model.UserSession;
import com.plopcas.bb.service.TournamentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Battle page controller.
 */
@Controller
public class BattleController {

    private final UserSession userSession;
    private final TournamentService tournamentService;

    public BattleController(UserSession userSession, TournamentService tournamentService) {
        this.userSession = userSession;
        this.tournamentService = tournamentService;
    }

    @GetMapping("battle")
    public String getBattlePage(Model model) {
        if (userSession.getUsername() == null) {
            return "redirect:/";
        }
        Tournament tournament = tournamentService.findTournamentById(userSession.getTournamentId());
        if (tournament.getRound() >= 1) {
            List<PlayerImage> images = tournament
                    .getPlayers()
                    .values()
                    .stream()
                    .flatMap(p -> p.getImages()
                            .stream()
                            .map(i -> new PlayerImage(p.getUsername(), i.getData().getMedium().getUrl()))
                    )
                    .collect(toList());
            Collections.shuffle(images);
            if (userSession.isHost()) {
                tournament.getRounds().add(new Round(images.get(0), images.get(1)));
            } else {
                // Super naive implementation of synchronisation
                int count = 0;
                while (tournament.getRounds().size() < tournament.getRound() && count < 10) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // noop
                    }
                    count++;
                }
            }
            model.addAttribute("round", tournament.getRounds().get(tournament.getRound() - 1));
            model.addAttribute("roundNumber", tournament.getRound());
        }
        return "battle";
    }

    @GetMapping("battle-waiting")
    public String getWaitingRoom(Model model) {
        if (userSession.getUsername() == null) {
            return "redirect:/";
        }

        Tournament tournament = tournamentService.findTournamentById(userSession.getTournamentId());

        Round round = tournament.getRounds().get(tournament.getRound() - 1);
        model.addAttribute("round", round);

        Integer playersVoted = round.getPlayersThatVotedLeft().size() + round.getPlayersThatVotedRight().size();
        model.addAttribute("playersVoted", playersVoted);
        model.addAttribute("playersTotal", tournament.getPlayers().size());
        model.addAttribute("allowNextRound", false);

        if (playersVoted == tournament.getPlayers().size()) {
            if (userSession.isHost()) {
                model.addAttribute("allowNextRound", true);
            }

            if (round.getPlayersThatVotedLeft().size() > round.getPlayersThatVotedRight().size()) {
                model.addAttribute("winner", round.getLeft());
            } else if (round.getPlayersThatVotedRight().size() > round.getPlayersThatVotedLeft().size()) {
                model.addAttribute("winner", round.getRight());
            } else {
                model.addAttribute("winner", round.getLeft());
                model.addAttribute("tie", true);
            }

            model.addAttribute("showWinner", true);
        }

        return "battleWaitingRoom";
    }
}
