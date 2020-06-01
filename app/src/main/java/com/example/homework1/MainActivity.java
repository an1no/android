package com.example.homework1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {
    private FragmentA fragmentA;
    private FragmentB fragmentB;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentA = (FragmentA) getSupportFragmentManager().findFragmentById(R.id.fa);
        fragmentB = (FragmentB) getSupportFragmentManager().findFragmentById(R.id.fb);
    }


}
