package com.example.Homework1;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
import com.example.Homework1.services.GetMoviesAsyncTask.Callback;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getMovies(View view) {
        findViewById(R.id.progress).setVisibility(View.VISIBLE);
        GetMoviesAsyncTask getMoviesAsyncTask = new GetMoviesAsyncTask();
        Callback callback = new Callback() {
            @Override
            public void onDataReceived(ArrayList<Movie> movies) {

            }
        };
        getMoviesAsyncTask.setCallback(callback);
        getMoviesAsyncTask.execute();
    }
}
