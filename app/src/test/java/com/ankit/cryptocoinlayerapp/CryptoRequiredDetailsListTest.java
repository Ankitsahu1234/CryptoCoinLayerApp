package com.ankit.cryptocoinlayerapp;

import static org.junit.Assert.*;

import org.junit.Test;

public class CryptoRequiredDetailsListTest {

    @Test
    public void testGetName() {
        CryptoRequiredDetailsList cryptoDetails = new CryptoRequiredDetailsList("Bitcoin", 50000, "bitcoin_icon.png");
        assertEquals("Bitcoin", cryptoDetails.getName());
    }

    @Test
    public void testGetPrice() {
        CryptoRequiredDetailsList cryptoDetails = new CryptoRequiredDetailsList("Bitcoin", 50000, "bitcoin_icon.png");
        assertEquals(50000, cryptoDetails.getPrice());
    }

    @Test
    public void testSetPrice() {
        CryptoRequiredDetailsList cryptoDetails = new CryptoRequiredDetailsList("Bitcoin", 50000, "bitcoin_icon.png");
        cryptoDetails.setPrice(55000);
        assertEquals(55000, cryptoDetails.getPrice());
    }

    @Test
    public void testGetIconImg() {
        CryptoRequiredDetailsList cryptoDetails = new CryptoRequiredDetailsList("Bitcoin", 50000, "bitcoin_icon.png");
        assertEquals("bitcoin_icon.png", cryptoDetails.getIconImg());
    }
}