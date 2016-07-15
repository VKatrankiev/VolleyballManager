package com.example.user1.volleyballmanager20.cmn;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

import com.example.user1.volleyballmanager20.cmn.Team;

import java.io.Serializable;

/**
 * Created by User on 6/28/2016.
 */
public class User {
    private String userName;
    private String fName;
    private String sName;
    private String email;
    private String password;
    private String teamName;



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public User(){};

    public User(String userName, Team team, String password, String email, String sName, String fName,String teamName) {
        this.userName = userName;
        this.email = email;
        this.sName = sName;
        this.fName = fName;
        this.password = password;
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
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
}
