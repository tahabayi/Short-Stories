package com.taha.deneme;

import java.util.List;

/**
 * Created by taha on 01.10.2016.
 */

public class Story {
    private int story_id;
    private String title;
    private String author;
    private String url;
    private List<String> genres;
    private String cover;
    private float rating;
    private boolean editorPick;
    private int time;
    //boolean loaded=false;
    //String urlFull;

    public Story(){}

    public Story(int story_id, String title, String author, String url, String cover, float rating, boolean editorPick,int time) {
        this.story_id = story_id;
        this.title = title;
        this.author = author;
        this.url = url;
        this.genres = genres;
        this.cover = cover;
        this.rating = rating;
        this.editorPick = editorPick;
        this.time = time;
    }

    @Override
    public String toString() {
        return this.title;
    }

    public int getId() {
        return this.story_id;
    }

    public String getTitle(){
        return this.title;
    }

    public String getAuthor(){
        return this.author;
    }

    public String getUrl(){
        return this.url;
    }

    public List<String> getGenres(){
        return this.genres;
    }

    public String getCover(){
        return this.cover;
    }

    public float getRating(){
        return this.rating;
    }

    public boolean getEditorPick(){
        return editorPick;
    }

    public int getTime(){
        return time;
    }
}
