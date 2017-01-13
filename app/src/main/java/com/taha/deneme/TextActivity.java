package com.taha.deneme;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by taha on 06.10.2016.
 */

public class TextActivity extends Activity {

    private TextView mTextView;
    private Pagination mPagination;
    private CharSequence mText;
    private int mCurrentIndex = 0;



    private String texturl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagination);


        Intent intent = getIntent();
        texturl = intent.getStringExtra("texturl");

        mTextView = (TextView) findViewById(R.id.tv);
        changeFont(mTextView);
        final FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storytext = storage.getReferenceFromUrl(texturl);

        final long ONE_MEGABYTE = 1024 * 1024;
        Task t=storytext.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                final String s = new String(bytes);
                deneme(s);
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
    private void deneme(String s){
        mText = s;
        mPagination = new Pagination(mText,
                mTextView.getWidth(),
                mTextView.getHeight(),
                mTextView.getPaint(),
                mTextView.getLineSpacingMultiplier(),
                mTextView.getLineSpacingExtra(),
                mTextView.getIncludeFontPadding());
        update();


        /*mTextView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // Removing layout listener to avoid multiple calls
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    mTextView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    mTextView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }

            }
        });*/

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
                mCurrentIndex = (mCurrentIndex < mPagination.size() - 1) ? mCurrentIndex + 1 : mPagination.size() - 1;
                update();
            }
        });
    }
}
