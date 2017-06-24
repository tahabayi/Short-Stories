package com.taha.deneme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    LinearLayout getstoryButton;
    LinearLayout edtpickButton;
    LinearLayout popButton;
    LinearLayout authButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseReference story_database = FirebaseDatabase.getInstance().getReference("stories");

        story_database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //--
        getstoryButton = (LinearLayout) findViewById(R.id.getstoryButton);
        edtpickButton = (LinearLayout) findViewById(R.id.edtpickButton);
        popButton = (LinearLayout) findViewById(R.id.popButton);
        authButton = (LinearLayout) findViewById(R.id.authButton);
        //--
        getstoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TimeCircleActivity.class);
                startActivity(intent);
            }
        });
        //--
        edtpickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EdtpickActivity.class);
                startActivity(intent);
            }
        });
        //--
        popButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PopularActivity.class);
                startActivity(intent);
            }
        });
        //--
        authButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AuthorActivity.class);
                startActivity(intent);
            }
        });
    }
}