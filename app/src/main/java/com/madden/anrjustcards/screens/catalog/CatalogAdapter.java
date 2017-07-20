package com.madden.anrjustcards.screens.catalog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.madden.anrjustcards.R;
import com.madden.anrjustcards.model.pojo.Cards;
import com.madden.anrjustcards.model.pojo.Datum;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.DatumViewHolder> {
    private Cards cards;

    @Override
    public DatumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.catalog_card, parent, false);
        return new DatumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DatumViewHolder holder, int position) {
        Datum datum = cards.data.get(position);
        String imageUrl = cards.imageUrlTemplate.replace("{code}", datum.code);

        Glide.with(holder.context)
                .load(imageUrl)
                .into(holder.cardImage);

        holder.cardName.setText(datum.title);
        holder.cardText.setText(datum.text);
    }

    @Override
    public int getItemCount() {
        return cards != null ? cards.data.size() : 0;
    }

    public void setCards(Cards cards) {
        this.cards = cards;
        notifyDataSetChanged();
    }

    class DatumViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_image) ImageView cardImage;
        @BindView(R.id.card_name) TextView cardName;
        @BindView(R.id.card_text) TextView cardText;
        Context context;

        DatumViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }
    }
}