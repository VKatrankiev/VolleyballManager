package com.example.user1.volleyballmanager20.cmn;

<<<<<<< HEAD
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
=======
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by User on 6/29/2016.
 */
public class Team {

    private String name;
    private ArrayList<Player> allPlayers;
    private Player captain;
    private ArrayList<Player> startingList;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team() {
>>>>>>> refs/remotes/origin/master
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

<<<<<<< HEAD
    public Player[] getStartingList() {
        return startingList;
    }

    public void setStartingList(Player[] startingList) {
        this.startingList = startingList;
    }
}
=======
    public ArrayList<Player> getStartingList() {
        return startingList;
    }

    public void setStartingList(ArrayList<Player> startingList) {
        this.startingList = startingList;
    }

}

>>>>>>> refs/remotes/origin/master
