package com.plopcas.bb.api;

import com.plopcas.bb.model.Player;
import com.plopcas.bb.model.Tournament;
import com.plopcas.bb.service.TournamentService;
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
    public ResponseEntity<?> createTournament(@RequestBody String username) {
        Tournament tournament = new Tournament();
        Map<String, Player> players = tournament.getPlayers();
        players.put(username, new Player());
        return ResponseEntity.ok().body(tournamentService.saveTournament(tournament));
    }

    @PostMapping("/join/{id}")
    public ResponseEntity<?> joinTournament(@PathVariable String id, @RequestBody String username) {
        Tournament tournament= tournamentService.findTournamentById(id);
        Map<String, Player> players = tournament.getPlayers();
        if (players.size() >= 8 && players.get(username) == null) {
            return ResponseEntity.badRequest().build();
        }
        players.put(username, new Player());
        return ResponseEntity.ok().body(tournamentService.saveTournament(tournament));
    }
}