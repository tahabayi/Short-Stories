package com.taha.deneme;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

/**
 * Created by taha on 23/01/17.
 */

public class AuthorActivity extends Activity{

    RecyclerView cycview;
    DatabaseReference story_database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cyclistview);
        //--
        cycview = (RecyclerView) findViewById(R.id.cycview);
        //-- get database
        story_database = FirebaseDatabase.getInstance().getReference("authors");
        //-- order stories by rating
        Query authorordered=story_database.orderByChild("name").limitToLast(40);
        //--
        Utility.authorToView(authorordered,cycview,getApplicationContext(),this);

    }

}
