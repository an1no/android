package com.example.homework1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.homework1.data.StorageSharePreference;

public class TestActivity extends AppCompatActivity {
    private int correctAnswers = 0;
    private StorageSharePreference mSharePreferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

    }
}
