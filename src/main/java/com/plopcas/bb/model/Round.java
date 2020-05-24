package com.plopcas.bb.model;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private PlayerImage left;
    private PlayerImage right;
    private List<String> playersThatVotedLeft = new ArrayList<>();
    private List<String> playersThatVotedRight = new ArrayList<>();

    public Round(PlayerImage left, PlayerImage right) {
        this.left = left;
        this.right = right;
    }

    public PlayerImage getLeft() {
        return left;
    }

    public void setLeft(PlayerImage left) {
        this.left = left;
    }

    public PlayerImage getRight() {
        return right;
    }

    public void setRight(PlayerImage right) {
        this.right = right;
    }

    public List<String> getPlayersThatVotedLeft() {
        return playersThatVotedLeft;
    }

    public void setPlayersThatVotedLeft(List<String> playersThatVotedLeft) {
        this.playersThatVotedLeft = playersThatVotedLeft;
    }

    public List<String> getPlayersThatVotedRight() {
        return playersThatVotedRight;
    }

    public void setPlayersThatVotedRight(List<String> playersThatVotedRight) {
        this.playersThatVotedRight = playersThatVotedRight;
    }
}
