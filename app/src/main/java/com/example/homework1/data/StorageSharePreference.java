package com.example.homework1.data;

import android.content.Context;
import android.content.SharedPreferences;

public class StorageSharePreference implements Storage {
    private static final String FILE_NAME = "correct_answers_info";

    private SharedPreferences getSharedPreferences(Context context){
        return context.getSharedPreferences(FILE_NAME, context.MODE_PRIVATE);
    }

    @Override
    public void write(Context context, String key, String value) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    @Override
    public String read(Context context, String key) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        return sharedPreferences.getString(key, null);
    }

    @Override
    public boolean exists(Context context, String key) {
        return read(context, key) != null;
    }
}