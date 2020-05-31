package com.example.homework1.services;

import android.os.AsyncTask;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GetTTCDataBusStopsAsyncTask extends AsyncTask<Void, Void, ArrayList<RouteStop>> {

    private Callback callback;

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected ArrayList<RouteStop> doInBackground(Void... voids) {
        try {
            OkHttpClient client = new OkHttpClient();
            RequestBody formBody = new MultipartBody.Builder().addFormDataPart("routeNumber","1").build();
            Request request = new Request.Builder()
                    .url("http://transfer.ttc.com.ge:8080/otp/routers/ttc/routeInfo?type=bus&routeNumber=1")
                    .build();
            Response response = client.newCall(request).execute();
            String jsonData = response.body().string();
            Document document = loadXMLFromString(jsonData);
            NodeList nodes = document.getElementsByTagName("RouteStops");
            ArrayList<RouteStop> routeStopArrayList = new ArrayList<>();
            for(int i=0; i< nodes.getLength();i++) {
                Element element = (Element) nodes.item(i);
                String routeName = element.getElementsByTagName("Name").item(0).getTextContent();
                String stopId = element.getElementsByTagName("StopId").item(0).getTextContent();
                RouteStop routeStop = new RouteStop();
                routeStop.setRouteNumber(routeName);
                routeStop.setStopID(stopId);
                routeStopArrayList.add(routeStop);
            }
            return routeStopArrayList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();

    }

    protected void onPostExecute(ArrayList<RouteStop> routeStops) {
        if (callback != null) {
            callback.onDataReceived(routeStops);
        }
    }

    public interface Callback {

        void onDataReceived(ArrayList<RouteStop> routeStops);
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }
    public static Document loadXMLFromString(String xml) throws Exception
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        return builder.parse(is);
    }
}

