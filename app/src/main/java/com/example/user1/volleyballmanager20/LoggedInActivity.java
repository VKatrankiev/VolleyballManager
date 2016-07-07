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

import com.example.user1.volleyballmanager20.cmn.Player;
import com.example.user1.volleyballmanager20.cmn.Team;
import com.example.user1.volleyballmanager20.cmn.TeamAdapter;

import java.util.ArrayList;

public class LoggedInActivity extends AppCompatActivity {

    Button btnAddPlayer;
    Button btnDrawScheme;

    protected RecyclerView mRecyclerView;
    protected TeamAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;

    static Team loggedTeam;

    TextView txtTeamNameLoggedIn;
    String teamName;
    User userLogged;

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

        userLogged = new User();
        if (!MainActivity.demoUser.equals(null)) {
            userLogged = MainActivity.demoUser;
        }
        teamName = userLogged.getTeam().getName();
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
            loggedTeam.setStartingList(new ArrayList<Player>());
            loggedTeam.setCaptain(new Player());
            loggedTeam.setAllPlayers(new ArrayList<Player>());
            loggedTeam.setName(teamName);
            Log.e("hello", "nope im here");
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (MainActivity.demoUser != null) {
            userLogged = MainActivity.demoUser;
        }
        teamName = userLogged.getTeam().getName();
        txtTeamNameLoggedIn.setText(teamName);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        if (userLogged.getTeam().getAllPlayers() != null) {
            loggedTeam = userLogged.getTeam();
            mAdapter = new TeamAdapter(userLogged.getTeam());
            mRecyclerView.setAdapter(mAdapter);
            Log.e("cmon", "in the onresume if");
        } else {
            loggedTeam = new Team();
            Log.e("cmon", "in the onresume else");
        }
    }
}
