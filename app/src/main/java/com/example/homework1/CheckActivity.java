package com.example.homework1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CheckActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers);
        TextView textView = findViewById(R.id.answers);
        Intent intent = getIntent();
        textView.setText(intent.getStringExtra("answers").toString());
    }
}
