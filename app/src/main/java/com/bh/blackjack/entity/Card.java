package com.bh.blackjack.entity;

import com.bh.blackjack.enums.Rank;
import com.bh.blackjack.enums.Suit;
import com.bh.blackjack.enums.SuitColor;

import java.util.Locale;

public class Card {

    private final Rank rank;
    private final Suit suit;
    private final SuitColor color;
    private boolean isFaceUp;
    private String imageUrl;
    private int value;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
        this.color = suit.getSuitColor();
        this.imageUrl = suit.toString().toLowerCase(Locale.ROOT) + rank.toString();
        this.isFaceUp = false;
        value = rank.getValue();
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public SuitColor getColor() {
        return color;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean getIsFaceUp() {
        return isFaceUp;
    }

    public void setIsFaceUp(boolean faceUp) {
        isFaceUp = faceUp;
    }

    public int getValue() {
        return value;
    }
    public void toggleAceValue(){
        if (getRank() == Rank.ACE){
            if (getValue() == 11){
                value = 1;
            }
            else {
                value = 11;
            }
        }
    }
}
