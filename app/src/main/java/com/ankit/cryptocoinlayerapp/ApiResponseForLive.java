package com.ankit.cryptocoinlayerapp;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class ApiResponseForLive {

    @SerializedName("success")
    private boolean success;

    @SerializedName("terms")
    private String terms;

    @SerializedName("privacy")
    private String privacy;

    @SerializedName("timestamp")
    private long timestamp;

    @SerializedName("target")
    private String target;

    @SerializedName("rates")
    private Map<String, Double> rates;

    public ApiResponseForLive(boolean success, String terms, String privacy, long timestamp, String target, Map<String, Double> rates) {
        this.success = success;
        this.terms = terms;
        this.privacy = privacy;
        this.timestamp = timestamp;
        this.target = target;
        this.rates = rates;
    }

    public ApiResponseForLive() {

    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}

