package com.itpvt.mediaplayer;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.VideoView;

public class VideoPlay extends AppCompatActivity {

    VideoView videoView;
    ImageView imgg;
    SeekBar seekBar;
    String urls;
    boolean is_play;
    MediaController mediaC;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);

        init();



    }


    private void init() {
        videoView = (VideoView) findViewById(R.id.view);
        imgg = (ImageView) findViewById(R.id.imag);
        seekBar = (SeekBar) findViewById(R.id.seek);

        urls = getIntent().getStringExtra("video");
        videoView.setVideoPath(urls);

        mediaC  = new MediaController(this);

        videoView.setMediaController(mediaC);
        mediaC.setAnchorView(videoView);

        videoView.start();
        is_play = true;
        imgg.setImageResource(R.drawable.pause);


        updateseekbar();


    }

    private void updateseekbar() {
        handler= new Handler();
        handler.postDelayed(updatetime, 100);


    }

    public Runnable updatetime = new Runnable() {
        @Override
        public void run() {


            seekBar.setProgress(videoView.getCurrentPosition());
            seekBar.setMax(videoView.getDuration());
            handler.postDelayed(this, 100);

            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {


                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    handler.removeCallbacks(updatetime);
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    handler.removeCallbacks(updatetime);

                    videoView.seekTo(seekBar.getProgress());

                    updateseekbar();
                }
            });

        }
    };

    public  void  toogle(View playPause){

        if (is_play){


videoView.pause();
is_play=false;
            (imgg).setImageResource(R.drawable.play);

        }
        else if (is_play==false){

            videoView.start();
            updateseekbar();
            is_play=true;
            (imgg).setImageResource(R.drawable.pause);

        }



    }

}
