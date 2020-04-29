package com.example.homework1.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.homework1.classes.Question;
import com.example.homework1.classes.Questions;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Iterator;

public class Storage{

    private  SharedPreferences getInstance(Context context) {
        return context.getSharedPreferences("question_storage", Context.MODE_PRIVATE);
    }

    public void add(Context context, String key, Object object) {
        SharedPreferences sharedPreferences = getInstance(context);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString(key, new Gson().toJson(object));
        editor.apply();
    }

    public Object getObject(Context context, String key, Class mClass) {
        SharedPreferences sharedPreferences = getInstance(context);
        String data = sharedPreferences.getString(key, null);
        return data == null? null : new Gson().fromJson(data, mClass);
    }

    public String getString(Context context, String key) {
        SharedPreferences sharedPreferences = getInstance(context);
        return sharedPreferences.getString(key, null);
    }

    public void deleteQuestion(Context context, String key) {
        Object questionsAsObject = getObject(context, "question_storage", Questions.class);
        Questions questions = (Questions) questionsAsObject;
        ArrayList<Question> questionArrayList = questions.getQuestions();
        Iterator<Question> questionIterator = questionArrayList.iterator();
        while (questionIterator.hasNext()) {
            Question question = questionIterator.next();
            if(question.getId()!= null && question.getId().equals(key)) {
                questionIterator.remove();
                break;
            }
        }
        questions.setQuestions(questionArrayList);
        add(context,"question_storage",questions);
    }
}
