package com.example.homework1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void addQuestion(View view) {
        Intent intent = new Intent(this, AddQuestionActivity.class);
        startActivity(intent);
    }

    public void showHistory(View view) {
        Intent intent = new Intent(this, ShowHistoryActivity.class);
        startActivity(intent);
    }

    public void showQuestions(View view) {
        Intent intent = new Intent(this, ShowQuestionsActivity.class);
        startActivity(intent);
    }

}
