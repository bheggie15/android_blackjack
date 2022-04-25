package com.bh.blackjack.entity;

import java.util.ArrayList;
import java.util.Collections;

public class Deck extends AbstractStack{


    public Deck (ArrayList<Card> cardStack){
        this.cardStack = cardStack;
    }

    public void convertWasteToStock(ArrayList<Card> wasteStack) {
        cardStack.addAll(wasteStack);
        cardStack.forEach(card -> card.setIsFaceUp(false));
    }

}
