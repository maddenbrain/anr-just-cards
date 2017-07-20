package com.madden.anrjustcards.model;

import com.madden.anrjustcards.model.pojo.Cards;

import retrofit2.http.GET;
import rx.Observable;


public interface NRDBService {
    @GET("/api/2.0/public/cards")
    Observable<Cards> cardsList();
}