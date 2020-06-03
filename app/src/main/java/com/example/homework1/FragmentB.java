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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.homework1.classes.Message;
import com.example.homework1.classes.Messages;
import com.example.homework1.classes.User;
import com.example.homework1.data.Storage;

import org.w3c.dom.Text;

import java.io.Console;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FragmentB extends Fragment {
    public static String NOTIFICATION = "com.example.homework1.NOTIFICATION";
    public static String NOTIFICATION_DATA = "data";
    private FragmentAMessageCatcherBroadcast fragmentAMessageCatcherBroadcast;
    private User mzia = new User("mzia");
    private Message message;
    private Messages messages;
    private EditText editText;
    private ListView listView;
    private static MessageArrayAdapter messageArrayAdapter;
    private ArrayList<Message> messageArrayList;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,   @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.view_fragment_b, container, false);
        listView = view.findViewById(R.id.chat2);
        editText = view.findViewById(R.id.input2);
        messageArrayAdapter = new MessageArrayAdapter(getContext(), 0,new ArrayList<Message>());
        Storage storage = new Storage();
        Object ChatStorage = storage.getObject(getContext(), "chat_storage", Messages.class);
        if(ChatStorage!=null){
            messages =(Messages) ChatStorage;
            messageArrayList = messages.getMessages();
            messageArrayAdapter.addAll(messageArrayList);
        } else {
            messages = new Messages();
        }
        listView.setAdapter(messageArrayAdapter);
        view.findViewById(R.id.send2).setOnClickListener(new OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onClick(View v) {
            String notificationData = editText.getText().toString();
            message = new Message();
            message.setMessage(notificationData);
            message.setUser(mzia);
            message.setDate(LocalDateTime.now().toString());
            Storage storage = new Storage();
            Object ChatStorage = storage.getObject(getContext(), "chat_storage", Messages.class);
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
                ArrayList<Message> messages = new ArrayList<>();
                Message message = new Message();
                message.setMessage(dataText);
                messages.add(message);
                message.setUser(new User("zezva"));
                messageArrayAdapter.addAll(messages);
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
            if(message.getUser().getName().equals("zezva")) {
                textView2.setText(message.getMessage());
            } else {
                textView1.setText(message.getMessage());
            }
            return  view;
        }
    }
}
