package com.ankit.cryptocoinlayerapp;

import static org.junit.Assert.*;

import org.junit.Test;

public class ApiResponseForLiveTest {

    @Test
    public void testApiResponseForLive() {
        // Arrange
        ApiResponseForLive apiResponse = new ApiResponseForLive(true, "terms", "privacy", 1234567890L, "USD", null);

        // Assert
        assertTrue(apiResponse.isSuccess());
        assertEquals("terms", apiResponse.getTerms());
        assertEquals("privacy", apiResponse.getPrivacy());
        assertEquals(1234567890L, apiResponse.getTimestamp());
        assertEquals("USD", apiResponse.getTarget());
        assertNull(apiResponse.getRates());
    }

    @Test
    public void testSetSuccess() {
        // Arrange
        ApiResponseForLive apiResponse = new ApiResponseForLive(true, "terms", "privacy", 1234567890L, "USD", null);

        // Act
        apiResponse.setSuccess(false);

        // Assert
        assertFalse(apiResponse.isSuccess());
    }

    @Test
    public void testSetTerms() {
        // Arrange
        ApiResponseForLive apiResponse = new ApiResponseForLive(true, "terms", "privacy", 1234567890L, "USD", null);

        // Act
        apiResponse.setTerms("newTerms");

        // Assert
        assertEquals("newTerms", apiResponse.getTerms());
    }

    @Test
    public void testSetPrivacy() {
        // Arrange
        ApiResponseForLive apiResponse = new ApiResponseForLive(true, "terms", "privacy", 1234567890L, "USD", null);

        // Act
        apiResponse.setPrivacy("newPrivacy");

        // Assert
        assertEquals("newPrivacy", apiResponse.getPrivacy());
    }

    @Test
    public void testSetTimestamp() {
        // Arrange
        ApiResponseForLive apiResponse = new ApiResponseForLive(true, "terms", "privacy", 1234567890L, "USD", null);

        // Act
        apiResponse.setTimestamp(9876543210L);

        // Assert
        assertEquals(9876543210L, apiResponse.getTimestamp());
    }

    @Test
    public void testSetTarget() {
        // Arrange
        ApiResponseForLive apiResponse = new ApiResponseForLive(true, "terms", "privacy", 1234567890L, "USD", null);

        // Act
        apiResponse.setTarget("EUR");

        // Assert
        assertEquals("EUR", apiResponse.getTarget());
    }

    @Test
    public void testSetRates() {
        // Arrange
        ApiResponseForLive apiResponse = new ApiResponseForLive(true, "terms", "privacy", 1234567890L, "USD", null);

        // Act
        apiResponse.setRates(null);

        // Assert
        assertNull(apiResponse.getRates());
    }
}