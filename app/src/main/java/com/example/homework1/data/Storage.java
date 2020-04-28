package com.example.homework1.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

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
    public void deleteObject(Context context, Object key){
        SharedPreferences sharedPreferences = getInstance(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(new Gson().toJson(key));
        editor.commit();
    }
}
