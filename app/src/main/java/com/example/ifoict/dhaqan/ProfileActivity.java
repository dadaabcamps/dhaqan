package com.example.ifoict.dhaqan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = ProfileActivity.class.getSimpleName();
    RecyclerView rv;
    LinearLayoutManager linearLayoutManager;
    DatabaseReference db;
    FirebaseRecyclerAdapter<Article, ArticleViewHolder> firebasenewsRecycleAdapter;
    ProgressBar progressBarNoticeList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_item_list);


        //Initialize Firebase DB
        db = FirebaseDatabase.getInstance().getReference();

        //SETUP RECYCLER
        rv = (RecyclerView) findViewById(R.id.recyclerViewArticleList);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(false);


//        progressBarNoticeList = (ProgressBar) findViewById(R.id.progressBarArticleList);
//        progressBarNoticeList.setVisibility(View.VISIBLE);

        firebasenewsRecycleAdapter = new FirebaseRecyclerAdapter<Article, ArticleViewHolder>(Article.class, R.layout.article_item_list, ArticleViewHolder.class, db.child("Notices")) {
            @Override
            protected void populateViewHolder(ArticleViewHolder viewHolder, final Article model, final int position) {
                viewHolder.textViewListArticleTitle.setText(model.getTitle());
                viewHolder.textViewListArticleCategory.setText(model.getCategory());
                viewHolder.textViewListArticleAuthor.setText(model.getAuthor());
//                progressBarNoticeList.setVisibility(View.GONE);
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //firebasenewsRecycleAdapter.getRef(position).removeValue();
                        openNoticeDetailActivity(model.getTitle(), model.getAuthor(), model.getContent());
                    }
                });
            }

            private void openNoticeDetailActivity(String title, String author, String content) {
                Intent newsIntent = new Intent(ProfileActivity.this, DetailActivity.class);
                newsIntent.putExtra("titleKey", title);
                newsIntent.putExtra("authorKey", author);
                newsIntent.putExtra("contentKey", content);

                startActivity(newsIntent);
            }
        };
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(firebasenewsRecycleAdapter);

    }

//    public void openCreateNoticeActivity(View v){
//        startActivity( new Intent(ListNoticeActivity.this, CreateNoticeActivity.class));
//    }
}

