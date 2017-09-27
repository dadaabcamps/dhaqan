package com.example.ifoict.dhaqan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

public class EditActivity extends AppCompatActivity {
    EditText editTextEditTitle, editTextEditContent, editTextEditAuthor;
    String recievedTitle, recievedContent, recievedAuthor, recievedCategory;
    Spinner spinnerEditCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        editTextEditTitle = (EditText) findViewById(R.id.editTextEditTitle);
        editTextEditContent = (EditText)findViewById(R.id.editTextEditContent);
        editTextEditAuthor = (EditText) findViewById(R.id.editTextEditAuthor);
        spinnerEditCategory = (Spinner) findViewById(R.id.spinnerEditCategory);

        Intent i = getIntent();
        recievedTitle = i.getStringExtra("titleKey");
        recievedContent = i.getStringExtra("contentKey");
        recievedAuthor = i.getStringExtra("authorKey");
        recievedCategory = i.getStringExtra("categoryKey");

        editTextEditTitle.setText(recievedTitle);
        editTextEditContent.setText(recievedContent);
        editTextEditAuthor.setText(recievedAuthor);

    }
}
