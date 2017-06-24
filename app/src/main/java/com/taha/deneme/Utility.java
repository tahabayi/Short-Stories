package com.taha.deneme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by taha on 13/01/17.
 */

public class Utility {

    public static void storyToView(final Query story_ordered, final RecyclerView cycview, final Context context, final Activity main,final boolean aut){
        Log.d("story","deneme1");
        story_ordered.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Log.d("story","deneme2");
                final ArrayList<Story> storylist = new ArrayList<Story>();
                for(DataSnapshot child:dataSnapshot.getChildren()) {
                    int story_id = Integer.parseInt(child.getKey());
                    String title = child.child("title").getValue().toString();
                    String author = child.child("authorName").getValue().toString();
                    String url = child.child("textFile").getValue().toString();
                    String cover = child.child("coverPublic").getValue().toString();
                    float rating = Float.parseFloat(child.child("rating").getValue().toString());
                    boolean editorPick = (boolean)child.child("isEditorsPick").getValue();
                    int time = Integer.parseInt(child.child("time").getValue().toString());
                    //--
                    storylist.add(0,new Story(story_id,title,author,url, cover,rating,editorPick,time));
                }
                Collections.shuffle(storylist);
                final ArrayList<Story> finalList;
                if(storylist.size()>20 && !aut)
                    finalList = new ArrayList<Story>(storylist.subList(0,20));
                else
                    finalList = storylist;

                final StoryAdapter sAdapter = new StoryAdapter(finalList);

                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);



                cycview.setLayoutManager(mLayoutManager);
                cycview.setItemAnimator(new DefaultItemAnimator());
                cycview.setAdapter(sAdapter);
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(cycview.getContext());
                cycview.addItemDecoration(dividerItemDecoration);
                sAdapter.notifyDataSetChanged();
                cycview.addOnItemTouchListener(new RecyclerTouchListener(context, cycview, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Story story = finalList.get(position);
                        String texturl= story.getUrl();
                        int story_id = story.getId();
                        Intent intent = new Intent(main, TextActivity.class);
                        intent.putExtra("texturl",texturl);
                        intent.putExtra("story_id",story_id);
                        main.startActivity(intent);
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));
                Log.d("story","deneme3");
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        Log.d("story","deneme4");
    }



    public static void authorToView(final Query authorOrdered, final RecyclerView cycview, final Context context, final Activity main){
        //final FirebaseStorage storage = FirebaseStorage.getInstance();
        Log.d("story","deneme1");
        authorOrdered.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Log.d("story","deneme2");
                final ArrayList<Author> authorlist = new ArrayList<Author>();
                for(DataSnapshot child:dataSnapshot.getChildren()) {
                    String name = child.child("name").getValue().toString();
                    String nation = child.child("nationality").getValue().toString();
                    String storyNumber = child.child("numberStories").getValue().toString();
                    String cover = child.child("coverPublic").getValue().toString();
                    //--
                    authorlist.add(0,new Author(name,nation,storyNumber,cover));
                }
                Collections.shuffle(authorlist);
                final ArrayList<Author> finalList;
                //if(authorlist.size()>20)
                //    finalList = new ArrayList<Author>(authorlist.subList(0,20));
                //else
                    finalList = authorlist;

                final AuthorAdapter sAdapter = new AuthorAdapter(finalList);
                //TODO
                /*for(final Author a:finalList){
                    StorageReference autcover = storage.getReferenceFromUrl(a.getCover());

                    autcover.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            if(!a.loaded){
                                a.urlFull = uri.toString();
                                a.loaded = true;
                                sAdapter.notifyDataSetChanged();
                            }
                        }
                    });
                }*/

                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
                cycview.setLayoutManager(mLayoutManager);
                cycview.setItemAnimator(new DefaultItemAnimator());
                cycview.setAdapter(sAdapter);
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(cycview.getContext());
                cycview.addItemDecoration(dividerItemDecoration);
                sAdapter.notifyDataSetChanged();
                cycview.addOnItemTouchListener(new RecyclerTouchListener(context, cycview, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Author author = finalList.get(position);
                        String autname= author.getName();
                        Intent intent = new Intent(main, StorybyautActivity.class);
                        intent.putExtra("autname",autname);
                        main.startActivity(intent);
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));
                Log.d("story","deneme3");
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        Log.d("story","deneme4");
    }


}
