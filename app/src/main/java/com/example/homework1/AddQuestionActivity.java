package com.example.homework1;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.homework1.classes.Answer;
import com.example.homework1.classes.Question;
import com.example.homework1.classes.Questions;
import com.example.homework1.data.Storage;

import java.util.ArrayList;
import java.util.UUID;

public class AddQuestionActivity extends AppCompatActivity {
    EditText mQuestion;
    RadioGroup mRadioGroup;
    RadioGroup radiogroup;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        mQuestion = findViewById(R.id.question);
    }

    public void addQuestionInStorage(View view) {
        Questions questions;
        Question question = new Question();
        Storage storage = new Storage();
        Object QuestionsStorage = storage.getObject(this, "question_storage", Questions.class);
        if(QuestionsStorage != null ){
            questions = (Questions) QuestionsStorage;
        }
        else {
            questions = new Questions();
        }

        radiogroup = (RadioGroup) findViewById(R.id.radio_group);
        int selectedId = radiogroup.getCheckedRadioButtonId();
        ArrayList<Answer> answerArrayList = new ArrayList<>();
        TextView textView;
        int[] textViewIDs = new int[]{R.id.answer1, R.id.answer2, R.id.answer3, R.id.answer4};
        for(int i = 1;i <= 4; i++) {
            textView = findViewById(textViewIDs[i-1]);
            Answer answer = new Answer();
            answer.setAnswer(textView.getText().toString());
            answer.setCorrect(selectedId==i);
            answerArrayList.add(answer);
        }
        question.setQuestion(mQuestion.getText().toString());
        question.setId(UUID.randomUUID().toString());
        question.setAnswers(answerArrayList);
        questions.addQuestions(question);
        storage.add(this, "question_storage", questions);
        finish();
    }
}
