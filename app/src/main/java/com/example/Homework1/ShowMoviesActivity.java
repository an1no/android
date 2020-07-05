package com.example.Homework1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Homework1.services.GetMoviesAsyncTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ShowMoviesActivity extends AppCompatActivity {
    private MoviesArrayAdapter movieArrayAdapter;
    private ListView mMovies;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_movies);
        getMovies();
        movieArrayAdapter = new MoviesArrayAdapter(this, 0, new ArrayList<Movie>());
        mMovies = findViewById(R.id.movies);
        mMovies.setAdapter(movieArrayAdapter);
    }

    public void getMovies() {
        GetMoviesAsyncTask getMoviesAsyncTask = new GetMoviesAsyncTask();
        GetMoviesAsyncTask.Callback callback = new GetMoviesAsyncTask.Callback() {
            @Override
            public void onDataReceived(ArrayList<Movie> movies) {
                mMovies.setAdapter(movieArrayAdapter);
                movieArrayAdapter.addAll(movies);
            }
        };
        getMoviesAsyncTask.setCallback(callback);
        getMoviesAsyncTask.execute();
    }
    class MoviesArrayAdapter extends ArrayAdapter<Movie> {
        private Context mContext;
        public MoviesArrayAdapter(Context context,
                                   int resource,
                                   List<Movie> movieList){
            super(context,resource, movieList);
            mContext = context;
        }
        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            final Movie movie = getItem(position);
            LayoutInflater inflater = (LayoutInflater)mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.view_movie_item, parent,false);
            TextView textView = view.findViewById(R.id.header);
            textView.setText(movie.getName());
            ImageView imageView = view.findViewById(R.id.image);
            Picasso.get().load(movie.getImage()).into(imageView);
            return  view;
        }
    }
}
