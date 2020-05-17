package com.plopcas.bb.model;

import java.util.HashMap;
import java.util.Map;

public class Tournament {
    private String id;
    private Map<String, Player> players = new HashMap<>();
    private Integer round = 0;

    public Tournament() {
    }

    public Tournament(String id, Map<String, Player> players) {
        this.id = id;
        this.players = players;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Player> getPlayers() {
        return players;
    }

    public void setPlayers(Map<String, Player> players) {
        this.players = players;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }
}
