package com.example.movitest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Context mcontext;
    List<Movie> movie = new ArrayList<Movie>();
   // private String[] myDataset={"어벤져스","토르","블랙위도우","토이스토리","변신","나쁜녀석들","7","8","9","10"};
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.my_recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2)

);




        queue = Volley.newRequestQueue(this);
        getMovie();
    }

    public void getMovie(){
        // Instantiate the RequestQueue.
        String url ="https://api.themoviedb.org/3/movie/upcoming?api_key=95adb1fd22031e832d7c94209652414c&language=ko-KR&page=1";

// Request a string response from the provided URL.
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

                                moviedata.setTitle(obj.getString("title"));
                                moviedata.setPoset_path(obj.getString("poster_path"));
                                moviedata.setOverview(obj.getString("overview"));
                                movie.add(moviedata);




                                Log.d("rere",moviedata.getPoset_path());
                                Log.d("rere",moviedata.getTitle());
                                Log.d("rere",moviedata.getOverview());

                            }




                            mAdapter = new MyAdapter(movie,MainActivity.this, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if(view.getTag() != null){
                                        int position = (int)view.getTag();
                                        ((MyAdapter)mAdapter).getMovie_detail(position);
                                        Intent intent = new Intent(MainActivity.this,DetailMovie.class);

                                        intent.putExtra("title",movie.get(position).getTitle());
                                        intent.putExtra("poster_path",movie.get(position).getPoset_path());
                                        intent.putExtra("overview",movie.get(position).getOverview());

                                        startActivity(intent);
                                        Log.d("sese", String.valueOf(position));

                                    }
                                }
                            });
                            recyclerView.setAdapter(mAdapter);


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