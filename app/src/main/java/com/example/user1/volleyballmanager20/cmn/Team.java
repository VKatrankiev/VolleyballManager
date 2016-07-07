package com.example.user1.volleyballmanager20.cmn;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by User on 6/29/2016.
 */
public class Team implements Parcelable {

    private String name;
    private ArrayList<Player> allPlayers;
    private Player captain;
    private ArrayList<Player> startingList;

    protected Team(Parcel in) {
        name = in.readString();
        allPlayers = in.createTypedArrayList(Player.CREATOR);
        captain = in.readParcelable(Player.class.getClassLoader());
        startingList = in.createTypedArrayList(Player.CREATOR);
    }

    public static final Creator<Team> CREATOR = new Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel in) {
            return new Team(in);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(getName());
        parcel.writeParcelableArray((Parcelable[]) getAllPlayers().toArray(), i);
        parcel.writeParcelable(getCaptain(), i);
        parcel.writeParcelableArray((Parcelable[]) getStartingList().toArray(), i);
    }
}

