package com.example.ifoict.dhaqan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinnerCreateCategory;
    ArrayAdapter<CharSequence> adapterCategories;
    EditText editTextTitle, editTextAuthor, editTextContent;
    String title,author,content, category;
    FirebaseDatabase db;
    DatabaseReference dbRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        spinnerCreateCategory = (Spinner) findViewById(R.id.spinnerEditCategory);
        adapterCategories = ArrayAdapter.createFromResource(CreateActivity.this,R.array.categories, android.R.layout.simple_spinner_item);
        adapterCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCreateCategory.setAdapter(adapterCategories);

        spinnerCreateCategory.setOnItemSelectedListener(this);

        editTextTitle = (EditText) findViewById(R.id.editTextEditTitle);
        editTextAuthor = (EditText) findViewById(R.id.editTextEditAuthor);
        editTextContent = (EditText) findViewById(R.id.editTextEditContent);
        db = FirebaseDatabase.getInstance();
        dbRef = db.getReference("Articles");

    }
    public void createArticle (View v){
        title = editTextTitle.getText().toString().trim();
        author = editTextAuthor.getText().toString().trim();
        content = editTextContent.getText().toString().trim();

        if (title.equals("") || author.equals("") || content.equals("")){
            Toast.makeText(CreateActivity.this, "Fill in all the fields", Toast.LENGTH_SHORT).show();
            return;
        }
        String id = dbRef.push().getKey();

        Article article = new Article(id, title, author, content, category);

        dbRef.child(id).setValue(article);
        Toast.makeText(CreateActivity.this, "Article created", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(CreateActivity.this,ProfileActivity.class);
       Intent i = new Intent(CreateActivity.this,DetailActivity.class);
//        i.putExtra("titleKey", title);
//        i.putExtra("contentKey", content);
//        i.putExtra("authorKey", author);
//        i.putExtra("idKey", id);
//        i.putExtra("categoryKey", category);
//        startActivity(i);

    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        category = parent.getItemAtPosition(pos).toString();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}