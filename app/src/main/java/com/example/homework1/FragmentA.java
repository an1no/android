package com.example.homework1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.view.View.OnClickListener;


import androidx.fragment.app.Fragment;

public class FragmentA extends Fragment {
    private Callback mCallback;
    //private FragmentBMessageCatcherBroadcast fragmentBMessageCatcherBroadcast;
    private EditText editText;
    private String notificationData;
    @Override
    public View onCreateView( final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_fragment_a, container, false);
        editText = view.findViewById(R.id.input);
        view.findViewById(R.id.send1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationData = editText.getText().toString();
                if (mCallback != null) {
                    mCallback.onSent(notificationData);
                }
                editText.getText().clear();
            }
        });
       // initBroadcast();
        return view;
    }
//    void initBroadcast() {
//        fragmentBMessageCatcherBroadcast = new FragmentBMessageCatcherBroadcast();
//        IntentFilter filter = new IntentFilter();
//        filter.addAction(FragmentB.NOTIFICATION);
//        getActivity().registerReceiver(fragmentBMessageCatcherBroadcast, filter);
//    }

    public interface Callback {

        void onSent(String text);
    }
    public void setCallback(Callback mCallback) {
        this.mCallback = mCallback;
    }
//    public static class FragmentBMessageCatcherBroadcast extends BroadcastReceiver {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            if (intent.hasExtra(FragmentB.NOTIFICATION_DATA)) {
//                String dataText = intent.getStringExtra(FragmentB.NOTIFICATION_DATA);
//            }
//        }
//    }
}
