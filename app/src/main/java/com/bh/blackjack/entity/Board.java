package com.bh.blackjack.entity;

import com.bh.blackjack.enums.Rank;
import com.bh.blackjack.logic.DeckManager;

import java.util.ArrayList;

public class Board {
    private DeckManager deckManager;
    private Hand dealerHand;
    private Hand playerHand;
    private int dealerValue;
    private int playerValue;

    public Board (){
        deckManager = DeckManager.getDeckManagerInstance();
        dealerHand = new Hand();
        playerHand = new Hand();
        dealerValue = 0;
        playerValue = 0;
    }

    public void calculateValues(){

        dealerValue = 0;
        for (Card c : dealerHand.getStack()) {
            dealerValue+= c.getValue();
        }
        if (dealerValue > 21 && containsAce(dealerHand)){
            for (Card c : dealerHand.getStack()) {
                if (c.getRank() == Rank.ACE && c.getValue() == 11){
                    c.toggleAceValue();
                    break;
                }
            }
        }

        playerValue = 0;
        for (Card c : playerHand.getStack()) {
            playerValue+= c.getValue();
        }
        if (playerValue > 21 && containsAce(playerHand)){
            for (Card c : playerHand.getStack()) {
                if (c.getRank() == Rank.ACE && c.getValue() == 11){
                    c.toggleAceValue();
                    break;
                }
            }
        }
    }

    public boolean containsAce (Hand hand){
        for (Card c : hand.getStack()) {
            if (c.getRank() == Rank.ACE){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Card> getDeck() {
        return deckManager.getMainDeck();
    }

    public int getDealerValue() {
        return dealerValue;
    }

    public int getPlayerValue() {
        return playerValue;
    }

    public Hand getDealerHand() {
        return dealerHand;
    }

    public Hand getPlayerHand() {
        return playerHand;
    }
}
