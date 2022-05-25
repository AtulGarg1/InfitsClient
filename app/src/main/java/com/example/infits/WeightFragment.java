package com.example.infits;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeightFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public WeightFragment() {
        // Required empty public constructor
    }
    public static WeightFragment newInstance(String param1, String param2) {
        WeightFragment fragment = new WeightFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weight, container, false);
        RadioButton week_radioButton = view.findViewById(R.id.week_btn_weight);
        RadioButton month_radioButton = view.findViewById(R.id.month_btn_weight);
        RadioButton year_radioButton = view.findViewById(R.id.year_btn_weight);
        RadioButton custom_radioButton = view.findViewById(R.id.customdates_btn_weight);
        week_radioButton.setOnClickListener(v->{
            final GraphView graph = (GraphView) view.findViewById(R.id.graph);
            String url = "http://192.168.124.91/infits/weightGraph.php";
            String from = "";
            String to = "";
            SimpleDateFormat fromTo = new SimpleDateFormat("yyyy-MM-dd");
            String [] days = new String[7];
            float[] dataPoints= new float[7];
            StringRequest stringRequest = new StringRequest(Request.Method.GET,url,response -> {
                graph.removeAllSeries();
                List<String> allNames = new ArrayList<String>();
                JSONObject jsonResponse = null;
                try {
                    jsonResponse = new JSONObject(response);
                    JSONArray cast = jsonResponse.getJSONArray("steps");

                    for (int i=0; i<cast.length(); i++) {
                        JSONObject actor = cast.getJSONObject(i);
                        String name = actor.getString("steps");
                        String date = actor.getString("date");
                        allNames.add(name);
                        Log.d("Length", allNames.get(i));
                        days[i] = date;
                        dataPoints[i] = Float.parseFloat(allNames.get(i))/1000;
                        Log.d("Length", String.valueOf(dataPoints[i]));
                        Log.d("Length",days[i]);
                    }
                    DataPoint[] values = new DataPoint[dataPoints.length];
                    for(int i =0; i<dataPoints.length; i++){
                        if (i==0){
                            DataPoint val = new DataPoint(0, 0);
                            values[i] = val;
                        }
                        else if (i == 6){
                            DataPoint val = new DataPoint(i+1, dataPoints[i]);
                            values[i] = val;
                        }
                        else{
                            DataPoint val = new DataPoint(i, dataPoints[i]);
                            values[i] = val;
                        }
                    }
                    LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(values);
                    StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
                    staticLabelsFormatter.setHorizontalLabels(days);
                    staticLabelsFormatter.setVerticalLabels(new String[] {"0", "1000", "2000", "3000","4000","5000","6000","7000","8000","9000","10000"});
                    graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
                    graph.getGridLabelRenderer().setNumHorizontalLabels(7);
                    graph.getViewport().setMinX(1);
                    graph.getViewport().setMaxX(7);
                    graph.getViewport().setXAxisBoundsManual(true);
                    series.setDrawDataPoints(true);
                    series.setDataPointsRadius(10);
                    graph.addSeries(series);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            },error -> {
                Log.d("Data",error.toString().trim());
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> data = new HashMap<>();

                    data.put("option","Month");

                    return data;
                }
            };
            Volley.newRequestQueue(getActivity()).add(stringRequest);
        });
        month_radioButton.setOnClickListener(v->{
            final GraphView graphMonth = (GraphView) view.findViewById(R.id.graph);
            graphMonth.removeAllSeries();
            String url = "http://192.168.124.91/infits/stepMonthGraph.php";
            String from = "";
            String to = "";
            SimpleDateFormat fromTo = new SimpleDateFormat("yyyy-MM-dd");
            String [] days = new String[31];
            float[] dataPoints= new float[31];
            StringRequest stringRequest = new StringRequest(Request.Method.GET,url,response -> {
                List<String> allNames = new ArrayList<>();
                JSONObject jsonResponse;
                try {
                    jsonResponse = new JSONObject(response);
                    JSONArray cast = jsonResponse.getJSONArray("steps");
                    for (int i=0; i<cast.length(); i++) {
                        JSONObject actor = cast.getJSONObject(i);
                        String name = actor.getString("steps");
                        String date = actor.getString("date");
                        allNames.add(name);
                        days[i] = date;
                        dataPoints[i] = Float.parseFloat(allNames.get(i))/1000;
                    }

                    DataPoint[] values = new DataPoint[dataPoints.length];
                    for(int i =0; i<dataPoints.length; i++){
                        DataPoint val = new DataPoint(i, dataPoints[i]);
                        values[i] = val;
                    }

                    LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(values);
                    StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graphMonth);
                    staticLabelsFormatter.setHorizontalLabels(days);
                    staticLabelsFormatter.setVerticalLabels(new String[] {"0", "1000", "2000", "3000","4000","5000","6000","7000","8000","9000","10000"});
                    graphMonth.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
                    graphMonth.getGridLabelRenderer().setNumHorizontalLabels(7);
                    graphMonth.getViewport().setMinX(1);
                    graphMonth.getViewport().setMaxX(31);
                    graphMonth.getViewport().setXAxisBoundsManual(true);

                    Log.d("length", String.valueOf(dataPoints.length));
                    series.setDrawDataPoints(true);
                    series.setDataPointsRadius(7);
                    graphMonth.addSeries(series);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            },error -> {
                Log.d("Data",error.toString().trim());
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> data = new HashMap<>();

                    data.put("option","Week");

                    return data;
                }
            };
            Volley.newRequestQueue(getActivity()).add(stringRequest);
        });
        year_radioButton.setOnClickListener(v->{
            final GraphView graphMonth = (GraphView) view.findViewById(R.id.graph);
            graphMonth.removeAllSeries();
            String url = "http://192.168.124.91/infits/stepYearGraph.php";
            String from = "";
            String to = "";
            SimpleDateFormat fromTo = new SimpleDateFormat("yyyy-MM-dd");
            String [] days = new String[12];
            float[] dataPoints= new float[12];
            StringRequest stringRequest = new StringRequest(Request.Method.GET,url,response -> {
                List<String> allNames = new ArrayList<String>();
                try {
                    JSONArray cast = new JSONArray(response);
                    for (int i=0; i<cast.length(); i++) {
                        JSONObject actor = cast.getJSONObject(i);
                        String name = actor.getString("av");
                        allNames.add(name);
                        dataPoints[i] = Float.parseFloat(allNames.get(i))/1000;
                    }

                    DataPoint[] values = new DataPoint[dataPoints.length];
                    for(int i =0; i<dataPoints.length; i++){
                        if (i==0){
                            DataPoint val = new DataPoint(0,0);
                            values[i] = val;
                        }
                        if (i==11){
                            DataPoint val = new DataPoint(i+1 , dataPoints[i]);
                            values[i] = val;
                        }
                        else {
                            DataPoint val = new DataPoint(i , dataPoints[i]);
                            Log.d("Step", String.valueOf(dataPoints.length));
                            values[i] = val;
                            Log.d("vals", String.valueOf(val));
                        }
                    }

                    LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(values);
                    StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graphMonth);
                    staticLabelsFormatter.setHorizontalLabels(new String[]{"0","1","2","3","4","5","6","7","8","9","10","11","12"});
                    staticLabelsFormatter.setVerticalLabels(new String[] {"0", "1000", "2000", "3000","4000","5000","6000","7000","8000","9000","10000"});
                    graphMonth.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

                    graphMonth.getGridLabelRenderer().setNumHorizontalLabels(6);
                    graphMonth.getViewport().setMinX(0);
                    graphMonth.getViewport().setMaxX(12);
                    graphMonth.getViewport().setXAxisBoundsManual(true);

                    Log.d("length", String.valueOf(dataPoints.length));
                    series.setDrawDataPoints(true);
                    series.setDataPointsRadius(7);
                    graphMonth.addSeries(series);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            },error -> {
                Log.d("Data",error.toString().trim());
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> data = new HashMap<>();

                    data.put("option","Week");

                    return data;
                }
            };
            Volley.newRequestQueue(getActivity()).add(stringRequest);
        });
        custom_radioButton.setOnClickListener(v->{
            final GraphView graph = (GraphView) view.findViewById(R.id.graph);
            String url = "http://192.168.124.91/infits/stepsGraph.php";
            String from = "2022-09-10";
            String to = "2022-09-17";
            SimpleDateFormat fromTo = new SimpleDateFormat("yyyy-MM-dd");
            String [] days = new String[7];
            float[] dataPoints= new float[7];
            StringRequest stringRequest = new StringRequest(Request.Method.GET,url,response -> {
                graph.removeAllSeries();
                List<String> allNames = new ArrayList<String>();
                JSONObject jsonResponse = null;
                try {
                    jsonResponse = new JSONObject(response);
                    JSONArray cast = jsonResponse.getJSONArray("steps");

                    for (int i=0; i<cast.length(); i++) {
                        JSONObject actor = cast.getJSONObject(i);
                        String name = actor.getString("steps");
                        String date = actor.getString("date");
                        allNames.add(name);
                        Log.d("Length", allNames.get(i));
                        days[i] = date;
                        dataPoints[i] = Float.parseFloat(allNames.get(i))/1000;
                        Log.d("Length", String.valueOf(dataPoints[i]));
                        Log.d("Length",days[i]);
                    }
                    DataPoint[] values = new DataPoint[dataPoints.length];
                    for(int i =0; i<dataPoints.length; i++){
                        if (i==0){
                            DataPoint val = new DataPoint(0, 0);
                            values[i] = val;
                        }
                        else if (i == 6){
                            DataPoint val = new DataPoint(i+1, dataPoints[i]);
                            values[i] = val;
                        }
                        else{
                            DataPoint val = new DataPoint(i, dataPoints[i]);
                            values[i] = val;
                        }
                    }
                    LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(values);
                    StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
                    staticLabelsFormatter.setHorizontalLabels(days);
                    staticLabelsFormatter.setVerticalLabels(new String[] {"0", "1000", "2000", "3000","4000","5000","6000","7000","8000","9000","10000"});
                    graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
                    graph.getGridLabelRenderer().setNumHorizontalLabels(7);
                    graph.getViewport().setMinX(1);
                    graph.getViewport().setMaxX(7);
                    graph.getViewport().setXAxisBoundsManual(true);
                    series.setDrawDataPoints(true);
                    series.setDataPointsRadius(10);
                    graph.addSeries(series);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            },error -> {
                Log.d("Data",error.toString().trim());
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> data = new HashMap<>();
                    data.put("from",from);
                    data.put("to",to);
                    return data;
                }
            };
            Volley.newRequestQueue(getActivity()).add(stringRequest);
        });
        return view;
    }
}