package com.example.homework1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.view.View.OnClickListener;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentB extends Fragment {
    public static String NOTIFICATION = "com.example.homework1.NOTIFICATION";
    public static String NOTIFICATION_DATA = "data";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,   @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.view_fragment_b, container, false);
        view.findViewById(R.id.send2).setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
//            Intent intent = new Intent();
//            intent.setAction(NOTIFICATION);
//            intent.putExtra(NOTIFICATION_DATA, "Hello Fragment Someone");
//            getActivity().sendBroadcast(intent);
        }
    });
    return view;
 }

    public void onSentReceived(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }
}
