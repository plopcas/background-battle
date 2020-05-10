package com.plopcas.bb.model;

/**
 * Bean to capture the current user session.
 */
public class UserSession {
    private String username;
    private String tournamentId;

    public UserSession() {
    }

    public UserSession(String username, String tournamentId) {
        this.username = username;
        this.tournamentId = tournamentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(String tournamentId) {
        this.tournamentId = tournamentId;
    }
}
