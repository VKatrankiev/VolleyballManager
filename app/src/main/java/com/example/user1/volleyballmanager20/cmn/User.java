package com.example.user1.volleyballmanager20.cmn;

import android.content.Context;
import android.widget.Toast;

import com.example.user1.volleyballmanager20.RegistrationActivity;

/**
 * Created by User on 6/27/2016.
 */
public class User {
    private String fName;
    private String sName;
    private String email;
    private String password;
    private String teamName;

    public User() {}

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
//        if(password.isEmpty()) {
//            Toast.makeText(context,"You must enter password", Toast.LENGTH_SHORT).show();
//        }
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email,Context context) {
        if(isValidEmail(email)){
            this.email = email;
        }else{
            Toast.makeText(context,"You must enter a valid email",Toast.LENGTH_SHORT).show();
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
