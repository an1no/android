package com.example.homework1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentB extends Fragment {
    public static String NOTIFICATION = "com.example.homework1.NOTIFICATION";
    public static String NOTIFICATION_DATA = "data";
    private FragmentAMessageCatcherBroadcast fragmentAMessageCatcherBroadcast;

    private EditText editText;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,   @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.view_fragment_b, container, false);
        editText = view.findViewById(R.id.input);
        view.findViewById(R.id.send2).setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
            String notificationData = editText.getText().toString();
            Intent intent = new Intent();
            intent.setAction(NOTIFICATION);
            intent.putExtra(NOTIFICATION_DATA, notificationData);
            getActivity().sendBroadcast(intent);
            editText.getText().clear();
        }
    });
    initBroadcast();
    return view;
 }

    void initBroadcast() {
        fragmentAMessageCatcherBroadcast = new FragmentAMessageCatcherBroadcast();
        IntentFilter filter = new IntentFilter();
        filter.addAction(NOTIFICATION);
        getActivity().registerReceiver(fragmentAMessageCatcherBroadcast, filter);
    }

    public static class FragmentAMessageCatcherBroadcast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.hasExtra(NOTIFICATION_DATA)) {
                String dataText = intent.getStringExtra(NOTIFICATION_DATA);
                Toast.makeText(context, dataText, Toast.LENGTH_SHORT).show();

            }
        }
    }
}
