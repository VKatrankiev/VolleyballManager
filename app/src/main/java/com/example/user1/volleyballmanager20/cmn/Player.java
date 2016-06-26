package com.example.user1.volleyballmanager20.cmn;

/**
 * Created by user1 on 26.6.2016 г..
 */
public class Player {
    private String name;
    private int height;
    private String position;

    private static final String SETTER = "S";
    private static final String OPPOSITE = "O";
    private static final String OUTSIDE_HITTER = "OH";
    private static final String LIBERO = "L";
    private static final String MIDDLE_BLOCKER = "MB";


    public Player(String name, int height, String position) {
        this.name = name;
        this.height = height;
        this.position = position;
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
        if(position.equals(SETTER) || position.equals(OPPOSITE) || position.equals(OUTSIDE_HITTER)
                || position.equals(LIBERO) || position.equals(MIDDLE_BLOCKER)){
            this.position=position;
        } else {
            this.position = OUTSIDE_HITTER;
        }
    }
}
