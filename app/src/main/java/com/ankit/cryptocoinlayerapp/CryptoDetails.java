package com.ankit.cryptocoinlayerapp;

import com.google.gson.annotations.SerializedName;

public class CryptoDetails {

    @SerializedName("symbol")
    private String symbol;

    @SerializedName("name")
    private String name;

    @SerializedName("name_full")
    private String fullName;

    @SerializedName("max_supply")
    private Number maxSupply;

    @SerializedName("icon_url")
    private String iconUrl;

    public CryptoDetails(String symbol, String name, String fullName, Number maxSupply, String iconUrl) {
        this.symbol = symbol;
        this.name = name;
        this.fullName = fullName;
        this.maxSupply = maxSupply;
        this.iconUrl = iconUrl;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Number getMaxSupply() {
        return maxSupply;
    }

    public void setMaxSupply(Number maxSupply) {
        this.maxSupply = maxSupply;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}

