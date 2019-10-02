package com.example.movitest;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;
import java.util.List;

public class SearchMoviePage extends Activity {
    private RecyclerView recyclerView2;
    private RecyclerView.Adapter mAdapter;
    private EditText search_movie2;
    private Button search_btn2;
    private RecyclerView.LayoutManager layoutManager;
    RequestQueue queue;
    List<Movie> movie = new ArrayList<Movie>();
    String searchMovie;

    protected void onCreate(Bundle savedInstanceState) {
        Log.d("tlfgod","실행");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_activity);

        Intent intent = getIntent();
        searchMovie= intent.getStringExtra("searchMovie");



        search_movie2=(EditText) findViewById(R.id.search_movie2);
        recyclerView2 = (RecyclerView) findViewById(R.id.my_recyclerView2);
        recyclerView2.setHasFixedSize(true);


        layoutManager = new LinearLayoutManager(this);
        recyclerView2.setLayoutManager(new GridLayoutManager(SearchMoviePage.this, 2));

        queue = Volley.newRequestQueue(this);

        getMovie();


    }
    public void getMovie(){

        String url = "https://api.themoviedb.org/3/search/movie?api_key=95adb1fd22031e832d7c94209652414c&query="+searchMovie+"&language=ko-KR&page=1";

        Log.d("searchMovie",searchMovie);
        Log.d("searchMovie",url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("rere",response);

                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            JSONArray arrayArticles = jsonObj.getJSONArray("results");


                            for(int i = 0; i<arrayArticles.length(); i++)
                            {
                                JSONObject obj = arrayArticles.getJSONObject(i);

                                Movie moviedata = new Movie();


                                moviedata.setId(obj.getString("id"));
                                moviedata.setTitle(obj.getString("title"));
                                moviedata.setPoset_path(obj.getString("poster_path"));
                                moviedata.setOverview(obj.getString("overview"));
                                movie.add(moviedata);



                                Log.d("tjr",moviedata.getId());
                                Log.d("rere",moviedata.getPoset_path());
                                Log.d("rere",moviedata.getTitle());
                                Log.d("rere",moviedata.getOverview());

                            }




                            mAdapter = new MyAdapter2(movie,SearchMoviePage.this, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if(view.getTag() != null){
                                        int position = (int)view.getTag();
                                        ((MyAdapter2)mAdapter).getMovie_detail(position);
                                        Intent intent = new Intent(SearchMoviePage.this,DetailMovie.class);


                                        intent.putExtra("id",movie.get(position).getId());
                                        intent.putExtra("title",movie.get(position).getTitle());
                                        intent.putExtra("poster_path",movie.get(position).getPoset_path());
                                        intent.putExtra("overview",movie.get(position).getOverview());

                                        startActivity(intent);
                                        Log.d("sese", movie.get(position).getId());

                                    }
                                }
                            });
                            recyclerView2.setAdapter(mAdapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }





                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        queue.add(stringRequest);

    }

}