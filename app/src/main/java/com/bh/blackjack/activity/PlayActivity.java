package com.bh.blackjack.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bh.blackjack.R;
import com.bh.blackjack.entity.AbstractStack;
import com.bh.blackjack.entity.Board;
import com.bh.blackjack.entity.Card;

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

        // Set on clicks for buttons
        standButton.setOnClickListener( click -> {
            board.dealersTurn();
            drawBoard();
        });
        hitButton.setOnClickListener( click -> {
            board.hit();
            drawBoard();
        });

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
        eraseBoard();

        dealerValueLabel.setText(String.valueOf(board.getDealerValue()));
        playerValueLabel.setText(String.valueOf(board.getPlayerValue()));

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
        checkWin();
    }

    private void checkWin() {
        if (board.checkWinner()){
            if (board.isPlayerWins()){
                new AlertDialog.Builder(this)
                        .setTitle("You Win!")
                        .setMessage("Congratuations! You win!")
                        .setPositiveButton("New Game", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                recreate();
                            }
                        })
                        .setNegativeButton("Home", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent menuIntent = new Intent(getApplicationContext(), MenuActivity.class);
                                startActivity(menuIntent);
                            }
                        })
                        .show();
            }
            else {
                new AlertDialog.Builder(this)
                        .setTitle("You Lose")
                        .setMessage("You love, better luck next time!")
                        .setPositiveButton("New Game", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                recreate();
                            }
                        })
                        .setNegativeButton("Home", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent menuIntent = new Intent(getApplicationContext(), MenuActivity.class);
                                startActivity(menuIntent);
                            }
                        })
                        .show();
            }
        }
    }

    private void eraseBoard() {
        playerHandLayout.removeAllViews();
        dealerHandLayout.removeAllViews();
    }

    public void inflateCardLayout(RelativeLayout handLayout, Card c, int position, AbstractStack hand){
        View cardLayout = LayoutInflater.from(this).inflate(R.layout.card_layout, handLayout, false);
        ImageView cardImage = cardLayout.findViewById(R.id.card);
        cardImage.setImageResource(getResources().getIdentifier("@drawable/"+ c.getImageUrl(), null, getPackageName()));
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins((position*100),0,0,0);
        params.addRule(RelativeLayout.ALIGN_PARENT_START);
        cardImage.setLayoutParams(params);
        handLayout.addView(cardLayout);
    }
}