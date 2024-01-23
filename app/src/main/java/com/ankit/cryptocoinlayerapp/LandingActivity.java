package com.ankit.cryptocoinlayerapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class LandingActivity extends AppCompatActivity {

    private ArrayList<CryptoDetails> coinDetailsList = new ArrayList<>();
    private Map<String, Double> coinRate = Collections.emptyMap();
    private ArrayList<CryptoRequiredDetailsList> cryptoRequiredDetailsList = new ArrayList<>();
    private Timer timer;
    private final Handler handler = new Handler(Looper.getMainLooper());
    protected RequestQueue requestQueue;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeToRefresh;
    private long lastApiCallTimestamp = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        requestQueue = Volley.newRequestQueue(this);
        recyclerView = findViewById(R.id.recyclerView);
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                long currentTime = System.currentTimeMillis();
                String elapsedTime = calculateElapsedTime(currentTime);
                lastApiCallTimestamp = currentTime;

                getCoinLive();
                getCoinList();
                handler.post(() ->
                        Toast.makeText(LandingActivity.this, "Last Refreshed " + elapsedTime, Toast.LENGTH_SHORT).show());
            }
        }, 0, 180000);

        swipeToRefresh = findViewById(R.id.swipeRefreshLayout);
        swipeToRefresh.setOnRefreshListener(() -> {
            long currentTime = System.currentTimeMillis();
            String elapsedTime = calculateElapsedTime(currentTime);
            lastApiCallTimestamp = currentTime;
            cryptoRequiredDetailsList.clear();
            coinDetailsList.clear();
            coinRate = Collections.emptyMap();
            getCoinLive();
            getCoinList();
            handler.post(() ->
                    Toast.makeText(LandingActivity.this, "Last Refreshed " + elapsedTime, Toast.LENGTH_SHORT).show());
        });
    }

    private String calculateElapsedTime(long currentTime) {
        long elapsedMillis = currentTime - lastApiCallTimestamp;
        long elapsedSeconds = elapsedMillis / 1000;

        if (elapsedSeconds < 60) {
            return elapsedSeconds + " seconds ago";
        } else {
            long elapsedMinutes = elapsedSeconds / 60;
            return elapsedMinutes + " minutes ago";
        }
    }

    private void getCoinList() {
        String url = "http://api.coinlayer.com/list?access_key=e27bef1cc48c9f5a12224346f968fc77";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Map<String, CryptoDetails> data = parseCoinList(response).getCrypto();
                        if (data != null) {
                            coinDetailsList.addAll(data.values());

                            LinearLayoutManager layoutManager = new LinearLayoutManager(LandingActivity.this);
                            recyclerView.setLayoutManager(layoutManager);

                            for (CryptoDetails coinList : coinDetailsList) {
                                for (Map.Entry<String, Double> rate : coinRate.entrySet()) {
                                    if (coinList.getSymbol().equals(rate.getKey())) {
                                        CryptoRequiredDetailsList data1 = new CryptoRequiredDetailsList(
                                                coinList.getFullName(),
                                                rate.getValue(),
                                                coinList.getIconUrl()
                                        );
                                        cryptoRequiredDetailsList.add(data1);
                                    }
                                }
                            }

                            Collections.sort(cryptoRequiredDetailsList, (o1, o2) -> o1.getName().compareTo(o2.getName()));
                            recyclerView.setAdapter(new CoinLayerAdapter(cryptoRequiredDetailsList));
                            swipeToRefresh.setRefreshing(false);
                        }
                        else{
                            swipeToRefresh.setRefreshing(false);
                            Toast.makeText(LandingActivity.this, "Data not found!", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        swipeToRefresh.setRefreshing(false);
                        Log.e("volleyError", String.valueOf(error));
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }

    private void getCoinLive() {
        String url = "http://api.coinlayer.com/live?access_key=19119eb2967cce12f18f6e1f7851848d";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ApiResponseForLive data = parseCoinLive(response);
                        if (data != null) {
                            coinRate = data.getRates();
                            Log.e("DEOvolleyError", String.valueOf(coinRate));
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        swipeToRefresh.setRefreshing(false);
                        Log.e("volleyError", String.valueOf(error));
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }

    private ApiResponseForList parseCoinList(JSONObject res) {
        try {
            Gson gson = new Gson();
            return gson.fromJson(res.toString(), ApiResponseForList.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private ApiResponseForLive parseCoinLive(JSONObject res) {
        try {
            Gson gson = new Gson();
            return gson.fromJson(res.toString(), ApiResponseForLive.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
