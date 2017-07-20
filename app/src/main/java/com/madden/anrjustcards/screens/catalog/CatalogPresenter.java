package com.madden.anrjustcards.screens.catalog;

import com.madden.anrjustcards.model.pojo.Cards;


public class CatalogPresenter implements CatalogContract.Presenter {
    private final CatalogContract.View view;
    private final CatalogContract.Model model;

    public CatalogPresenter(CatalogContract.View view, CatalogContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onResume() {
        model.addListener(this);
    }

    @Override
    public void onPause() {
        model.removeListener(this);
    }

    @Override
    public void call(Cards cards) {
        view.onCardsReceived(cards);
    }
}