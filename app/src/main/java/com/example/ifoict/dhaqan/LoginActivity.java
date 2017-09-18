package com.example.ifoict.dhaqan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {
    Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buttonLogin=(Button) findViewById(R.id.buttonLogin);
    }
    public void openRegistrationActivity(View view){
        Intent i =new Intent(LoginActivity.this,RegistrationActivity.class);
        finish();
        startActivity(i);
    }
    public void openProfileActivity(View view){
        Intent i = new Intent(LoginActivity.this, ProfileActivity.class);
        finish();
        startActivity(i);
    }
}
