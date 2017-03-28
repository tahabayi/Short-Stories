package com.taha.deneme;

/**
 * Created by taha on 23/01/17.
 */

public class Author {

    private String name;
    private String nation;
    private String storyNumber;
    private String cover;
    //boolean loaded=false;
    //String urlFull;

    public Author(String name, String nation, String storyNumber, String cover) {
        this.name = name;
        this.nation = nation;
        this.storyNumber = storyNumber;
        this.cover = cover;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getName(){
        return this.name;
    }

    public String getNation(){
        return this.nation;
    }

    public String getStoryNumber(){
        return this.storyNumber;
    }

    public String getCover(){
        return this.cover;
    }

}
