package com.taha.deneme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jesusm.holocircleseekbar.lib.HoloCircleSeekBar;

/**
 * Created by taha on 12.10.2016.
 */

public class TimeCircleActivity extends Activity{

    private HoloCircleSeekBar seekBar;
    private Button popularButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timecircle);
        //--
        //--
        seekBar = (HoloCircleSeekBar) findViewById(R.id.picker);
        popularButton = (Button) findViewById(R.id.popularButton);
        //--
        seekBar.setValue(5);
        seekBar.setOnSeekBarChangeListener(new HoloCircleSeekBar.OnCircleSeekBarChangeListener() {
            @Override
            public void onStartTrackingTouch(HoloCircleSeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(HoloCircleSeekBar seekBar) {
            }
            @Override
            public void onProgressChanged(HoloCircleSeekBar seekBar, int progress, boolean fromUser) {
                if (progress < 5)
                    seekBar.setValue(5);
            }
        });

        popularButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int time = seekBar.getValue();
                Intent intent = new Intent(TimeCircleActivity.this,TimeListActivity.class);
                intent.putExtra("time",time);
                startActivity(intent);
            }
        });

    }
}
