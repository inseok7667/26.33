package com.example.movitest;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class DetailMovie extends YouTubeBaseActivity {
    private SimpleDraweeView poster_detail;
    private TextView title_detail;
    private TextView overview_detail;
    private YouTubePlayerView youtubeView;
    private YouTubePlayer.OnInitializedListener playListener;
    ArrayList<Youtube> youtube_key = new ArrayList<Youtube>();



    String k_id;

    RequestQueue queue;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_info);

        Intent intent = getIntent();
        String m_id = intent.getStringExtra("id");
        k_id = m_id;
        String dtitle = intent.getStringExtra("title");
        String dposeter = intent.getStringExtra("poster_path");
        String doverview = intent.getStringExtra("overview");

        poster_detail = findViewById(R.id.poster_detail);
        title_detail = findViewById(R.id.title_detail);
        overview_detail = findViewById(R.id.overview_detail);
        youtubeView = findViewById(R.id.youtubeView);


        Uri uri = Uri.parse("https://image.tmdb.org/t/p/w500/" + dposeter);

        poster_detail.setImageURI(uri);
        title_detail.setText("영화 제목:" + dtitle);
        overview_detail.setText("영화내용:" + doverview);

        queue = Volley.newRequestQueue(this);


        getYoutubeUrl();

    }

    public void getYoutubeUrl() {

        String url = "https://api.themoviedb.org/3/movie/" + k_id + "/videos?api_key=95adb1fd22031e832d7c94209652414c";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            JSONArray arrayArticles = jsonObj.getJSONArray("results");


                            for (int i = 0; i < arrayArticles.length(); i++) {
                                JSONObject obj = arrayArticles.getJSONObject(i);

                                Youtube youtubedata = new Youtube();


                                youtubedata.setY_id(obj.getString("key"));

                                youtube_key.add(youtubedata);




                            }
                            Log.d("seok22",""+youtube_key.get(0).getY_id());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
        playListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                try{
                    if(youtube_key.get(0).getY_id()!=null){
                        youTubePlayer.loadVideo(youtube_key.get(0).getY_id());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        youtubeView.initialize("AIzaSyB4gLjh8U0QvwAyl_gStzUHUe2MbNuFXrY", playListener);


    }


}