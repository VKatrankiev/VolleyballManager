package com.example.user1.volleyballmanager20;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

import com.example.user1.volleyballmanager20.cmn.Team;

import java.io.Serializable;

/**
 * Created by User on 6/28/2016.
 */
public class User implements Parcelable{
    private String userName;
    private String fName;
    private String sName;
    private String email;
    private String password;
    private Team team;

    public User(Parcel in) {
        this.userName = in.readString();
        this.fName = in.readString();
        this.sName =  in.readString();
        this.email = in.readString();
        this.password =   in.readString();;
        this.team = in.readParcelable(Team.class.getClassLoader());
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public User(){};

    public User(String userName, Team team, String password, String email, String sName, String fName) {
        this.userName = userName;
        this.email = email;
        this.sName = sName;
        this.fName = fName;
        this.password = password;
        this.team = team;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }


    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email,Context context) {
        if(isValidEmail(email)){
            this.email = email;
        }else{
            Toast.makeText(context,"You must enter a valid email!",Toast.LENGTH_SHORT).show();
        }
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
//        parcel.writeList(team.getAllPlayers());
//        parcel.writeList(team.getStartingList());
//        parcel.writeValue(team.getCaptain());
//        parcel.writeValue(team.getName());
        parcel.writeString(this.userName);
        parcel.writeString(this.fName);
        parcel.writeString(this.sName);
        parcel.writeString(this.email);
        parcel.writeString(this.password);
        parcel.writeParcelable(team, i);
    }
    public static final Parcelable.Creator<User> CREATOR= new Parcelable.Creator<User>() {

        @Override
        public User createFromParcel(Parcel source) {
// TODO Auto-generated method stub
            return new User(source);  //using parcelable constructor
        }

        @Override
        public User[] newArray(int size) {
// TODO Auto-generated method stub
            return new User[size];
        }
    };
}
