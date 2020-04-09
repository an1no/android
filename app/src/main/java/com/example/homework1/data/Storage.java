package com.example.homework1.data;

import android.content.Context;

public interface Storage {
    void write(Context context, String key, String value);
    String read(Context context, String key);
    boolean exists(Context context, String key);
}
