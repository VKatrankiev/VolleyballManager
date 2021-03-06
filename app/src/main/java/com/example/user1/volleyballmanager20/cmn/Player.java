package com.example.user1.volleyballmanager20.cmn;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user1 on 26.6.2016 г..
 */
public class Player {
    private String name;
    private int height;
    private String position;
    private int age;
    private boolean isTaken;
    private boolean isTitular;
    private boolean isCaptain;

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    public boolean isCaptain() {
        return isCaptain;
    }

    public void setCaptain(boolean captain) {
        isCaptain = captain;
    }

    public boolean isTitular() {
        return isTitular;
    }

    public void setTitular(boolean titular) {
        isTitular = titular;
    }

    public Player (){
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        if(position.equals(Config.SETTER) || position.equals(Config.OPPOSITE) || position.equals(Config.OUTSIDE_HITTER)
                || position.equals(Config.LIBERO) || position.equals(Config.MIDDLE_BLOCKER)){
            this.position=position;
        } else {
            this.position = Config.OUTSIDE_HITTER;
        }
    }
    public boolean isPositionCorrect(String position){
        if(position.equals(Config.SETTER) || position.equals(Config.OPPOSITE) || position.equals(Config.OUTSIDE_HITTER)
                || position.equals(Config.LIBERO) || position.equals(Config.MIDDLE_BLOCKER)){
            return true;
        }
        return false;
    }

}
