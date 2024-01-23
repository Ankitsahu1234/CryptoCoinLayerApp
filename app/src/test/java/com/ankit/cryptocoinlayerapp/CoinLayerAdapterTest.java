package com.ankit.cryptocoinlayerapp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

public class CoinLayerAdapterTest {

    private CoinLayerAdapter adapter;
    private ArrayList<CryptoRequiredDetailsList> mockCoinList;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setup() {
        mockCoinList = new ArrayList<>();
        mockCoinList.add(new CryptoRequiredDetailsList("Bitcoin", 50000, "bitcoin.png"));
        mockCoinList.add(new CryptoRequiredDetailsList("Ethereum", 3000, "ethereum.png"));

        adapter = new CoinLayerAdapter(mockCoinList);
    }

    @Test
    public void getItemCount_shouldReturnCorrectSize() {
        // Arrange

        // Act
        int itemCount = adapter.getItemCount();

        // Assert
        assertEquals(mockCoinList.size(), itemCount);
    }

    @Test
    public void onCreateViewHolder_shouldInflateCorrectLayout() {
        // Arrange
        ViewGroup mockParent = mock(ViewGroup.class);
        LayoutInflater mockInflater = mock(LayoutInflater.class);
        View mockView = mock(View.class);

        when(mockParent.getContext()).thenReturn(ApplicationProvider.getApplicationContext());
        when(mockInflater.inflate(Mockito.anyInt(), Mockito.eq(mockParent), Mockito.eq(false)))
                .thenReturn(mockView);

        // Act
        CoinLayerAdapter.CoinLayerHolder holder = adapter.onCreateViewHolder(mockParent, 0);

        // Assert
        assertEquals(mockView, holder.itemView);
    }

    @Test
    public void onBindViewHolder_shouldBindDataCorrectly() {
        // Arrange
        CoinLayerAdapter.CoinLayerHolder mockHolder = mock(CoinLayerAdapter.CoinLayerHolder.class);
        int position = 0;
        CryptoRequiredDetailsList mockItem = mockCoinList.get(position);

        // Act
        adapter.onBindViewHolder(mockHolder, position);

        // Assert
        verify(mockHolder.tvName).setText(mockItem.getName());
        verify(mockHolder.tvExchangeRate).setText(String.valueOf(mockItem.getPrice()));
        verify(mockHolder.ivIcon).setImageResource(Integer.parseInt(mockItem.getIconImg()));
    }
}
