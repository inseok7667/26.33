package com.example.movitest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
    private EditText search_movie;
    private Button search_btn;

    String searchMovie;
    List<Movie> movie = new ArrayList<Movie>();

    RequestQueue queue;
//    RequestQueue queue2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search_movie=(EditText) findViewById(R.id.search_movie);
        recyclerView = (RecyclerView) findViewById(R.id.my_recyclerView);
        recyclerView.setHasFixedSize(true);


        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));

        queue = Volley.newRequestQueue(this);
//        queue2 =  Volley.newRequestQueue(this);

        search_btn=(Button)findViewById(R.id.search_btn);

        getMovie();
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchText();
//                Intent intent2 = new Intent(MainActivity.this,SearchMoviePage.class);
//
//                intent2.putExtra("searchMovie",searchMovie);
//                startActivity(intent2);
                getMovie2();
            }
        });


//        search_movie.addTextChangedListener(new TextWatcher() {
////            @Override
////            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
////
////            }
////
////            @Override
////            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
////                SearchText(charSequence);
////                Log.d("search",searchMovie);
////            }
////
////            @Override
////            public void afterTextChanged(Editable editable) {
////
////            }
////
////        });



    }

    public void getMovie(){

        String url ="https://api.themoviedb.org/3/movie/upcoming?api_key=95adb1fd22031e832d7c94209652414c&language=ko-KR&page=1";
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




                            mAdapter = new MyAdapter(movie,MainActivity.this, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if(view.getTag() != null){
                                        int position = (int)view.getTag();
                                        ((MyAdapter)mAdapter).getMovie_detail(position);
                                        Intent intent = new Intent(MainActivity.this,DetailMovie.class);


                                        intent.putExtra("id",movie.get(position).getId());
                                        intent.putExtra("title",movie.get(position).getTitle());
                                        intent.putExtra("poster_path",movie.get(position).getPoset_path());
                                        intent.putExtra("overview",movie.get(position).getOverview());

                                        startActivity(intent);
                                        Log.d("sese", movie.get(position).getId());

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
    public void getMovie2(){
        final List<Movie> movie2 = new ArrayList<Movie>();
        RequestQueue queue2 = Volley.newRequestQueue(this);

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
                                movie2.add(moviedata);



                                Log.d("tjr",moviedata.getId());
                                Log.d("rere",moviedata.getPoset_path());
                                Log.d("rere",moviedata.getTitle());
                                Log.d("rere",moviedata.getOverview());

                            }




                            mAdapter = new MyAdapter2(movie2,MainActivity.this, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if(view.getTag() != null){
                                        int position = (int)view.getTag();
                                        ((MyAdapter2)mAdapter).getMovie_detail(position);
                                        Intent intent = new Intent(MainActivity.this,DetailMovie.class);


                                        intent.putExtra("id",movie2.get(position).getId());
                                        intent.putExtra("title",movie2.get(position).getTitle());
                                        intent.putExtra("poster_path",movie2.get(position).getPoset_path());
                                        intent.putExtra("overview",movie2.get(position).getOverview());

                                        startActivity(intent);
                                        Log.d("sese", movie2.get(position).getId());

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

        queue2.add(stringRequest);

    }
    public void SearchText(){
        searchMovie = search_movie.getText().toString();
    }

}