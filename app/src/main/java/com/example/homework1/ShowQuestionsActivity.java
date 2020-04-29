package com.example.homework1;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homework1.classes.Answer;
import com.example.homework1.classes.Histories;
import com.example.homework1.classes.History;
import com.example.homework1.classes.Question;
import com.example.homework1.classes.Questions;
import com.example.homework1.data.HistoryStorage;
import com.example.homework1.data.Storage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ShowQuestionsActivity extends AppCompatActivity {
    private ArrayList<Answer> answers;
    private ListView mListView;
    private QuestionArrayAdapter questionArrayAdapter;
    private ArrayList<Question> questionsArrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_questions);
        mListView = findViewById(R.id.questions);
        questionArrayAdapter = new QuestionArrayAdapter(this, 0, new ArrayList<Question>());
        mListView.setAdapter(questionArrayAdapter);

        Storage storage = new Storage();
        Object questionsAsObject = storage
          .getObject(this, "question_storage", Questions.class);

        if( questionsAsObject != null) {
            Questions questions = (Questions) questionsAsObject;
            questionsArrayList = questions.getQuestions();
            questionArrayAdapter.addAll(questionsArrayList);
        }
        findViewById(R.id.complete_quiz).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                int count = complete();
                HistoryStorage historyStorage = new HistoryStorage();
                History history = new History();
                history.setScore(count);
                history.setDate(LocalDateTime.now().toString());
                Histories histories;
                Object historyStorageObject = (Histories)historyStorage.getObject(ShowQuestionsActivity.this, "history_storage", Histories.class);
                if(historyStorageObject!= null) {
                    histories = (Histories) historyStorageObject;
                } else {
                    histories = new Histories();
                }
                histories.addHistories(history);
                historyStorage.add(ShowQuestionsActivity.this, "history_storage", histories);
                finish();

            }
        });
    }
    public int complete() {
        int[] radioButtonIds = new int[]{R.id.radio1, R.id.radio2, R.id.radio3, R.id.radio4};
        RadioButton radioButton;
        int count=0;
        for (Question question:questionsArrayList){
            for (int i=0;i<4;i++) {
                radioButton = findViewById(radioButtonIds[i]);
                if(radioButton.isChecked() && question.getAnswers().get(i).isCorrect()) count++;
            }
        }
        return count;
    }

    class QuestionArrayAdapter extends ArrayAdapter<Question> {
        private Context mContext;

        public QuestionArrayAdapter(Context context,
                                    int resource,
                                    List<Question> questions){
            super(context,resource,questions);
            mContext = context;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            final Question question = getItem(position);
            LayoutInflater inflater = (LayoutInflater)mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.view_question, parent,false);
            Button deleteButton = view.findViewById(R.id.delete_button);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Storage storage = new Storage();
                    storage.deleteQuestion(mContext, question.getId());
                }
            });
            TextView textView = view.findViewById(R.id.question_item);
            textView.setText(question.getQuestion());
            TextView textView1;
            int[] textViewIds = new int[]{R.id.ans1, R.id.ans2, R.id.ans3, R.id.ans4};
            ArrayList<Answer> answerArrayList = question.getAnswers();
            int i=0;
                for(Answer answer : answerArrayList) {
                    textView1 = view.findViewById(textViewIds[i]);
                    textView1.setText(answer.getAnswer());
                    i++;
                    if(i==4) break;
                }
            return  view;
        }
    }

}
