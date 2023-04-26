package com.google.ar.core.examples.java.ww;

import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.ar.core.examples.java.augmentedimage.R;
import com.google.ar.core.examples.java.utils.Constants;

public class PlayIntroduction extends AppCompatActivity {

    private ProgressBar progressBar;
    private VideoView videoView;
    private String introVideoUrl;
    private String name;

    private static final String TAG = "PlayIntroduction";

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        else
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_introduction);

        initViews();
        getIntentData();
        initData();

        if(introVideoUrl!=null && !introVideoUrl.equals("")){
            setupVideoPlayer();
        }else{
            Toast.makeText(PlayIntroduction.this, getResources().getString(R.string.blank_video_url), Toast.LENGTH_SHORT).show();
        }
    }

    private void initData() {
        if(!TextUtils.isEmpty(name)){
            getSupportActionBar().setTitle(name);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupVideoPlayer() {
        Uri uri = Uri.parse(introVideoUrl);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);

        // sets the anchor view
        // anchor view for the videoView
        mediaController.setAnchorView(videoView);

        // sets the media player to the videoView
        mediaController.setMediaPlayer(videoView);

        // sets the media controller to the videoView
        videoView.setMediaController(mediaController);

        // perform set on prepared listener event on video view
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                // starts the video
                progressBar.setVisibility(View.GONE);
                videoView.start();
            }
        });

        // perform set on error listener event on video view
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(PlayIntroduction.this, getResources().getString(R.string.error_while_playing_video), Toast.LENGTH_SHORT).show();
                //PlayIntroduction.this.finish();
                return false;
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.stop();
                PlayIntroduction.this.finish();
                Toast.makeText(PlayIntroduction.this, getResources().getString(R.string.done), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getIntentData() {
        Intent intent = getIntent();
        introVideoUrl = intent.getStringExtra(Constants.KEY_INTRO_URL);
        name = intent.getStringExtra(Constants.KEY_NAME);
    }

    private void initViews() {
        progressBar = findViewById(R.id.progress_bar);
        videoView = findViewById(R.id.videoView);
    }
}