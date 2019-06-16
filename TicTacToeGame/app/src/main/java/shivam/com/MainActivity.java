package shivam.com;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

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

    int gameState[]={2, 2, 2, 2, 2, 2, 2, 2, 2};

    int [][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};


    public void getSymbol(View view){

        ImageView image=(ImageView)view;

        int tappedImagePosition=Integer.parseInt(image.getTag().toString());

        gameState[tappedImagePosition]=activePlayer;

        image.setTranslationX(-1500);

        if (activePlayer==0){
            activePlayer=1;
            image.setImageResource(R.drawable.o);
        }else{
            activePlayer=0;
            image.setImageResource(R.drawable.x);
        }

        image.animate().translationXBy(1500).rotation(1800).setDuration(800);

    }
}
