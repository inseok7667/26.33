package com.example.movitest;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder>{
    private  List<Movie> mDataset;
    public static View.OnClickListener onClickListener;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public SimpleDraweeView Movie_path;
        public TextView Movie_title;
        public TextView overview;
        public View rootview;
        public MyViewHolder(View v){
            super(v);
            Movie_path = (SimpleDraweeView)v.findViewById(R.id.Moive_path);
            Movie_title = v.findViewById(R.id.Movie_title);
            rootview = v;
            v.setClickable(true);
            v.setEnabled(true);
            v.setOnClickListener(onClickListener);

        }

    }
    public MyAdapter2(List<Movie> myDataset, Context context, View.OnClickListener onClick){
        mDataset = myDataset;
        onClickListener = onClick;
        Fresco.initialize(context);


    }


    @NonNull
    @Override
    public MyAdapter2.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.title_image, parent, false);
        MyViewHolder vh = new MyViewHolder(linearLayout);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter2.MyViewHolder holder, int position) {

        Movie movie = mDataset.get(position);
        holder.Movie_title.setText(movie.getTitle());

        Uri uri = Uri.parse("https://image.tmdb.org/t/p/w500/"+movie.getPoset_path());
        //SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.Moive_path);
        holder.Movie_path.setImageURI(uri);

        holder.rootview.setTag(position);

    }

    @Override
    public int getItemCount() {
        return mDataset == null ? 0: mDataset.size();
    }
    public Movie getMovie_detail(int position){ return mDataset != null ? mDataset.get(position) : null;}
}