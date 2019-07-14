package com.example.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // yellow:0, red:1, empty:2
    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean gameActive = true;
    int turnsLeft= 9;

    public void dropIn(View view){

        TextView playerTextView = findViewById(R.id.playerTextView);
        ImageView counter = (ImageView) view;
        Button playAgainButton = findViewById(R.id.playAgainButton);
        TextView winnerTextView = findViewById(R.id.winnerTextView);

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter]== 2 && gameActive) {
            gameState[tappedCounter] = activePlayer;
            turnsLeft--;

            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).setDuration(1000);

            for (int[] winningPositions : winningPositions) {
                if (gameState[winningPositions[0]] == gameState[winningPositions[1]] && gameState[winningPositions[1]] == gameState[winningPositions[2]] && gameState[winningPositions[0]] != 2) {
                    gameActive = false;
                    String winner = "";
                    if (activePlayer == 0)
                        winner = "Red";
                    else
                        winner = "Yellow";

                    winnerTextView.setText(winner + " has won");

                    playAgainButton.setVisibility(view.VISIBLE);
                    winnerTextView.setVisibility(view.VISIBLE);
                    playerTextView.setVisibility(view.INVISIBLE);
                }
            }

            if(turnsLeft==0){
                gameActive = false;

                winnerTextView.setText("It's a draw");

                playAgainButton.setVisibility(view.VISIBLE);
                winnerTextView.setVisibility(view.VISIBLE);
                playerTextView.setVisibility(view.INVISIBLE);
            }

            if(activePlayer ==0)
                playerTextView.setText("It's Yellow's turn");
            else
                playerTextView.setText("It's Red's turn");


        }
    }

    public void playAgain(View view) {
        recreate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
