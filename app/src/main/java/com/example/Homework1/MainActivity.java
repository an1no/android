package com.example.Homework1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Homework1.services.GetMoviesAsyncTask;
import com.example.Homework1.services.GetMoviesAsyncTask.Callback;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_login);
        getMovies();
    }

    public void getMovies() {

        GetMoviesAsyncTask getMoviesAsyncTask = new GetMoviesAsyncTask();
        Callback callback = new Callback() {
            @Override
            public void onDataReceived(ArrayList<Movie> movies) {

            }
        };
        getMoviesAsyncTask.setCallback(callback);
        getMoviesAsyncTask.execute();
    }

    public void SignUp(View view) {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
}
