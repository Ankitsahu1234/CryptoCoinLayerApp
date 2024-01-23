package com.ankit.cryptocoinlayerapp;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CoinLayerAdapter extends RecyclerView.Adapter<CoinLayerAdapter.CoinLayerHolder> {

    private final ArrayList<CryptoRequiredDetailsList> coinList;

    public CoinLayerAdapter(ArrayList<CryptoRequiredDetailsList> coinList) {
        this.coinList = coinList;
    }

    public static class CoinLayerHolder extends RecyclerView.ViewHolder {
        final TextView tvName;
        final TextView tvExchangeRate;
        final ImageView ivIcon;

        public CoinLayerHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvExchangeRate = itemView.findViewById(R.id.tvExchangeRate);
            ivIcon = itemView.findViewById(R.id.ivIcon);
        }
    }

    @Override
    public CoinLayerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.coin_layout, parent, false);
        return new CoinLayerHolder(listView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(CoinLayerHolder holder, int position) {
        CryptoRequiredDetailsList currentItem = coinList.get(position);
        holder.tvName.setText(currentItem.getName());
        holder.tvExchangeRate.setText(String.valueOf(currentItem.getPrice()));
        Glide.with(holder.itemView)
                .load(currentItem.getIconImg())
                .into(holder.ivIcon);
    }

    @Override
    public int getItemCount() {
        return coinList.size();
    }
}

