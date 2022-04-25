package com.bh.blackjack.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.bh.blackjack.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        Button playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener( click -> {
            Intent playIntent = new Intent(this, PlayActivity.class);
            startActivity(playIntent);
        });
    }
}