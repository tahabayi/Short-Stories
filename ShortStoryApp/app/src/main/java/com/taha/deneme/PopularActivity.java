package com.taha.deneme;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class PopularActivity extends Activity {

    RecyclerView cycview;
    DatabaseReference story_database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cyclistview);
        //--
        cycview = (RecyclerView) findViewById(R.id.cycview);
        //-- get database
        story_database = FirebaseDatabase.getInstance().getReference("stories");
        //-- order stories by rating
        Query story_ordered=story_database.orderByChild("rating").limitToLast(40);
        //--
        Utility.storyToView(story_ordered,cycview,getApplicationContext(),this,false);

    }
}