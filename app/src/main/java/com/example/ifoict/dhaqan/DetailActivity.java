package com.example.ifoict.dhaqan;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailActivity extends AppCompatActivity {

    TextView textViewTittle;
    TextView textViewContent;
    TextView  textViewAuthor;
    String recievedTittle;
    String recievedContent;
    String recievedAuthor;
    String receivedID;
    FirebaseDatabase db;
    DatabaseReference dbArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

// Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

// Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        db = FirebaseDatabase.getInstance();
        dbArticle = db.getReference("Articles");



        textViewTittle=(TextView) findViewById(R.id.textViewTitle);
        textViewContent=(TextView) findViewById(R.id.textViewContent);
        textViewAuthor=(TextView) findViewById(R.id.textViewAuthor);


        Intent i= getIntent();
        recievedTittle = i.getStringExtra("titleKey");
        recievedContent = i.getStringExtra("contentKey");
        recievedAuthor = i.getStringExtra("authorKey");
        receivedID = i.getStringExtra("idKey");

        textViewTittle.setText(recievedTittle);
        textViewContent.setText(recievedContent);
        textViewAuthor.setText(recievedAuthor);

        getSupportActionBar().setTitle(recievedTittle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.crud_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
//                Intent agencyIntent = new Intent(ViewAgencyActivity.this, EditAgencyActivity.class);
//
//                agencyIntent.putExtra("idKey", id);
//                agencyIntent.putExtra("nameKey", name);
//                agencyIntent.putExtra("codeKey", code);
//                agencyIntent.putExtra("imageKey", imageUrl);
//
//                startActivity(agencyIntent);
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_delete:

                try{
                    dbArticle.child(receivedID).removeValue();
//                    progress.dismiss();
                    Toast.makeText(DetailActivity.this, "Article deleted", Toast.LENGTH_SHORT).show();
                }
                catch (DatabaseException e){
                    Toast.makeText(DetailActivity.this, e+"", Toast.LENGTH_SHORT).show();
                }
                finish();
                startActivity(new Intent(DetailActivity.this, ProfileActivity.class));
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

}
