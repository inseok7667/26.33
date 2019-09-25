package com.example.movitest;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.w3c.dom.Text;

public class DetailMovie extends Activity {
    private SimpleDraweeView poster_detail;
    private TextView title_detail;
    private TextView overview_detail;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_info);

        poster_detail = findViewById(R.id.poster_detail);
        title_detail = findViewById(R.id.title_detail);
        overview_detail = findViewById(R.id.overview_detail);

        Intent intent = getIntent();

        String dtitle = intent.getStringExtra("title");
        String dposeter = intent.getStringExtra("poster_path");
        String doverview = intent.getStringExtra("overview");

        Log.d("sre",dtitle);
        Log.d("sre",dposeter);
        Log.d("sre",doverview);
        Uri uri = Uri.parse("https://image.tmdb.org/t/p/w500/"+dposeter);

        poster_detail.setImageURI(uri);
        title_detail.setText("영화 제목"+dtitle);
        overview_detail.setText("영화내용:"+doverview);






    }


}