package com.bh.blackjack.enums;

import androidx.annotation.NonNull;

public enum Suit {
    SPADES,
    CLUBS,
    HEARTS,
    DIAMONDS;

    public SuitColor getSuitColor() {
        if (this == SPADES || this == CLUBS) {
            return SuitColor.BLACK;
        }

        return SuitColor.RED;
    }

    @NonNull
    @Override
    public String toString() {
        String result = "N/A";
        switch (this) {
            case SPADES:
                result = "S";
                break;
            case CLUBS:
                result =  "C";
                break;
            case HEARTS:
                result =  "H";
                break;
            case DIAMONDS:
                result =  "D";
                break;
        }
        return result;
    }
}