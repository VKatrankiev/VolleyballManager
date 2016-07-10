package com.example.user1.volleyballmanager20.cmn;

import java.util.ArrayList;

/**
 * Created by user1 on 29.6.2016 г..
 */
public class Team {

    private ArrayList<Player> allPlayers;

    public Team(ArrayList<Player> allPlayers) {
        this.allPlayers = allPlayers;
    }

    public Team() {
    }

    public ArrayList<Player> getAllPlayers() {
        return allPlayers;
    }

    public void setAllPlayers(ArrayList<Player> allPlayers) {
        this.allPlayers = allPlayers;
    }

    public void addPlayer(Player player) {
        if (this.allPlayers == null) {
            this.allPlayers = new ArrayList<>();
        }
        this.allPlayers.add(player);
    }

}
