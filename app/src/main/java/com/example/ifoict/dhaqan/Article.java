package com.example.ifoict.dhaqan;

/**
 * Created by IFO ICT on 9/20/2017.
 */

public class Article {
    String id, title, author, content, category;

    public Article(){

    }

    public Article(String id, String title, String author, String content, String category){

        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.category = category;
    }

    public String getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }

    public String getCategory(){
        return category;
    }

    public String getAuthor(){
        return author;
    }

    public String getContent(){
        return content;
    }
}
