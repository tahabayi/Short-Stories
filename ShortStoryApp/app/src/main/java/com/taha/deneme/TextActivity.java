package com.taha.deneme;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by taha on 06.10.2016.
 */

public class TextActivity extends FragmentActivity {


    private int story_id;
    private TextView mTextView;
    private Pagination mPagination;
    private CharSequence mText;
    private int mCurrentIndex = 0;
    public String text;



    private String texturl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagination);


        Intent intent = getIntent();
        texturl = intent.getStringExtra("texturl");
        story_id = intent.getIntExtra("story_id",0);

        mTextView = (TextView) findViewById(R.id.tv);
        changeFont(mTextView);
        final FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storytext = storage.getReferenceFromUrl(texturl);

        final long ONE_MEGABYTE = 1024 * 1024;
        Task t=storytext.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                text = new String(bytes);
                deneme(text);
            }
        });





    }

    private void update() {
        final CharSequence text = mPagination.get(mCurrentIndex);
        if(text != null) mTextView.setText(text);
    }

    protected void changeFont(TextView tv){
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Bookerly-Regular.ttf");
        tv.setTypeface(custom_font);
    }
    public void deneme(String s){
        mText = s;
        mPagination = new Pagination(mText,
                mTextView.getWidth(),
                mTextView.getHeight(),
                mTextView.getPaint(),
                mTextView.getLineSpacingMultiplier(),
                mTextView.getLineSpacingExtra(),
                mTextView.getIncludeFontPadding());
        update();

        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex > 0) ? mCurrentIndex - 1 : 0;
                update();
            }
        });
        findViewById(R.id.btn_forward).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCurrentIndex==mPagination.size()-1){
                    if (findViewById(R.id.fragment_container) != null) {
                        findViewById(R.id.btn_back).setOnClickListener(null);
                        findViewById(R.id.btn_forward).setOnClickListener(null);

                        Bundle bundle = new Bundle();
                        bundle.putInt("story_id",story_id);

                        // Create a new Fragment to be placed in the activity layout
                        RatingFragment ratingFragment = new RatingFragment();

                        ratingFragment.setArguments(bundle);

                        // Add the fragment to the 'fragment_container' FrameLayout
                        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,ratingFragment,null).commit();
                        findViewById(R.id.fragment_container).bringToFront();
                    }
                }

                mCurrentIndex = (mCurrentIndex < mPagination.size() - 1) ? mCurrentIndex + 1 : mPagination.size() - 1;
                update();
            }
        });
    }
}
