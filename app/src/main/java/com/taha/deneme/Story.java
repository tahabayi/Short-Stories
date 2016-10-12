package com.taha.deneme;

import java.util.List;

/**
 * Created by taha on 01.10.2016.
 */

public class Story {
    private String title;
    private String author;
    private String url;
    private List<String> genres;
    private String cover;
    private float rating;
    private boolean editorPick;
    private int time;
    boolean loaded=false;
    String urlFull;

    public Story(String title, String author, String url, List<String> genres, String cover, float rating, boolean editorPick,int time) {
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
