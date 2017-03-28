package com.taha.deneme;

/**
 * Created by taha on 04.10.2016.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.MyViewHolder>{
    private ArrayList<Story> storylist;
    private Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, author, time;
        public RatingBar rating;
        public ImageView cover,editorPick;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            author = (TextView) view.findViewById(R.id.author);
            rating = (RatingBar) view.findViewById(R.id.ratingBar);
            cover = (ImageView) view.findViewById(R.id.cover);
            editorPick = (ImageView) view.findViewById(R.id.editorsPickImage);
            time = (TextView) view.findViewById(R.id.time);
        }
    }


    public StoryAdapter(ArrayList<Story> storylist) {
        this.storylist = storylist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listview, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //final MyViewHolder holder1=holder;
        final Story story = storylist.get(position);
        holder.title.setText(story.getTitle());
        holder.author.setText(story.getAuthor());
        holder.rating.setRating(story.getRating());
        holder.time.setText(String.valueOf(story.getTime())+" minutes");

        //if(story.loaded){
            Picasso.with(context).load(story.getCover()).into(holder.cover);
        //}

        if(story.getEditorPick())
            holder.editorPick.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return storylist.size();
    }

}