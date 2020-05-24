package com.plopcas.bb.model;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Tournament {
    private String id;
    private Map<String, Player> players = new ConcurrentHashMap<>();
    private Integer maxPlayers = 3;
    private Integer round = 0;
    private List<Round> rounds = new CopyOnWriteArrayList<>();

    public Tournament() {
    }

    public Tournament(String id, Integer maxPlayers, Map<String, Player> players) {
        this.id = id;
        this.maxPlayers = maxPlayers;
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

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }
}
