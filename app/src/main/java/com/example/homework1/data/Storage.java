package com.example.homework1.data;

import android.content.Context;
import android.content.SharedPreferences;


public class Storage {

    private SharedPreferences getInstance(Context context) {
        return context.getSharedPreferences("chat_storage",Context.MODE_PRIVATE);
    }

    public void add(Context context, String key, String value) {
        SharedPreferences sharedPreferences = getInstance(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public Object getObject(Context context, String key) {
        SharedPreferences sharedPreferences = getInstance(context);
        String data = sharedPreferences.getString(key, null);
        return data;
    }
}