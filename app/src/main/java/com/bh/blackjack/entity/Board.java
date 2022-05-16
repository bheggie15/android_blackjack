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
    private boolean playerWins = false;
    private boolean dealerWins = false;
    private boolean dealingDone = false;

    public Board (){
        deckManager = DeckManager.getDeckManagerInstance();
        dealerHand = new Hand();
        dealDealerCard();
        dealDealerCard();
        playerHand = new Hand();
        dealPlayerCard();
        dealPlayerCard();
        calculateValues();
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
                    dealerValue = 0;
                    for (Card ca : dealerHand.getStack()) {
                        dealerValue+= ca.getValue();
                    }
                    break;
                }
            }
        }

        dealerValue = 0;
        for (Card c : dealerHand.getStack()) {
            if (c.getIsFaceUp()){
                dealerValue+= c.getValue();
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
                    playerValue = 0;
                    for (Card ca : playerHand.getStack()) {
                        playerValue+= ca.getValue();
                    }
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

    public void dealersTurn(){
        while (!checkWinner()) {
            for (Card c:dealerHand.getStack()) {
                c.setIsFaceUp(true);
            }
            calculateValues();
            if (dealerValue >= 15) {
                dealingDone = true;
            }
            else {
                dealDealerCard();
                calculateValues();
            }
        }
    }

    public Boolean checkWinner(){
        calculateValues();
        if (checkPlayerBlackjack() || checkDealerBust()){
            playerWins = true;
            return true;
        }
        else if (checkDealerBlackjack() || checkPlayerBust()){
            dealerWins = true;
            return true;
        }
        else if (playerValue > dealerValue && dealingDone){
            playerWins = true;
            return true;
        }
        else if (dealerValue > playerValue && dealingDone) {
            dealerWins = true;
            return true;
        }
        else{
            return false;
        }
    }

    private boolean checkPlayerBust(){
        if (playerValue > 21){
            return true;
        }
        else {
            return false;
        }
    }

    private boolean checkDealerBust(){
        if (dealerValue > 21){
            return true;
        }
        else {
            return false;
        }
    }
    private boolean checkPlayerBlackjack(){
        if(playerValue == 21){
            return true;
        }
        else {
            return false;
        }
    }
    private boolean checkDealerBlackjack(){
        if(dealerValue == 21){
            return true;
        }
        else {
            return false;
        }
    }

    public void dealPlayerCard(){
        playerHand.addCard(deckManager.getMainDeck().popTopCard());
    }
    public void dealDealerCard(){
        dealerHand.addCard(deckManager.getMainDeck().popTopCard());
    }

    public Deck getDeck() {
        return deckManager.getMainDeck();
    }

    public int getDealerValue() {
        calculateValues();
        return dealerValue;
    }

    public int getPlayerValue() {
        calculateValues();
        return playerValue;
    }

    public Hand getDealerHand() { return dealerHand;}

    public Hand getPlayerHand() {
        return playerHand;
    }

    public void hit() {
        dealPlayerCard();
        calculateValues();
    }

    public boolean isPlayerWins() {
        return playerWins;
    }

    public boolean isDealerWins() {
        return dealerWins;
    }
}
