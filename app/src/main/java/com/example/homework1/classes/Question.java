package com.example.homework1.classes;

import java.util.ArrayList;

public class Question{
    private String question;
    private ArrayList<Answer> answers;

    public Question() {

    }

    public Question(String question, ArrayList<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }
}
