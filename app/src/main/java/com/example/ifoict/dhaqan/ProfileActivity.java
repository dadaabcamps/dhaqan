package com.example.ifoict.dhaqan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {
    Button buttonCreateStory;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        buttonCreateStory =(Button) findViewById(R.id.buttonCreateStory);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        if(currentUser == null){
            openLoginActivity();
        }

     }
     public void openCreateActivity (View view){
         Intent i = new Intent(ProfileActivity.this,CreateActivity.class);
         finish();
         startActivity(i);
     }

    public void openLoginActivity (){
        Intent i = new Intent(ProfileActivity.this,LoginActivity.class);
        finish();
        startActivity(i);
    }

    public void logOut(View v){
        mAuth.signOut();
        openLoginActivity();
    }
}
