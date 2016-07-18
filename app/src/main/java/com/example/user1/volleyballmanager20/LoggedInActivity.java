package com.example.user1.volleyballmanager20;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
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

    public static Context context;
    Button btnAddPlayer;
    Button btnDrawScheme;

    protected RecyclerView mRecyclerView;
    protected TeamAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;

    TextView txtTeamNameLoggedIn;
    String teamName;

    public static User loggedUser;
    public static Team loggedTeam;
    private static boolean alreadyInit = false;

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
        if (loggedTeam == null) {
            Log.d("damn23", "damn");
            loggedTeam = new Team();
            loggedTeam.setTeamName(teamName);
            loggedTeam.setAllPlayers(new ArrayList<Player>());
        }
        if (loggedUser == null) {
            loggedUser = new User();
            if (MainActivity.demoUser != null) {

                loggedUser = MainActivity.demoUser;
            }
        }

        btnAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoggedInActivity.this, PlayerRegistrationActivity.class));
            }
        });


        teamName = loggedUser.getTeamName();
        txtTeamNameLoggedIn.setText(teamName);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        context = mRecyclerView.getContext();
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        Firebase.setAndroidContext(this);
        Firebase teamRef = new Firebase(Config.FIREBASE_TEAMS);
        if (!alreadyInit) {
            alreadyInit = true;
            teamRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.d("damn123", "damn2");
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        if (snapshot.child("teamName").exists() && snapshot.child("teamName") != null) {
                            if (snapshot.child("teamName").getValue().equals(teamName)) {
                                Log.d("damn123", "damn3");
                                loggedTeam = snapshot.getValue(Team.class);
                                if (loggedTeam.getAllPlayers() == null) {
                                    loggedTeam.setAllPlayers(new ArrayList<Player>());
                                }
                                break;
                            }
                        } else {
                            Firebase ref = snapshot.child("teamName").getRef();
                            ref.setValue(teamName);
                        }
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }

        mAdapter = new TeamAdapter(loggedTeam);
        mRecyclerView.setAdapter(mAdapter);
        for(Player player : loggedTeam.getAllPlayers()){
            if(player.isTitular()){
                TeamAdapter.titularCount++;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter = new TeamAdapter(loggedTeam);
        mRecyclerView.setAdapter(mAdapter);
        Log.d("player count", String.valueOf(loggedTeam.getAllPlayers().size()));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Firebase.setAndroidContext(this);
        final Firebase teamsRoot = new Firebase(Config.FIREBASE_TEAMS);
        teamsRoot.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Firebase targetRef;
                boolean noTeam = true;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (snapshot.child("teamName").getValue().equals(loggedTeam.getTeamName())) {
                        targetRef = snapshot.getRef();
                        targetRef.setValue(loggedTeam);
                        noTeam = false;
                        break;
                    }
                }
                if (noTeam) {
                    teamsRoot.child(teamName).setValue(loggedTeam);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
