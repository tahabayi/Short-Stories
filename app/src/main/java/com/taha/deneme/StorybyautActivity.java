package com.taha.deneme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

/**
 * Created by taha on 24/01/17.
 */

public class StorybyautActivity extends Activity{

    RecyclerView cycview;
    String autname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cyclistview);

        cycview = (RecyclerView) findViewById(R.id.cycview);

        Intent intent = getIntent();
        autname = intent.getStringExtra("autname");

        DatabaseReference aut_database = FirebaseDatabase.getInstance().getReference("stories");
        Query storybyaut=aut_database.orderByChild("authorName").equalTo(autname);
        Utility.storyToView(storybyaut, cycview, getApplicationContext(),this,true);
    }


}
