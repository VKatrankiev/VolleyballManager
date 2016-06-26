package com.example.user1.volleyballmanager20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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


        edtFName = (EditText) findViewById(R.id.edt_fname);
        edtSName = (EditText) findViewById(R.id.edt_sname);
        edtRegPass = (EditText) findViewById(R.id.edt_regpass);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtTeam = (EditText) findViewById(R.id.edt_team);
        btnRegister = (Button) findViewById(R.id.btn_reg);
        btnCancel = (Button) findViewById(R.id.btn_cancel);

    }
}
