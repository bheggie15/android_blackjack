package com.bh.blackjack.entity;

import java.util.ArrayList;

public class Hand extends AbstractStack{

    public Hand (){
        cardStack = new ArrayList<>();
    }

    public void clear (){
        cardStack.clear();
    }

    public void addCard(Card c){cardStack.add(c);}
}
