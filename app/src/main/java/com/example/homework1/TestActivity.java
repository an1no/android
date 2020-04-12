package com.example.homework1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.homework1.data.StorageSharePreference;

public class TestActivity extends AppCompatActivity {
    private int correctAnswers = 0;
    private StorageSharePreference mSharePreferenceManager;
    private TextView lastScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        lastScore = findViewById(R.id.last_score);
        mSharePreferenceManager = new StorageSharePreference();

        if(mSharePreferenceManager.exists(this, "last_score")){
            lastScore.setText(mSharePreferenceManager.read(this, "last_score"));
        }
        else {
            lastScore.setText("Never played before");
        }

        Button checkAnswers = findViewById(R.id.check_answers);
        checkAnswers.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Submit(TestActivity.this);
                mSharePreferenceManager.write(TestActivity.this, "last_score", String.valueOf(correctAnswers));
                Intent intent = new Intent(TestActivity.this, CheckActivity.class);
                intent.putExtra("answers", String.valueOf(correctAnswers));
                startActivity(intent);
            }
        });
    }
    private void Submit(TestActivity view) {
        int chk1 = ((RadioGroup)findViewById(R.id.first)).getCheckedRadioButtonId();
        int chk2 = ((RadioGroup)findViewById(R.id.second)).getCheckedRadioButtonId();
        int chk3 = ((RadioGroup)findViewById(R.id.third)).getCheckedRadioButtonId();
        int chk4 = ((RadioGroup)findViewById(R.id.fourth)).getCheckedRadioButtonId();

        if(chk1 == R.id.google_launched) correctAnswers++;
        if(chk2 == R.id.simpsons_number) correctAnswers++;
        if(chk3 == R.id.known_quarryman) correctAnswers++;
        if(chk4 == R.id.metallica_drummer) correctAnswers++;
    }
}
