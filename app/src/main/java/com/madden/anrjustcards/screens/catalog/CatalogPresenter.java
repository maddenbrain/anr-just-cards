package com.madden.anrjustcards.screens.catalog;

import com.madden.anrjustcards.model.DatumsSearchEngine;
import com.madden.anrjustcards.model.pojo.Cards;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class CatalogPresenter implements CatalogContract.Presenter {
    private final CatalogContract.View view;
    private final CatalogContract.Model model;

    private Observable<String> searchObservable;
    private Subscription subscription;
    private Cards cards = new Cards();

    public CatalogPresenter(CatalogContract.View view, CatalogContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onResume() {
        model.addListener(this);

        if (searchObservable == null)
            searchObservable = view.createSearchObservable();

        if (subscription == null || subscription.isUnsubscribed()) {
            subscription = searchObservable
                    .debounce(250, TimeUnit.MILLISECONDS)
                    .observeOn(Schedulers.io())
                    .map(DatumsSearchEngine::generatePredicate)
                    .switchMap(predicate -> Observable.from(cards.data)
                            .filter(predicate::apply)
                            .toList())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> {
                        Cards resCards = new Cards();
                        resCards.imageUrlTemplate = cards.imageUrlTemplate;
                        resCards.data = result;

                        view.onCardsReceived(resCards);
                    });
        }
    }

    @Override
    public void onPause() {
        model.removeListener(this);
        subscription.unsubscribe();
    }

    @Override
    public void call(Cards cards) {
        this.cards = cards;
        view.onCardsReceived(cards);
    }
}