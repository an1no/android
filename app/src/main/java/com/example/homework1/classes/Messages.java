package com.example.homework1.classes;

import java.util.ArrayList;

public class Messages {
    private ArrayList<Message> messages = new ArrayList<>();

    public ArrayList<Message> getMessages() {
        return messages;
    }
    public void addMessages(Message message) {
        messages.add(message);
    }

    public void setMessages(ArrayList<Message> messages) {
        messages.addAll(messages);
    }
}
