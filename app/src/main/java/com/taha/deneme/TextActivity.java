package com.taha.deneme;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by taha on 06.10.2016.
 */

public class TextActivity extends Activity {

    TextView tv;

    protected static String texturl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_activity);
        tv = (TextView) findViewById(R.id.storytext);
        changeFont(tv);
        final FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storytext = storage.getReferenceFromUrl(texturl);

                        final long ONE_MEGABYTE = 1024 * 1024;
                        storytext.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                String text = new String(bytes);
                                tv.setText(text);
                            }
                        });
    }

    protected void changeFont(TextView tv){
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Bookerly-Regular.ttf");
        tv.setTypeface(custom_font);
    }
}
