package com.example.movitest;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.w3c.dom.Text;

public class DetailMovie extends YouTubeBaseActivity {
    private SimpleDraweeView poster_detail;
    private TextView title_detail;
    private TextView overview_detail;
    private YouTubePlayerView youtubeView;
    YouTubePlayer.OnInitializedListener playListener;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_info);

        poster_detail = findViewById(R.id.poster_detail);
        title_detail = findViewById(R.id.title_detail);
        overview_detail = findViewById(R.id.overview_detail);
        youtubeView = findViewById(R.id.youtubeView);



        playListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("4vWg5yJuWfs");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        youtubeView.initialize("AIzaSyB4gLjh8U0QvwAyl_gStzUHUe2MbNuFXrY",playListener);




        Intent intent = getIntent();

        String dtitle = intent.getStringExtra("title");
        String dposeter = intent.getStringExtra("poster_path");
        String doverview = intent.getStringExtra("overview");
        String t_id;

        Log.d("sre",dtitle);
        Log.d("sre",dposeter);
        Log.d("sre",doverview);
        Uri uri = Uri.parse("https://image.tmdb.org/t/p/w500/"+dposeter);

        poster_detail.setImageURI(uri);
        title_detail.setText("영화 제목:"+dtitle);
        overview_detail.setText("영화내용:"+doverview);













    }


}