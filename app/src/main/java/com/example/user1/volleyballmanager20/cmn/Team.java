package com.example.user1.volleyballmanager20.cmn;

import java.util.ArrayList;

/**
 * Created by user1 on 29.6.2016 г..
 */
public class Team {

    private ArrayList<Player> allPlayers;
    private Player captain;
    private Player[] startingList;

    public Team(ArrayList<Player> allPlayers, Player captain, Player[] startingList) {
        this.allPlayers = allPlayers;
        this.captain = captain;
        this.startingList = startingList;
    }

    public ArrayList<Player> getAllPlayers() {
        return allPlayers;
    }

    public void setAllPlayers(ArrayList<Player> allPlayers) {
        this.allPlayers = allPlayers;
    }

    public Player getCaptain() {
        return captain;
    }

    public void setCaptain(Player captain) {
        this.captain = captain;
    }

    public Player[] getStartingList() {
        return startingList;
    }

    public void setStartingList(Player[] startingList) {
        this.startingList = startingList;
    }
}
