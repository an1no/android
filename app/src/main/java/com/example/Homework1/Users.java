package com.example.Homework1;

import java.util.ArrayList;

public class Users {
    private ArrayList<User> users = new ArrayList<>();

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUsers(User user) {
        users.add(user);
    }
    public void setUsers(ArrayList<User> userArrayList) {
        users.addAll(userArrayList);
    }


}