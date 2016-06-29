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
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class RegistrationActivity extends AppCompatActivity {

    EditText edtRegistrationUsername;
    EditText edtFName;
    EditText edtSName;
    EditText edtRegPass;
    EditText edtEmail;
    EditText edtTeam;
    Button btnRegister;
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Firebase.setAndroidContext(this);


        edtRegistrationUsername = (EditText) findViewById(R.id.edt_register_username);
        edtFName = (EditText) findViewById(R.id.edt_fname);
        edtSName = (EditText) findViewById(R.id.edt_sname);
        edtRegPass = (EditText) findViewById(R.id.edt_regpass);
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


        //btnRegister = (Button) loginDialog.findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            Firebase ref = new Firebase(Config.FIREBASE_URL);
            User user = new User();
            Boolean hasUserName = false;
            Boolean hasEmail = false;
            Boolean hasTeamName = false;

            @Override
            public void onClick(View view) {

                String userName = edtRegistrationUsername.getText().toString().trim();
                String firstName = edtFName.getText().toString().trim();
                String pass = edtRegPass.getText().toString().trim();
                String sirName = edtSName.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String teamName = edtTeam.getText().toString().trim();

                user.setUserName(userName, RegistrationActivity.this);
                user.setfName(firstName, RegistrationActivity.this);
                user.setPassword(pass, RegistrationActivity.this);
                user.setsName(sirName, RegistrationActivity.this);
                user.setEmail(email, RegistrationActivity.this);
                user.setTeamName(teamName, RegistrationActivity.this);

                ref.child("User").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                            if (!postSnapshot.hasChildren()) {
                                Log.e("blabla23", "ivan");
                                ref.child("User").push().setValue(user);
                                break;
                            } else {
                                //Getting the data from snapshot
                                User user1 = postSnapshot.getValue(User.class);
                                Log.e("user1", user1.getUserName());
                                Log.e("user", user.getUserName());

                                if (user1.getUserName().equals(user.getUserName())) {
                                    Log.e("spas","2");
                                    Toast.makeText(RegistrationActivity.this,"Username already taken",Toast.LENGTH_LONG).show();
                                    hasUserName = true;
                                    break;
                                }
                                if(user1.getEmail().equals(user.getEmail())){
                                    Toast.makeText(RegistrationActivity.this,"Email already taken!",Toast.LENGTH_LONG).show();
                                    hasEmail = true;
                                    break;
                                }
<<<<<<< HEAD
=======
                                if(user1.getTeamName().equals(user.getTeamName())){
                                    Toast.makeText(RegistrationActivity.this,"Team name already taken!",Toast.LENGTH_LONG).show();
                                    hasTeamName = true;
                                    break;
                                }
>>>>>>> refs/remotes/origin/master
                            }
                        }
                        if (hasUserName == false && hasEmail == false && hasTeamName == false) {
                            ref.child("User").push().setValue(user);
                            Intent intent = new Intent(RegistrationActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
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

