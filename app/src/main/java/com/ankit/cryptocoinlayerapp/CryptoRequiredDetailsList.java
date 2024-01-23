package com.ankit.cryptocoinlayerapp;

public class CryptoRequiredDetailsList {

    private final String name;
    private Number price;
    private final String iconImg;

    public CryptoRequiredDetailsList(String name, Number price, String iconImg) {
        this.name = name;
        this.price = price;
        this.iconImg = iconImg;
    }

    public String getName() {
        return name;
    }

    public Number getPrice() {
        return price;
    }

    public void setPrice(Number price) {
        this.price = price;
    }

    public String getIconImg() {
        return iconImg;
    }
}

