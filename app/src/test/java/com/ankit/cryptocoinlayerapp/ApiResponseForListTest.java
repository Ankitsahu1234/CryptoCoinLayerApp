package com.ankit.cryptocoinlayerapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

public class ApiResponseForListTest {

    @Mock
    private CryptoDetails mockCryptoDetails;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGettersAndSetters() {
        // Arrange
        Map<String, CryptoDetails> mockCryptoMap = new HashMap<>();
        mockCryptoMap.put("BTC", mockCryptoDetails);

        ApiResponseForList apiResponse = new ApiResponseForList(true, mockCryptoMap);

        // Act
        boolean success = apiResponse.isSuccess();
        Map<String, CryptoDetails> cryptoMap = apiResponse.getCrypto();

        // Assert
        assertTrue(success);
        assertNotNull(cryptoMap);
        assertEquals(1, cryptoMap.size());
        assertEquals(mockCryptoDetails, cryptoMap.get("BTC"));
    }

    @Test
    public void testSetSuccess() {
        // Arrange
        ApiResponseForList apiResponse = new ApiResponseForList(true, new HashMap<>());

        // Act
        apiResponse.setSuccess(false);

        // Assert
        assertFalse(apiResponse.isSuccess());
    }

    @Test
    public void testSetCrypto() {
        // Arrange
        ApiResponseForList apiResponse = new ApiResponseForList(true, new HashMap<>());

        // Act
        Map<String, CryptoDetails> mockCryptoMap = new HashMap<>();
        mockCryptoMap.put("ETH", mockCryptoDetails);
        apiResponse.setCrypto(mockCryptoMap);

        // Assert
        assertEquals(mockCryptoMap, apiResponse.getCrypto());
    }

    @Test
    public void testEmptyConstructor() {
        // Arrange
        ApiResponseForList apiResponse = new ApiResponseForList();

        // Act & Assert
        assertNull(apiResponse.getCrypto());
        assertFalse(apiResponse.isSuccess());
    }
}
