package com.taha.deneme;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    RecyclerView cycview;
    StoryAdapter sAdapter;
    DatabaseReference story_database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //--
        cycview = (RecyclerView) findViewById(R.id.recyclerview);
        //-- get database
        story_database = FirebaseDatabase.getInstance().getReference("stories");
        //-- firebase storage
        final FirebaseStorage storage = FirebaseStorage.getInstance();
        //-- order stories by rating
        Query story_ordered=story_database.orderByChild("rating").limitToLast(40);

        story_ordered.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                final ArrayList<Story> storylist = new ArrayList<Story>();
                for(DataSnapshot child:dataSnapshot.getChildren()) {
                    String title = child.child("title").getValue().toString();
                    String author = child.child("authorName").getValue().toString();
                    String url = child.child("textFile").getValue().toString();
                    String cover = child.child("cover").getValue().toString();
                    float rating = Float.parseFloat(child.child("rating").getValue().toString());
                    boolean editorPick = (boolean)child.child("isEditorsPick").getValue();
                    int time = Integer.parseInt(child.child("time").getValue().toString());
                    //--
                    storylist.add(0,new Story(title,author,url,null, cover,rating,editorPick,time));
                }
                Collections.shuffle(storylist);
                final ArrayList<Story> finalList = new ArrayList<Story>(storylist.subList(0,20));

                sAdapter = new StoryAdapter(finalList);
                for(final Story s:finalList){
                    StorageReference storycover = storage.getReferenceFromUrl(s.getCover());

                    storycover.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            if(!s.loaded){
                                s.urlFull = uri.toString();
                                s.loaded = true;
                                sAdapter.notifyDataSetChanged();
                            }
                        }
                    });
                }

                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                cycview.setLayoutManager(mLayoutManager);
                cycview.setItemAnimator(new DefaultItemAnimator());
                cycview.setAdapter(sAdapter);
                sAdapter.notifyDataSetChanged();
                cycview.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), cycview, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Story story = finalList.get(position);
                        TextActivity.texturl = story.getUrl();
                        Intent intent = new Intent(MainActivity.this, TextActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }


}