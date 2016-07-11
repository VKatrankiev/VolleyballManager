package com.example.user1.volleyballmanager20;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user1.volleyballmanager20.cmn.Config;
import com.example.user1.volleyballmanager20.cmn.FragmentOne;
import com.example.user1.volleyballmanager20.cmn.Player;
import com.example.user1.volleyballmanager20.cmn.User;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class PlayerRegistrationActivity extends AppCompatActivity {

    public static ArrayList<Player> players;
    EditText edtName;
    EditText edtHeight;
    EditText edtPosition;
    EditText edtAge;
    Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_registration);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        players = new ArrayList<>();
        edtName = (EditText) findViewById(R.id.player_name_register);
        edtHeight = (EditText) findViewById(R.id.player_height_register);
        edtPosition = (EditText) findViewById(R.id.player_position_register);
        edtAge = (EditText) findViewById(R.id.player_age_register);
        btnRegister = (Button) findViewById(R.id.btn_player_register);

        final Firebase rootRef = new Firebase(Config.FIREBASE_PLAYERS_URL);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String name = String.valueOf(edtName.getText());
                String position = String.valueOf(edtPosition.getText());
                int height = Integer.parseInt(edtHeight.getText().toString());
                int age = Integer.parseInt(edtAge.getText().toString());
                final Player player = new Player();
                player.setHeight(height);
                player.setName(name);
                player.setAge(age);
                if (player.isPositionCorrect(position)) {
                    player.setPosition(position);
                    rootRef.child("User").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                User tempUser = snapshot.getValue(User.class);
                                if(tempUser.getUserName().equals(MainActivity.demoUser.getUserName())){
                                    tempUser.getTeam().addPlayer(player);
                                    MainActivity.demoUser.getTeam().addPlayer(player);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });
                    rootRef.push().setValue(player);
                    LoggedInActivity.loggedTeam.addPlayer(player);
                    MainActivity.demoUser.getTeam().addPlayer(player);
                } else {
                    Toast.makeText(PlayerRegistrationActivity.this, "Incorrect position!", Toast.LENGTH_LONG);
                }
                startActivity(new Intent(PlayerRegistrationActivity.this, LoggedInActivity.class));
            }
        });

        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Player player = postSnapshot.getValue(Player.class);
                    players.add(player);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    //duplicated method...
    public void getFragmentWithTag(Fragment fragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.replace(R.id.layoutFragmentContainer2, fragment, tag).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search: {
                FragmentOne fragment = new FragmentOne();
                getFragmentWithTag(fragment, null);
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }
}
