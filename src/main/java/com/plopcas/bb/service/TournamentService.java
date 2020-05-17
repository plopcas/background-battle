package com.plopcas.bb.service;

import com.plopcas.bb.model.Tournament;
import com.plopcas.bb.utils.IdGenerator;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class TournamentService {
    // For now keeping all the tournaments in memory
    private Map<String, Tournament> activeTournaments = new HashMap<>();

    public Tournament saveTournament(Tournament tournament) {
        if(tournament.getId() == null) {
            tournament.setId(IdGenerator.getAlphaNumericString(4));
        }
        activeTournaments.put(tournament.getId(), tournament);
        return tournament;
    }

    public Collection<Tournament> findAll() {
        return activeTournaments.values();
    }

    public Tournament findTournamentById(String id) {
        return activeTournaments.get(id);
    }

    public void deleteTournamentById(String id) {
        activeTournaments.remove(id);
    }
}
