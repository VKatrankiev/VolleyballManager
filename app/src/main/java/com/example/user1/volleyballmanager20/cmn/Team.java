package com.example.user1.volleyballmanager20.cmn;

import java.util.ArrayList;

/**
 * Created by user1 on 29.6.2016 г..
 */
public class Team {

    private String name;
    private ArrayList<Player> allPlayers;
    private Player captain;
    private ArrayList<Player> startingList;

    public Team(ArrayList<Player> allPlayers, Player captain, ArrayList<Player> startingList, String name) {
        this.allPlayers = allPlayers;
        this.captain = captain;
        this.startingList = startingList;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team() {
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


    public ArrayList<Player> getStartingList() {
        return startingList;
    }

    public void setStartingList(ArrayList<Player> startingList) {
        this.startingList = startingList;
    }

    public void addPlayer(Player player) {
        if (this.allPlayers == null) {
            this.allPlayers = new ArrayList<>();
        }
        this.allPlayers.add(player);
    }

}
