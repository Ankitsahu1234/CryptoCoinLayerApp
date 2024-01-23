package com.ankit.cryptocoinlayerapp;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import android.os.SystemClock;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

@RunWith(AndroidJUnit4.class)
public class LandingActivityIntegrationTest {

    @Mock
    private RequestQueue mockRequestQueue;

    @Rule
    public ActivityTestRule<LandingActivity> activityRule =
            new ActivityTestRule<>(LandingActivity.class);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        LandingActivity landingActivity = activityRule.getActivity();
        landingActivity.requestQueue = mockRequestQueue;
    }

    @Test
    public void testApiIntegration() {
        // Arrange
        LandingActivity landingActivity = activityRule.getActivity();
        ApiResponseForLive mockApiResponse = new ApiResponseForLive();
        Map<String, Double> mockRates = new HashMap<>();
        mockRates.put("BTC", 50000.0);
        mockApiResponse.setRates(mockRates);

        when(mockRequestQueue.add(any(JsonObjectRequest.class))).then(invocation -> {
            Response.Listener<JSONObject> listener = invocation.getArgument(2);
            listener.onResponse(new JSONObject(new Gson().toJson(mockApiResponse)));
            return null;
        });

        // Act
        Espresso.onView(ViewMatchers.withId(R.id.swipeRefreshLayout)).perform(ViewActions.swipeDown());

        // Assert
        Espresso.onView(ViewMatchers.withId(R.id.tvExchangeRate))
                .check(ViewAssertions.matches(ViewMatchers.withText("50000.0")));
    }

    @Test
    public void testRecyclerViewIntegration() {
        // Arrange
        LandingActivity landingActivity = activityRule.getActivity();
        ApiResponseForList mockApiResponse = new ApiResponseForList();
        Map<String, CryptoDetails> mockCryptoMap = new HashMap<>();
        mockCryptoMap.put("BTC", new CryptoDetails("BTC", "Bitcoin", "Bitcoin", 21000000, "bitcoin.png"));
        mockApiResponse.setCrypto(mockCryptoMap);

        when(mockRequestQueue.add(any(JsonObjectRequest.class))).then(invocation -> {
            Response.Listener<JSONObject> listener = invocation.getArgument(2);
            listener.onResponse(new JSONObject(new Gson().toJson(mockApiResponse)));
            return null;
        });

        // Act
        // Wait for the RecyclerView to load data
        SystemClock.sleep(3000);

        // Assert
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
                .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText("Bitcoin"))));
    }
}
