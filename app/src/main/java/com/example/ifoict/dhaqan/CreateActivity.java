package com.example.ifoict.dhaqan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class CreateActivity extends AppCompatActivity {
    Button buttonCreateStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        buttonCreateStory= (Button) findViewById(R.id.buttonCreateStory);

    }
}
