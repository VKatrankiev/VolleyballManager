package com.example.user1.volleyballmanager20;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.Toast;

import com.example.user1.volleyballmanager20.cmn.User;
import com.firebase.client.Firebase;


public class RegistrationActivity extends AppCompatActivity {

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

        Firebase.setAndroidContext(this);


        edtFName = (EditText) findViewById(R.id.edt_fname);
        edtSName = (EditText) findViewById(R.id.edt_sname);
        edtRegPass = (EditText) findViewById(R.id.edt_regpass);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtTeam = (EditText) findViewById(R.id.edt_team);
        btnRegister = (Button) findViewById(R.id.btn_reg);
        btnCancel = (Button) findViewById(R.id.btn_cancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


        //btnRegister = (Button) loginDialog.findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Firebase ref = new Firebase(Config.FIREBASE_URL);

                String name = edtFName.getText().toString().trim();
                String pass = edtRegPass.getText().toString().trim();

                User user = new User();

                user.setfName(name);
                user.setPassword(pass);

                ref.child("User").setValue(user);
            }
        });



    }


}
