package shivam.com;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    // 0: O.
    // 1: X.
    // 2: Empty.
    int activePlayer=0;


    // gameState stores the state of all the 9 blocks
    // 0 for O
    // 1 for X
    // 2 if field is Empty

    //Initially all fields are empty !
    int[] gameState={2, 2, 2, 2, 2, 2, 2, 2, 2};


    // At these positions, if there are same Symbol(O or X), then the player will win!
    int [][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    boolean gameOver=false;


    public void getSymbol(View view){

        ImageView image=(ImageView)view;

        int tappedImagePosition=Integer.parseInt(image.getTag().toString());

        // Checking whether the block is empty and the game is not over !
        if (gameState[tappedImagePosition]==2 && !gameOver) {

            gameState[tappedImagePosition] = activePlayer;

            image.setTranslationX(-1500);


            // Logic for alternate symbol.
            if (activePlayer == 0) {
                activePlayer = 1;
                image.setImageResource(R.drawable.o);
            } else {
                activePlayer = 0;
                image.setImageResource(R.drawable.x);
            }

            image.animate().translationXBy(1500).rotation(1800).setDuration(300);


            for (int[] winningPosition : winningPositions) {
                // If gameState of the winning positions are same the a player will win!
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[2]] != 2) {

                    String winner = "";

                    if (activePlayer == 0) {
                        winner = "Player X ";
                    } else {
                        winner = "Player O ";
                    }

                    gameOver = true;
                    Toast.makeText(this, winner + "has won !", Toast.LENGTH_SHORT).show();

                    Button playAgain=findViewById(R.id.playAgain);

                    TextView winnerText=findViewById(R.id.winnerText);

                    playAgain.setVisibility(View.VISIBLE);
                    winnerText.setText(winner + "has won !");
                    winnerText.setVisibility(View.VISIBLE);


                }
            }
        }
    }


    public void reset(View view){

        Button playAgain=findViewById(R.id.playAgain);

        TextView winnerText=findViewById(R.id.winnerText);

        playAgain.setVisibility(View.INVISIBLE);
        winnerText.setText(null);
        winnerText.setVisibility(View.INVISIBLE);

        android.support.v7.widget.GridLayout gridLayout=findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView child = (ImageView)gridLayout.getChildAt(i);

            child.setImageDrawable(null);
        }

        activePlayer=0;
        for (int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        gameOver=false;

    }

}
