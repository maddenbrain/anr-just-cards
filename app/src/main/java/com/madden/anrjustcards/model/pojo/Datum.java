package com.madden.anrjustcards.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("code")
    @Expose
    public String code;
    @SerializedName("deck_limit")
    @Expose
    public int deckLimit;
    @SerializedName("faction_code")
    @Expose
    public String factionCode;
    @SerializedName("flavor")
    @Expose
    public String flavor;
    @SerializedName("illustrator")
    @Expose
    public String illustrator;
    @SerializedName("influence_limit")
    @Expose
    public int influenceLimit;
    @SerializedName("keywords")
    @Expose
    public String keywords;
    @SerializedName("minimum_deck_size")
    @Expose
    public int minimumDeckSize;
    @SerializedName("pack_code")
    @Expose
    public String packCode;
    @SerializedName("position")
    @Expose
    public int position;
    @SerializedName("quantity")
    @Expose
    public int quantity;
    @SerializedName("side_code")
    @Expose
    public String sideCode;
    @SerializedName("text")
    @Expose
    public String text;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("type_code")
    @Expose
    public String typeCode;
    @SerializedName("uniqueness")
    @Expose
    public boolean uniqueness;
    @SerializedName("advancement_cost")
    @Expose
    public int advancementCost;
    @SerializedName("agenda_points")
    @Expose
    public int agendaPoints;
    @SerializedName("trash_cost")
    @Expose
    public int trashCost;
    @SerializedName("base_link")
    @Expose
    public int baseLink;
    @SerializedName("cost")
    @Expose
    public int cost;
    @SerializedName("faction_cost")
    @Expose
    public int factionCost;
    @SerializedName("memory_cost")
    @Expose
    public int memoryCost;
    @SerializedName("strength")
    @Expose
    public int strength;
}