package com.example.user1.volleyballmanager20;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user1.volleyballmanager20.cmn.Config;
import com.example.user1.volleyballmanager20.cmn.FragmentOne;
import com.example.user1.volleyballmanager20.cmn.Player;
import com.example.user1.volleyballmanager20.cmn.Team;
import com.example.user1.volleyballmanager20.cmn.User;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtUserName;
    Button btnReg;
    EditText edtPass;
    Button btnSearch;
    private boolean flag = false;
    Button button;
    Button btnLogin;
    public static boolean isLogged = false;
    Animation animFadein;

    public static ArrayList<Player> players;
    public static User demoUser;

    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
    android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //startActivity(new Intent(this, TacticsActivity.class));
        
        players = new ArrayList<>();
        Firebase.setAndroidContext(this);
        final Firebase rootRef = new Firebase(Config.FIREBASE_PLAYERS_URL);

        if (!flag) {
            rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Log.e("playersk count", String.valueOf(dataSnapshot.getChildrenCount()));
                        Player player = postSnapshot.getValue(Player.class);
                        players.add(player);
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    System.out.println("The read failed: " + firebaseError.getMessage());
                }
            });
            flag = true;
        }

        edtUserName = (EditText) findViewById(R.id.edtxt_us);
        edtPass = (EditText) findViewById(R.id.edtxt_pass);
        btnReg = (Button) findViewById(R.id.btn_reg);
        btnSearch = (Button) findViewById(R.id.btn_search);
        btnLogin = (Button) findViewById(R.id.btn_login);
        View v = findViewById(R.id.main_layout);
        animFadein = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        v.startAnimation(animFadein);


        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }

        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            Boolean loggedIn = false;

            Firebase ref = new Firebase(Config.FIREBASE_URL);

            @Override
            public void onClick(View view) {
                demoUser = new User();
                ref.child("User").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        logUser(snapshot);
                    }

                    private void logUser(DataSnapshot snapshot) {
                        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                            User user1 = postSnapshot.getValue(User.class);
                            if (user1.getUserName().equals(String.valueOf(edtUserName.getText())) &&
                                    user1.getPassword().equals(String.valueOf(edtPass.getText()))) {
                                if (isNetworkAvailable() == true) {
                                    isLogged = true;
                                    demoUser = user1;
//                                if (demoUser.getTeam() == null || demoUser.getTeam().getAllPlayers() == null) {
//                                    demoUser.setTeam(new Team());
//                                    demoUser.getTeam().setAllPlayers(new ArrayList<Player>());
//                                }
                                    Intent i = new Intent(MainActivity.this, LoggedInActivity.class);
                                    Log.e("uu", user1.getUserName());
                                    loggedIn = true;
                                    startActivity(i);
                                    //Toast.makeText(MainActivity.this, "da", Toast.LENGTH_LONG).show();
                                    break;
                                }else{
                                    Toast.makeText(MainActivity.this,"No internet connection",Toast.LENGTH_LONG).show();
                                }
                            }
                            if (loggedIn == false) {
                                Toast.makeText(MainActivity.this, "Incorrect username or password", Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });

            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentOne fragment = new FragmentOne();
                getFragmentWithTag(fragment, null);
            }
        });
    }


    public void getFragmentWithTag(Fragment fragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.replace(R.id.layoutFragmentContainer, fragment, tag).commit();
    }
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
}
