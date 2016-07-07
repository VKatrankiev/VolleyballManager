package com.example.user1.volleyballmanager20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user1.volleyballmanager20.cmn.Config;
import com.example.user1.volleyballmanager20.cmn.Player;
import com.example.user1.volleyballmanager20.cmn.Team;
import com.example.user1.volleyballmanager20.cmn.TeamAdapter;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class LoggedInActivity extends AppCompatActivity {

    protected RecyclerView mRecyclerView;
    protected TeamAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;

    TextView txtTeamNameLoggedIn;
    String teamName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        txtTeamNameLoggedIn = (TextView) findViewById(R.id.txt_team_name_loggedin);
        User userLogged = new User();
//        Bundle b = getIntent().getExtras();
//        User userLogged = b.getParcelable("userTag");
        //User userLogged= getIntent().getParcelableExtra("userTag");
        //Team captain = getIntent().getParcelableExtra("captain");
        //Log.e("vladi",captain.getName());
        //Log.e("ivan123",userLogged.getUserName() + userLogged.getEmail() + userLogged.getfName() + userLogged.getPassword() + userLogged.getsName());
        //Log.e("slas",userLogged.getTeam().getName());

//        Player ivan = new Player();
//        ivan.setHeight(123);
//        ivan.setAge(23);
//        ivan.setName("ivan");
//        ivan.setPosition("O");
//        Player az = new Player();
//        az.setName("az");
//        az.setPosition("MB");
//        az.setAge(18);
//        az.setHeight(195);
//        ArrayList<Player> players = new ArrayList<>();
//        players.add(az);
//        players.add(ivan);
//        team = new Team();
//        team.setAllPlayers(players);
//        team.setName("VK QKI");
//        team.setStartingList(players);
//        team.setCaptain(az);

        if(!MainActivity.demouUser.equals(null)) {
            userLogged = MainActivity.demouUser;
        }
        teamName = userLogged.getTeam().getName();
        txtTeamNameLoggedIn.setText(teamName);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new TeamAdapter(userLogged.getTeam());
        mRecyclerView.setAdapter(mAdapter);

    }
}
