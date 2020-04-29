package com.example.homework1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homework1.classes.Histories;
import com.example.homework1.classes.History;
import com.example.homework1.data.HistoryStorage;

import java.util.ArrayList;
import java.util.List;

public class ShowHistoryActivity extends AppCompatActivity {
    HistoryArrayAdapter historyArrayAdapter;
    ArrayList<History> historyArrayList;
    ListView mListView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_history);
        mListView = findViewById(R.id.history);
        historyArrayAdapter = new HistoryArrayAdapter(this, 0, new ArrayList<History>());
        mListView.setAdapter(historyArrayAdapter);

        HistoryStorage historyStorage = new HistoryStorage();
        Object history_storage = historyStorage
                .getObject(this, "history_storage", Histories.class);
        if( history_storage != null) {
            Histories histories = (Histories) history_storage;
            historyArrayList = histories.getHistories();
            historyArrayAdapter.addAll(historyArrayList);

        }
    }
    class HistoryArrayAdapter extends ArrayAdapter<History> {
        private Context mContext;

        public HistoryArrayAdapter(Context context,
                                    int resource,
                                    List<History> histories){
            super(context,resource, histories);
            mContext = context;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            final History history = getItem(position);
            LayoutInflater inflater = (LayoutInflater)mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.show_history_item, parent,false);
            TextView textView = view.findViewById(R.id.score);
            textView.setText(String.valueOf(history.getScore()));
            TextView textView1 = view.findViewById(R.id.time);
            textView1.setText(history.getDate());
            return  view;
        }
    }


}
