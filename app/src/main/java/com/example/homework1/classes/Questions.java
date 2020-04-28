package com.example.homework1.classes;

import java.util.ArrayList;

public class Questions {
    private ArrayList<Question> questions = new ArrayList<>();

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Question question) {
        questions.add(question);
    }
}
