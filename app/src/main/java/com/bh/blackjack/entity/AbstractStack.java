package com.bh.blackjack.entity;

import java.util.ArrayList;

public abstract class AbstractStack {

    protected ArrayList<Card> cardStack;

    public ArrayList<Card> getStack() {
        return cardStack;
    }

    public Card getTopCard() {
        if (cardStack.isEmpty()) {
            return null;
        }
        return cardStack.get(cardStack.size() - 1);
    }


    public boolean hasCards() {
        return !cardStack.isEmpty();
    }

    protected Card popTopCard() {
        if (cardStack.isEmpty()) {
            return null;
        }
        return cardStack.remove(cardStack.size() - 1);
    }
}
