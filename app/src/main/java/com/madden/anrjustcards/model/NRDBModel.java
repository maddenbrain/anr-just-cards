package com.madden.anrjustcards.model;

import com.madden.anrjustcards.model.pojo.Cards;
import com.madden.anrjustcards.screens.catalog.CatalogContract;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class NRDBModel implements CatalogContract.Model {
    private final NRDBService service;
    private final Observable<Cards> cardsObservable;
    private Subscription subscription;
    private Cards cards;
    private List<Action1<Cards>> cardsUpdatedListeners = new ArrayList<>();

    public NRDBModel() {
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://netrunnerdb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .build();

        service = retrofit.create(NRDBService.class);
        cardsObservable = service.cardsList().observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void addListener(Action1<Cards> listener) {
        cardsUpdatedListeners.add(listener);

        if (cards != null && cards.data.size() > 0)
            listener.call(cards);
        else
            updateCards();
    }

    @Override
    public void removeListener(Action1<Cards> listener) {
        cardsUpdatedListeners.remove(listener);
    }

    private void onCardsUpdated() {
        if (cards == null || cards.data.size() == 0)
            return;

        for (Action1<Cards> listener : cardsUpdatedListeners)
            listener.call(cards);
    }

    private void updateCards() {
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();

        subscription = cardsObservable.subscribe(cards -> {
            this.cards = cards;
            onCardsUpdated();
        });
    }
}