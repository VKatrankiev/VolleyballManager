package com.example.user1.volleyballmanager20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.user1.volleyballmanager20.cmn.Config;
import com.example.user1.volleyballmanager20.cmn.Player;
import com.example.user1.volleyballmanager20.cmn.Team;
import com.example.user1.volleyballmanager20.Adapters.TeamAdapter;
import com.example.user1.volleyballmanager20.cmn.User;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class LoggedInActivity extends AppCompatActivity {

    Button btnAddPlayer;
    Button btnDrawScheme;

    protected RecyclerView mRecyclerView;
    protected TeamAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;

    TextView txtTeamNameLoggedIn;
    String teamName;

    public static User userLogged;
    public static Team loggedTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        txtTeamNameLoggedIn = (TextView) findViewById(R.id.txt_team_name_loggedin);
        btnAddPlayer = (Button) findViewById(R.id.btn_add_player);
        btnDrawScheme = (Button) findViewById(R.id.btn_draw_scheme);
        btnDrawScheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoggedInActivity.this, TacticsActivity.class));
            }
        });

        btnAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoggedInActivity.this, PlayerRegistrationActivity.class));
            }
        });
        if (userLogged == null) {
            userLogged = new User();
            if (MainActivity.demoUser != null) {
                userLogged = MainActivity.demoUser;
            }
        }

        teamName = userLogged.getTeamName();
        txtTeamNameLoggedIn.setText(teamName);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        if (!(userLogged.getTeam().getAllPlayers() == null)) {
            Log.e("hello", "im here");
            loggedTeam = userLogged.getTeam();
            mAdapter = new TeamAdapter(userLogged.getTeam());
            mRecyclerView.setAdapter(mAdapter);
        } else {
            loggedTeam = new Team();
            loggedTeam.setAllPlayers(new ArrayList<Player>());
            Log.e("hello", "nope im here");
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        teamName = userLogged.getTeamName();
        txtTeamNameLoggedIn.setText(teamName);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        userLogged.setTeam(loggedTeam);
        mAdapter = new TeamAdapter(userLogged.getTeam());
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void onPause() {
        super.onPause();

        if (!userLogged.getTeam().getAllPlayers().equals(MainActivity.demoUser.getTeam().getAllPlayers())) {
            final String[] key = new String[1];
            Firebase ref = new Firebase(Config.FIREBASE_USERS_URL);

            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot shot : dataSnapshot.getChildren()) {
                        User tempUser = shot.getValue(User.class);
                        if (tempUser.getUserName().equals(MainActivity.demoUser.getUserName())) {
                            key[0] = shot.getKey();
                            break;
                        }
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
            ref.child(key[0]).setValue(userLogged);
        }
    }
}
