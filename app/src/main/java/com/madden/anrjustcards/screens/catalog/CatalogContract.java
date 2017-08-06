package com.madden.anrjustcards.screens.catalog;

import com.madden.anrjustcards.model.pojo.Cards;

import rx.Observable;
import rx.functions.Action1;


public class CatalogContract {
    public interface View {
        void onCardsReceived(Cards cards);
        Observable<String> createSearchObservable();
    }

    public interface Presenter extends Action1<Cards>{
        void onResume();
        void onPause();
    }

    public interface Model {
        void addListener(Action1<Cards> listener);
        void removeListener(Action1<Cards> listener);
    }
}