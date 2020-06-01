package com.example.homework1.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;


public class Storage {

    private SharedPreferences getInstance(Context context) {
        return context.getSharedPreferences("chat_storage", Context.MODE_PRIVATE);
    }

    public void add(Context context, String key, Object object) {
        SharedPreferences sharedPreferences = getInstance(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, new Gson().toJson(object));
        editor.apply();
    }

    public Object getObject(Context context, String key, Class mClass) {
        SharedPreferences sharedPreferences = getInstance(context);
        String data = sharedPreferences.getString(key, null);
        return data== null? null : new Gson().fromJson(data, mClass);
    }
}