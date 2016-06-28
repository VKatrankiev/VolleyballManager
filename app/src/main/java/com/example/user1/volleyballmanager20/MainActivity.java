package com.example.user1.volleyballmanager20;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.user1.volleyballmanager20.cmn.Config;
import com.example.user1.volleyballmanager20.cmn.FragmentOne;
import com.example.user1.volleyballmanager20.cmn.Player;
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
    public static ArrayList<Player> players;

    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
    android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        edtUserName = (EditText) findViewById(R.id.edtxt_us);
        edtPass = (EditText) findViewById(R.id.edtxt_pass);
        btnReg = (Button) findViewById(R.id.btn_reg);
        btnSearch = (Button) findViewById(R.id.btn_search);

        Firebase.setAndroidContext(this);
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Firebase rootRef = new Firebase(Config.FIREBASE_URL);
                Player player = new Player();
                player.setHeight(159);
                player.setName("Kolio");
                player.setPosition(Config.MIDDLE_BLOCKER);
                rootRef.child("Player").setValue(player);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentOne fragment = new FragmentOne();
                getFragmentWithTag(fragment,null);
            }
        });
    }


    public void getFragmentWithTag(Fragment fragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.replace(R.id.layoutFragmentContainer, fragment, tag).commit();
    }
}
