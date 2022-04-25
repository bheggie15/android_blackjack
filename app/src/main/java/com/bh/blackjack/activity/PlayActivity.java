package com.bh.blackjack.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bh.blackjack.R;
import com.bh.blackjack.entity.AbstractStack;
import com.bh.blackjack.entity.Board;
import com.bh.blackjack.entity.Card;
import com.bh.blackjack.logic.DeckManager;

public class PlayActivity extends AppCompatActivity {

    Board board;
    TextView dealerValueLabel;
    TextView playerValueLabel;
    RelativeLayout playerHandLayout;
    RelativeLayout dealerHandLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        // Find buttons
        Button standButton = findViewById(R.id.standButton);
        Button hitButton = findViewById(R.id.hitButton);

        // Find value labels
        dealerValueLabel = findViewById(R.id.dealerValue);
        playerValueLabel = findViewById(R.id.playerValue);

        // Find layouts
        playerHandLayout = findViewById(R.id.playerHand);
        dealerHandLayout = findViewById(R.id.dealerHand);

        board = new Board();

        drawBoard();

    }

    public void drawBoard() {
        dealerValueLabel.setText(board.getDealerValue());
        playerValueLabel.setText(board.getPlayerValue());

        int counter1 = 0;
        for (Card c : board.getDealerHand().getStack()) {
            inflateCardLayout(dealerHandLayout, c, counter1, board.getDealerHand());
            counter1++;
        }
        int counter2 = 0;
        for (Card c : board.getPlayerHand().getStack()) {
            inflateCardLayout(playerHandLayout, c, counter2, board.getPlayerHand());
            counter2++;
        }
    }

    public void inflateCardLayout(RelativeLayout handLayout, Card c, int position, AbstractStack hand){
        View cardLayout = LayoutInflater.from(this).inflate(R.layout.card_layout, handLayout, false);
        ImageView cardImage = cardLayout.findViewById(R.id.card);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,0,position*30,0);
        cardImage.setLayoutParams(params);
        handLayout.addView(cardLayout);
    }
}