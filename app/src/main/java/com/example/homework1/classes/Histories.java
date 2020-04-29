package com.example.homework1.classes;

import java.util.ArrayList;

public class Histories {
    private ArrayList<History> histories = new ArrayList<>();

    public ArrayList<History> getHistories() {
        return histories;
    }

    public void addHistories(History history) {
        histories.add(history);
    }
    public void setHistories(ArrayList<History> historyArrayList) {
        histories.addAll(historyArrayList);
    }
}
