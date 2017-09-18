package com.example.ifoict.dhaqan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfileActivity extends AppCompatActivity {
    Button buttonCreateStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        buttonCreateStory =(Button) findViewById(R.id.buttonCreateStory);

     }
     public void openCreateActivity (View view){
         Intent i = new Intent(ProfileActivity.this,CreateActivity.class);
         finish();
         startActivity(i);

     }
}
