package com.plopcas.bb.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String username;
    private List<Image> images = new ArrayList<>();

    private boolean host = false;

    public Player() {
    }

    public Player(String username) {
        this.username = username;
    }

    public Player(String username, boolean host) {
        this.username = username;
        this.host = host;
    }

    public Player(String username, List<Image> images) {
        this.username = username;
        this.images = images;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public boolean isHost() {
        return host;
    }

    public void setHost(boolean host) {
        this.host = host;
    }
}
