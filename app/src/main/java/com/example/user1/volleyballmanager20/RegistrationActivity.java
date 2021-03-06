package com.example.user1.volleyballmanager20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user1.volleyballmanager20.cmn.Config;
import com.example.user1.volleyballmanager20.cmn.Player;
import com.example.user1.volleyballmanager20.cmn.Team;
import com.example.user1.volleyballmanager20.cmn.User;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class RegistrationActivity extends AppCompatActivity {

    EditText edtRegistrationUsername;
    EditText edtFName;
    EditText edtSName;
    EditText edtRegPass;
    EditText edtEmail;
    EditText edtTeam;
    EditText edtConfPass;
    Button btnRegister;
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Firebase.setAndroidContext(this);

        edtRegistrationUsername = (EditText) findViewById(R.id.edt_register_username);
        edtFName = (EditText) findViewById(R.id.edt_fname);
        edtSName = (EditText) findViewById(R.id.edt_sname);
        edtRegPass = (EditText) findViewById(R.id.edt_regpass);
        edtConfPass = (EditText) findViewById(R.id.edt_confPass);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtTeam = (EditText) findViewById(R.id.edt_team);
        btnRegister = (Button) findViewById(R.id.btn_register);
        btnCancel = (Button) findViewById(R.id.btn_cancel);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {

            Firebase ref = new Firebase(Config.FIREBASE_URL);
            User user = new User();
            Boolean hasUserName = false;
            Boolean hasEmail = false;
            Boolean hasTeamName = false;
            Boolean flag = false;

            @Override
            public void onClick(View view) {

                final String userName = edtRegistrationUsername.getText().toString().trim();
                String firstName = edtFName.getText().toString().trim();
                String pass = edtRegPass.getText().toString().trim();
                String confPass = edtConfPass.getText().toString().trim();
                String sirName = edtSName.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                final String teamName = edtTeam.getText().toString().trim();


                user.setTeamName(teamName);
                user.setUserName(userName);
                user.setfName(firstName);
                user.setPassword(pass);
                user.setsName(sirName);
                user.setEmail(email, RegistrationActivity.this);

//                ref.child("User").addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        if (dataIsCorrect()) {
//                            ref.child("User").push().setValue(user);
//                            Team team = new Team();
//                            team.setAllPlayers(new ArrayList<Player>());
//                            team.setTeamName(user.getTeamName());
//                            ref.child("Teams").push().setValue(team);
//                            Log.d("heeeey", "ss");
//                            startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
//                        } else {
//                            Toast.makeText(RegistrationActivity.this, "Incorrect data", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    private boolean dataIsCorrect() {
//                        if(userName.equals("") || teamName.equals("")){
//                            return false;
//                        }
//                        return true;
//                    }
//
//                    @Override
//                    public void onCancelled(FirebaseError firebaseError) {
//
//                    }
//                });

                ref.child("User").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.child("User").hasChildren()) {
                            for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                if (edtRegistrationUsername.toString().length() == 0) {
                                    Toast.makeText(RegistrationActivity.this, "You must enter username!", Toast.LENGTH_SHORT).show();
                                    flag = true;
                                    break;
                                }
                                if (edtFName.getText().toString().length() == 0) {
                                    Toast.makeText(RegistrationActivity.this, "You must enter first name!", Toast.LENGTH_SHORT).show();
                                    flag = true;
                                    break;
                                }
                                if (edtSName.getText().toString().length() == 0) {
                                    Toast.makeText(RegistrationActivity.this, "You must enter sir name!", Toast.LENGTH_SHORT).show();
                                    flag = true;
                                    break;
                                }
                                if (edtRegPass.getText().toString().length() == 0) {
                                    Toast.makeText(RegistrationActivity.this, "You must enter password!", Toast.LENGTH_SHORT).show();
                                    flag = true;
                                    break;
                                }
                                if (!edtRegPass.getText().toString().equals(edtConfPass.getText().toString())) {
                                    Toast.makeText(RegistrationActivity.this, "Your passwords does not match!", Toast.LENGTH_SHORT).show();
                                    flag = true;
                                    break;
                                }
                                if (edtTeam.toString().isEmpty()) {
                                    Toast.makeText(RegistrationActivity.this, "You must enter team name!", Toast.LENGTH_SHORT).show();
                                    flag = true;
                                    break;
                                }
                                if (!postSnapshot.hasChildren()) {
                                    Log.e("blabla23", "ivan");
                                    ref.child("User").push().setValue(user);
                                    Team tempTeam = new Team();
                                    tempTeam.setTeamName(user.getTeamName());
                                    tempTeam.setAllPlayers(new ArrayList<Player>());
                                    ref.child("Teams").push().setValue(tempTeam);
                                    break;
                                } else {
                                    //Getting the data from snapshot
                                    User user1 = postSnapshot.getValue(User.class);
                                    Log.e("user1", user1.getUserName());
                                    Log.e("user", user.getUserName());

                                    if (user1.getUserName().equals(user.getUserName())) {
                                        Log.e("spas", "2");
                                        Toast.makeText(RegistrationActivity.this, "Username already taken", Toast.LENGTH_LONG).show();
                                        hasUserName = true;
                                        break;
                                    }
                                    if (user1.getEmail().equals(user.getEmail())) {
                                        Toast.makeText(RegistrationActivity.this, "Email already taken!", Toast.LENGTH_LONG).show();
                                        hasEmail = true;
                                        break;
                                    }
                                    if (user1.getTeamName().equals(user.getTeamName())) {
                                        Toast.makeText(RegistrationActivity.this, "Team name already taken!", Toast.LENGTH_LONG).show();
                                        hasTeamName = true;
                                        break;

                                    }
                                }
                                if (hasUserName == false && hasEmail == false && hasTeamName == false && flag == false) {
                                    ref.child("User").push().setValue(user);
                                    Team tempTeam = new Team();
                                    tempTeam.setTeamName(user.getTeamName());
                                    tempTeam.setAllPlayers(new ArrayList<Player>());
//                                    ref.child("Teams").child(user.getTeamName()).child("teamName").setValue(user.getTeamName());
//                                    ref.child("Teams").child(user.getTeamName()).child("allPlayers").setValue(new ArrayList<Player>());
                                    ref.child("Teams").push().setValue(tempTeam);
                                    Log.d("are be", "e te");
                                }

                            }

                        } else {
                            ref.child("User").push().setValue(user);
                            Team tempTeam = new Team();
                            tempTeam.setTeamName(user.getTeamName());
                            tempTeam.setAllPlayers(new ArrayList<Player>());
                            ref.child("Teams").push().setValue(tempTeam);
                            Log.d("ssssss","ss");
                        }
                        Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                        System.out.println("The read failed: " + firebaseError.getMessage());
                    }

                });
            }


        });
    }
}

