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

public class EditActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText editTextEditTitle, editTextEditContent, editTextEditAuthor;
    String recievedTitle, recievedContent, recievedAuthor, recievedCategory, category, title, author, content, id;
    Spinner spinnerEditCategory;
    ArrayAdapter<CharSequence> adapterCategories;

    FirebaseDatabase db;
    DatabaseReference dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        db = FirebaseDatabase.getInstance();
        dbRef = db.getReference("Articles");

        editTextEditTitle = (EditText) findViewById(R.id.editTextEditTitle);
        editTextEditContent = (EditText)findViewById(R.id.editTextEditContent);
        editTextEditAuthor = (EditText) findViewById(R.id.editTextEditAuthor);
        spinnerEditCategory = (Spinner) findViewById(R.id.spinnerEditCategory);

        adapterCategories = ArrayAdapter.createFromResource(EditActivity.this,R.array.categories, android.R.layout.simple_spinner_item);
        adapterCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEditCategory.setAdapter(adapterCategories);

        spinnerEditCategory.setOnItemSelectedListener(this);
        Intent i = getIntent();
        id = i.getStringExtra("idKey");
        recievedTitle = i.getStringExtra("titleKey");
        recievedContent = i.getStringExtra("contentKey");
        recievedAuthor = i.getStringExtra("authorKey");
        category = i.getStringExtra("categoryKey");

        int categoryPosition = adapterCategories.getPosition(category);
        spinnerEditCategory.setSelection(categoryPosition);

        editTextEditTitle.setText(recievedTitle);
        editTextEditContent.setText(recievedContent);
        editTextEditAuthor.setText(recievedAuthor);

    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        category = parent.getItemAtPosition(pos).toString();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void editArticle (View v){
        title = editTextEditTitle.getText().toString().trim();
        author = editTextEditAuthor.getText().toString().trim();
        content = editTextEditContent.getText().toString().trim();

        if (title.equals("") || author.equals("") || content.equals("")){
            Toast.makeText(EditActivity.this, "Fill in all the fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Article article = new Article(id, title, author, content, category);

        dbRef.child(id).setValue(article);
        Toast.makeText(EditActivity.this, "Article created", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(EditActivity.this,ProfileActivity.class);
//       Intent i = new Intent(CreateActivity.this,DetailActivity.class);
//        i.putExtra("titleKey", title);
//        i.putExtra("contentKey", content);
//        i.putExtra("authorKey", author);
//        i.putExtra("idKey", id);
//        i.putExtra("categoryKey", category);
        startActivity(i);

    }
}
