package com.example.homework1.services;
import android.os.AsyncTask;
import java.io.StringReader;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetTTCDataAsyncTask extends AsyncTask<Void, Void, ArrayList<BusRoute>> {

    private Callback callback;

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected ArrayList<BusRoute> doInBackground(Void... voids) {
        //   protected ArrayList<BusStop> doInBackground(Void... voids) {
//        ArrayList<BusStop> arrayList = new ArrayList<>();
//        try {
//            Document doc = Jsoup.connect("http://transfer.ttc.com.ge/?page=schedule&setLng=ka").get();
//            Elements elements = doc.getElementsByClass("route-row");
//            for (int i = 0; i < elements.size(); i++) {
//                Element element = elements.get(i);
//                Elements busNumbers = element.getElementsByClass("route-number-circle");
//                BusStop busStop = new BusStop();
//                if (busNumbers.size() > 0) {
//                    String busNumber = busNumbers.get(0).text();
//                    busStop.setNumber(busNumber);
//                }
//                Elements endStops = element.getElementsByAttribute("data-forward");
//                if(endStops.size()>0) {
//                    String endStop1 = endStops.get(0).text();
//                    String endStop2 = endStops.get(1).text();
//                    busStop.setEndStop1(endStop1);
//                    busStop.setEndStop2(endStop2);
//                }
//                arrayList.add(busStop);
//            }
//        } catch (IOException e) {
//            Log.d("MainActivity", e.getMessage());
//            e.printStackTrace();
//        }
//        return arrayList;
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://transfer.ttc.com.ge:8080/otp/routers/ttc/routes")
                    .build();
            Response response = client.newCall(request).execute();
            String jsonData = response.body().string();
            Document document = loadXMLFromString(jsonData);
            NodeList nodes = document.getElementsByTagName("Route");
            ArrayList<BusRoute> busRouteArrayList = new ArrayList<>();
            for(int i=0; i< nodes.getLength();i++) {
                Element element = (Element) nodes.item(i);
                String type = element.getElementsByTagName("Type").item(0).getTextContent();
                if(type.equals("bus")) {
                    String routeNumber = element.getElementsByTagName("RouteNumber").item(0).getTextContent();
                    BusRoute busRoute = new BusRoute();
                    busRoute.setRouteNumber(routeNumber);
                    busRouteArrayList.add(busRoute);
                }
            }
            return busRouteArrayList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();

    }

    protected void onPostExecute(ArrayList<BusRoute> busRoutes) {
        if (callback != null) {
            callback.onDataReceived(busRoutes);
        }
    }

    public interface Callback {

        void onDataReceived(ArrayList<BusRoute> busRoutes);
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

