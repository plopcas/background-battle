package com.plopcas.bb.api;

import com.plopcas.bb.model.Player;
import com.plopcas.bb.model.Tournament;
import com.plopcas.bb.service.TournamentService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/tournaments")
public class TournamentResource {
    private static Logger log = LoggerFactory.getLogger(TournamentResource.class);

    @Autowired
    private TournamentService tournamentService;

    @PostMapping("/create")
    public ResponseEntity<?> createTournament(@RequestHeader("Web-Monetization-Id") String monetizationId,
                                              @RequestBody String username) {
        Tournament tournament = new Tournament();
        // Naive approach, we should check the ID with the Interledger
        if (!StringUtils.isBlank(monetizationId)) {
            log.info("Premium user, removing max players limit");
            tournament.setMaxPlayers(20);
        }
        Map<String, Player> players = tournament.getPlayers();
        players.put(username, new Player(username, true));
        return ResponseEntity.ok().body(tournamentService.saveTournament(tournament));
    }

    @PostMapping("/join/{id}")
    public ResponseEntity<?> joinTournament(@PathVariable String id, @RequestBody String username) {
        Tournament tournament = tournamentService.findTournamentById(id);
        Map<String, Player> players = tournament.getPlayers();
        if (players.size() >= tournament.getMaxPlayers() && players.get(username) == null) {
            return ResponseEntity.badRequest().build();
        }
        players.put(username, new Player(username));
        return ResponseEntity.ok().body(tournamentService.saveTournament(tournament));
    }

    @PostMapping("/start/{id}")
    public ResponseEntity<?> start(@PathVariable String id, @RequestBody String username) {
        Tournament tournament = tournamentService.findTournamentById(id);
        Map<String, Player> players = tournament.getPlayers();
        if (players.size() >= tournament.getMaxPlayers() && players.get(username) == null) {
            return ResponseEntity.badRequest().build();
        }
        tournament.setRound(1);
        return ResponseEntity.ok().build();
    }
}
