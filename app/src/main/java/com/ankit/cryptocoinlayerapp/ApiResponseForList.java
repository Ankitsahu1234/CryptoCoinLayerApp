package com.ankit.cryptocoinlayerapp;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class ApiResponseForList {

    @SerializedName("success")
    private boolean success;

    @SerializedName("crypto")
    private Map<String, CryptoDetails> crypto;

    // Empty constructor
    public ApiResponseForList() {
    }

    public ApiResponseForList(boolean success, Map<String, CryptoDetails> crypto) {
        this.success = success;
        this.crypto = crypto;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Map<String, CryptoDetails> getCrypto() {
        return crypto;
    }

    public void setCrypto(Map<String, CryptoDetails> crypto) {
        this.crypto = crypto;
    }
}

