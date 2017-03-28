package com.taha.deneme;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by taha on 23/01/17.
 */

public class AuthorAdapter extends RecyclerView.Adapter<AuthorAdapter.MyViewHolder>{
    private ArrayList<Author> authorlist;
    private Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, nation, storyNumber;
        public ImageView cover;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.autname);
            nation = (TextView) view.findViewById(R.id.nation);
            storyNumber = (TextView) view.findViewById(R.id.storyNumber);
            cover = (ImageView) view.findViewById(R.id.autcover);
        }
    }


    public AuthorAdapter(ArrayList<Author> authorlist) {
        this.authorlist = authorlist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.autlistview, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AuthorAdapter.MyViewHolder holder, int position) {
        //final AuthorAdapter.MyViewHolder holder1=holder;
        final Author author = authorlist.get(position);
        holder.name.setText(author.getName());
        holder.nation.setText(author.getNation());
        holder.storyNumber.setText(author.getStoryNumber());

        //if(author.loaded){
            Picasso.with(context).load(author.getCover()).into(holder.cover);
        //}
    }

    @Override
    public int getItemCount() {
        return authorlist.size();
    }

}
