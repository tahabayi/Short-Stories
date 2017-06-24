package com.taha.deneme;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Profile;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by tahabayi on 23/06/2017.
 */

public class RatingFragment extends Fragment{

    Fragment f;
    int story_id;
    float vote;

    @Override
    public View onCreateView(LayoutInflater inflater,final ViewGroup container,Bundle savedInstanceState) {
        // get views
        container.setVisibility(View.INVISIBLE);
        View view = inflater.inflate(R.layout.fragment_rating, container, false);
        Button turn_back_button = (Button) view.findViewById(R.id.turn_back_button);
        Button rate_button = (Button) view.findViewById(R.id.rate_button);
        final RatingBar rating_bar = (RatingBar) view.findViewById(R.id.ratingBar);
        final TextView voted_text = (TextView) view.findViewById(R.id.voted_text);
        //
        final DatabaseReference dbr = FirebaseDatabase.getInstance().getReference("users");
        //
        story_id = getArguments().getInt("story_id");
        final String story_id_str = String.valueOf(story_id);
        final String user_id_tr = Profile.getCurrentProfile().getId();
        DatabaseReference user_ref = dbr.child(user_id_tr);
        user_ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(story_id_str)) {
                    vote = Float.valueOf(dataSnapshot.child(story_id_str).getValue().toString());
                    rating_bar.setRating(vote);
                }else{
                    voted_text.setVisibility(View.INVISIBLE);
                }
                container.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        // get fragment
        f=this;
        // set click listeners
        turn_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(f).commit();
                TextActivity t = (TextActivity) getActivity();
                t.deneme(t.text);
            }
        });

        rate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float user_rating = rating_bar.getRating();
                dbr.child(user_id_tr).child(story_id_str).setValue(user_rating);
                Toast.makeText(getActivity().getApplicationContext(), "Thank you for rating.", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });

        return view;
    }

}
