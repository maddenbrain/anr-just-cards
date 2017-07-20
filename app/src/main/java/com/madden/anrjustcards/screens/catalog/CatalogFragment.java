package com.madden.anrjustcards.screens.catalog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.madden.anrjustcards.R;
import com.madden.anrjustcards.model.pojo.Cards;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CatalogFragment extends Fragment implements CatalogContract.View {
    private CatalogAdapter adapter;

    @BindView(R.id.card_catalog_recycler)
    RecyclerView recycler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.catalog, container, false);
        ButterKnife.bind(this, root);

        adapter = new CatalogAdapter();
        recycler.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(root.getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(manager);

        return root;
    }

    @Override
    public void onCardsReceived(Cards cards) {
        adapter.setCards(cards);
    }
}