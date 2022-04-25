package com.bh.blackjack.logic;

import com.bh.blackjack.entity.Card;
import com.bh.blackjack.entity.Deck;
import com.bh.blackjack.enums.Rank;
import com.bh.blackjack.enums.Suit;

import java.util.ArrayList;
import java.util.Collections;

public class DeckManager {
    private static DeckManager instance = null;
    private Deck mainDeck;
    private Deck wasteDeck;

    private DeckManager(){
        mainDeck = new Deck(createNewDeck());
        wasteDeck = new Deck(new ArrayList<>());
        shuffleDeck(mainDeck.getStack());
    }

    public static DeckManager getDeckManagerInstance() {
        if (instance == null) {
            instance = new DeckManager();
        }
        return instance;
    }

    private ArrayList<Card> createNewDeck() {
        ArrayList<Card> newDeck = new ArrayList<>();
        for (final Rank rank: Rank.values()) {
            for (final Suit suit: Suit.values()) {
                newDeck.add(new Card(rank, suit));
            }
        }
        return newDeck;
    }

    private static void shuffleDeck(ArrayList<Card> deck) {
        Collections.shuffle(deck);
    }

    public ArrayList<Card> getMainDeck() {
        if (!instance.mainDeck.hasCards()){
            recycleWaste();
        }
        return instance.mainDeck.getStack();
    }
    public void recycleWaste(){
        for (Card c : wasteDeck.getStack()) {
            if (c.getRank() == Rank.ACE && c.getValue() == 1){
                c.toggleAceValue();
            }
        }
        mainDeck.convertWasteToStock(wasteDeck.getStack());
        Collections.shuffle(mainDeck.getStack());
    }
}
