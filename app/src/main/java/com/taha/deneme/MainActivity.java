package com.taha.deneme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
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
                Log.d("gotit","gotdb");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /*final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("authors");
        ref.orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> arr = new ArrayList<String>();
                for (DataSnapshot child:dataSnapshot.getChildren()){
                    arr.add(child.child("picture").getValue().toString());
                }
                final FirebaseStorage storage = FirebaseStorage.getInstance();
                final String [] str=new String[arr.size()];
                for(int i=0;i<arr.size();i++){
                    final int x=i;
                    StorageReference storycover = storage.getReferenceFromUrl(arr.get(i));

                    storycover.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            ref.child(String.valueOf(x)).child("coverPublic").setValue(uri.toString());
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/



        /*Log.d("time","deneme1");
        ref.orderByChild("rating").limitToLast(20).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot msgSnapshot: snapshot.getChildren()) {
                    StoryCus msg = msgSnapshot.getValue(StoryCus.class);
                }
                Log.d("time","deneme2");
            }
            @Override
            public void onCancelled(DatabaseError firebaseError) {
            }
        });*/


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