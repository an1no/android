package com.example.homework1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.homework1.classes.Message;
import com.example.homework1.classes.Messages;
import com.example.homework1.classes.User;
import com.example.homework1.data.Storage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FragmentA extends Fragment {
    public static String NOTIFICATION = "com.example.homework1.NOTIFICATION";
    public static String NOTIFICATION_DATA = "data";
    private FragmentBMessageCatcherBroadcast fragmentBMessageCatcherBroadcast;
    private EditText editText;
    private String notificationData;
    private User zezva = new User("zezva");
    private Message message;
    private Messages messages;
    Storage storage;
    private ListView listView;
    private MessageArrayAdapter messageArrayAdapter;
    private ArrayList<Message> messageArrayList;
    @Override
    public View onCreateView( final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_fragment_a, container, false);
        editText = view.findViewById(R.id.input);
        listView = view.findViewById(R.id.chat1);
        messageArrayAdapter = new MessageArrayAdapter(getContext(), 0,new ArrayList<Message>());
        storage = new Storage();
        Object ChatStorage = storage.getObject(getContext(), "chat_storage", Messages.class);
        if(ChatStorage!=null){
            messages =(Messages) ChatStorage;
            messageArrayList = messages.getMessages();
            messageArrayAdapter.addAll(messageArrayList);
        } else {
            messages = new Messages();
        }
        listView.setAdapter(messageArrayAdapter);
        view.findViewById(R.id.send1).setOnClickListener(new OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                notificationData = editText.getText().toString();
                message = new Message();
                message.setMessage(notificationData);
                message.setUser(zezva);
                message.setDate(LocalDateTime.now().toString());
                messages.addMessages(message);
                storage.add(getContext(),"chat_storage", messages);
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
        fragmentBMessageCatcherBroadcast = new FragmentBMessageCatcherBroadcast();
        IntentFilter filter = new IntentFilter();
        filter.addAction(FragmentB.NOTIFICATION);
        getActivity().registerReceiver(fragmentBMessageCatcherBroadcast, filter);
    }

    public static class FragmentBMessageCatcherBroadcast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.hasExtra(FragmentB.NOTIFICATION_DATA)) {
                String dataText = intent.getStringExtra(FragmentB.NOTIFICATION_DATA);
                Toast.makeText(context, dataText, Toast.LENGTH_SHORT).show();
            }
        }
    }
    class MessageArrayAdapter extends ArrayAdapter<Message> {
        private Context mContext;

        public MessageArrayAdapter(Context context,
                                   int resource,
                                   List<Message> messages){
            super(context,resource,messages);
            mContext = context;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            final Message message = getItem(position);
            LayoutInflater inflater = (LayoutInflater)mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.view_chat, parent,false);
            TextView textView1 = view.findViewById(R.id.zezva);
            TextView textView2 = view.findViewById(R.id.mzia);
            if(message.getUser().getName().equals("mzia")) {
                textView2.setText(message.getMessage());
            } else {
                textView1.setText(message.getMessage());
            }
            return  view;
        }
    }
}
