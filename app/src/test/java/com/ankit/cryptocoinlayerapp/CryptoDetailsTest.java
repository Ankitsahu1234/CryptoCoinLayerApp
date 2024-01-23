package com.ankit.cryptocoinlayerapp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CryptoDetailsTest {

    @Test
    public void testGettersAndSetters() {
        // Arrange
        String symbol = "BTC";
        String name = "Bitcoin";
        String fullName = "Bitcoin";
        Number maxSupply = 21000000;
        String iconUrl = "bitcoin.png";

        // Act
        CryptoDetails cryptoDetails = new CryptoDetails(symbol, name, fullName, maxSupply, iconUrl);

        // Assert
        assertEquals(symbol, cryptoDetails.getSymbol());
        assertEquals(name, cryptoDetails.getName());
        assertEquals(fullName, cryptoDetails.getFullName());
        assertEquals(maxSupply, cryptoDetails.getMaxSupply());
        assertEquals(iconUrl, cryptoDetails.getIconUrl());
    }

    @Test
    public void testSetters() {
        // Arrange
        CryptoDetails cryptoDetails = new CryptoDetails("BTC", "Bitcoin", "Bitcoin", 21000000, "bitcoin.png");

        // Act
        cryptoDetails.setSymbol("ETH");
        cryptoDetails.setName("Ethereum");
        cryptoDetails.setFullName("Ethereum");
        cryptoDetails.setMaxSupply(100000000);
        cryptoDetails.setIconUrl("ethereum.png");

        // Assert
        assertEquals("ETH", cryptoDetails.getSymbol());
        assertEquals("Ethereum", cryptoDetails.getName());
        assertEquals("Ethereum", cryptoDetails.getFullName());
        assertEquals(100000000, cryptoDetails.getMaxSupply());
        assertEquals("ethereum.png", cryptoDetails.getIconUrl());
    }
}
