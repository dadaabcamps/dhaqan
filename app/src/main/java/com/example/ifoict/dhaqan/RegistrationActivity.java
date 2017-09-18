package com.example.ifoict.dhaqan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RegistrationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    public void openLoginActivity (View view){
        Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
        finish();
        startActivity(i);

    }
    public void openProfileActivity (View view){
        Intent i = new Intent(RegistrationActivity.this, ProfileActivity.class);
        finish();
        startActivity(i);

    }
}
