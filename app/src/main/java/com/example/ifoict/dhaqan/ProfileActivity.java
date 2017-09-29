package com.example.ifoict.dhaqan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = ProfileActivity.class.getSimpleName();
    RecyclerView rv;
    LinearLayoutManager linearLayoutManager;
    DatabaseReference db;
    FirebaseAuth mAuth;
    FirebaseRecyclerAdapter<Article, ArticleViewHolder> firebasenewsRecycleAdapter;
    ProgressBar progressBarArticleList;
    private static Boolean isVisited = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if(!isVisited){
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            isVisited = true;
        }

        //Initialize Firebase DB
        db = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        //SETUP RECYCLER
        rv = (RecyclerView) findViewById(R.id.recyclerViewArticleList);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(false);

        progressBarArticleList = (ProgressBar) findViewById(R.id.progressBarArticleList);
        progressBarArticleList.setVisibility(View.VISIBLE);

        firebasenewsRecycleAdapter = new FirebaseRecyclerAdapter<Article, ArticleViewHolder>(Article.class, R.layout.article_item_list, ArticleViewHolder.class, db.child("Articles")) {
            @Override
            protected void populateViewHolder(ArticleViewHolder viewHolder, final Article model, final int position) {
                viewHolder.textViewListArticleTitle.setText(model.getTitle());
                viewHolder.textViewListArticleCategory.setText(model.getCategory());
                viewHolder.textViewListArticleAuthor.setText(model.getAuthor());
                progressBarArticleList.setVisibility(View.GONE);
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openDetailActivity(model.getId(), model.getTitle(), model.getCategory(), model.getAuthor(), model.getContent());
                    }
                });
            }

            private void openDetailActivity(String id, String title, String category, String author, String content) {
                Intent articleIntent = new Intent(ProfileActivity.this, DetailActivity.class);
                articleIntent.putExtra("idKey", id);
                articleIntent.putExtra("titleKey", title);
                articleIntent.putExtra("categoryKey", category);
                articleIntent.putExtra("authorKey", author);
                articleIntent.putExtra("contentKey", content);
                startActivity(articleIntent);
            }
        };
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(firebasenewsRecycleAdapter);

    }

    public void openCreateActivity(View v){
        startActivity( new Intent(ProfileActivity.this, CreateActivity.class));
    }

    public void openLoginActivity(){
        startActivity( new Intent(ProfileActivity.this, CreateActivity.class));

    }
    public void logOut(View v){
        mAuth.signOut();
        openLoginActivity();
    }
}

