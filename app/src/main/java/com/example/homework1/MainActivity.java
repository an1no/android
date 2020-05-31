package com.example.homework1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homework1.services.BusRoute;
import com.example.homework1.services.BusStop;
import com.example.homework1.services.GetTTCDataAsyncTask;
import com.example.homework1.services.GetTTCDataAsyncTask.Callback;
import com.example.homework1.services.GetTTCDataBusStopsAsyncTask;
import com.example.homework1.services.RouteStop;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Route;


public class MainActivity extends AppCompatActivity {
    private ListView mBusRoutes;
    private BusRouteArrayAdapter busRouteArrayAdapter;
    private RouteStopArrayAdapter routeStopArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBusRoutes = findViewById(R.id.busRoutes);
        busRouteArrayAdapter = new BusRouteArrayAdapter(this, 0, new ArrayList<BusRoute>());
        mBusRoutes.setAdapter(busRouteArrayAdapter);
        routeStopArrayAdapter = new RouteStopArrayAdapter(this, 0, new ArrayList<RouteStop>());
        mBusRoutes.setAdapter(routeStopArrayAdapter);
    }
    public void getBusRoutes(View view) {
        GetTTCDataAsyncTask getTTCDataAsyncTask = new GetTTCDataAsyncTask();
        GetTTCDataAsyncTask.Callback callback = new GetTTCDataAsyncTask.Callback() {
            @Override
            public void onDataReceived(ArrayList<BusRoute> busRoutes) {
                mBusRoutes.setAdapter(busRouteArrayAdapter);
                busRouteArrayAdapter.addAll(busRoutes);
            }
        };
        getTTCDataAsyncTask.setCallback(callback);
        getTTCDataAsyncTask.execute();
    }


    class BusRouteArrayAdapter extends ArrayAdapter<BusRoute> {

        private Context context;

        public BusRouteArrayAdapter(Context context, int resource, List<BusRoute> objects) {
            super(context, resource, objects);
            this.context = context;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.view_bus_route_item, parent, false);
            BusRoute busRoute = getItem(position);
            TextView textView = view.findViewById(R.id.busRouteNumber);
            textView.setText(busRoute.getRouteNumber());
            return view;
        }
    }

    public void getRouteStops(View view) {
        GetTTCDataBusStopsAsyncTask getTTCDataBusStopsAsyncTask = new GetTTCDataBusStopsAsyncTask();
        GetTTCDataBusStopsAsyncTask.Callback callback = new GetTTCDataBusStopsAsyncTask.Callback() {
            public void onDataReceived(ArrayList<RouteStop> routeStops) {
                mBusRoutes.setAdapter(routeStopArrayAdapter);
                routeStopArrayAdapter.addAll(routeStops);
            }
        };
        getTTCDataBusStopsAsyncTask.setCallback(callback);
        getTTCDataBusStopsAsyncTask.execute();
    }

    class RouteStopArrayAdapter extends ArrayAdapter<RouteStop> {

        private Context context;

        public RouteStopArrayAdapter(Context context, int resource, List<RouteStop> objects) {
            super(context, resource, objects);
            this.context = context;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.view_route_stop_item, parent, false);
            RouteStop routeStop = getItem(position);
            TextView textView = view.findViewById(R.id.routeStop);
            textView.setText("awdawdhaw");
            return view;
        }
    }
}
