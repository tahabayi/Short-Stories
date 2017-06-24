package com.taha.deneme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

/**
 * Created by taha on 12.10.2016.
 */

public class TimeListActivity extends Activity{

    private RecyclerView cycview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cyclistview);
        //--
        Intent intent = getIntent();
        int time = intent.getIntExtra("time",5);
        //--
        cycview = (RecyclerView) findViewById(R.id.cycview);
        //--
        final DatabaseReference story_database = FirebaseDatabase.getInstance().getReference("stories");
        Query story_ordered = story_database.orderByChild("time").startAt(time-1).endAt(time+1).limitToFirst(40);

        Utility.storyToView(story_ordered,cycview,getApplicationContext(),this,false);

    }

}
