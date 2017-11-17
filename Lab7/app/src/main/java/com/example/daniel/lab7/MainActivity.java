package com.example.daniel.lab7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int dice1;
    private int dice2;
    private int tries;
    private int result;
    private int point;
    Random num;
    Button playBtt;
    Button rollDices;
    TextView currentlyScore;
    TextView totalScore;
    TextView displayRes;
    ImageView die1Img;
    ImageView die2Img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dice1 = 1;
        dice2 = 1;
        tries = 0;
        result = 0;
        point = 0;
        playBtt = (Button) findViewById(R.id.playBtt);
        rollDices = (Button) findViewById(R.id.rollDices);
        currentlyScore = (TextView) findViewById(R.id.currentlyScore);
        totalScore = (TextView) findViewById(R.id.totalScore);
        displayRes = (TextView) findViewById(R.id.displayRes);
        die1Img = (ImageView) findViewById(R.id.die1Img);
        die2Img = (ImageView) findViewById(R.id.die2Img);

        displayRes.setText("Click Play to Start the Game!");
        currentlyScore.setText("You rolled: 0");
        totalScore.setText("Your point is: 0");
        rollDices.setEnabled(false);
        num = new Random();

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.playBtt:
                rollDices.setEnabled(true);
                playBtt.setEnabled(false);
                dice1 = 1;
                dice2 = 1;
                tries = 0;
                result = 0;
                point = 0;
                playGame();
                break;
            case R.id.rollDices:
                playGame();
                break;
        }

    }

    public void rollDices(){

        dice1 = num.nextInt(5)+1;
        dice2 = num.nextInt(5)+1;

        result = dice1 + dice2;

        switch (dice1){
            case 1:
                die1Img.setImageResource(R.drawable.die1);
                break;
            case 2:
                die1Img.setImageResource(R.drawable.die2);
                break;
            case 3:
                die1Img.setImageResource(R.drawable.die3);
                break;
            case 4:
                die1Img.setImageResource(R.drawable.die4);
                break;
            case 5:
                die1Img.setImageResource(R.drawable.die5);
                break;
            case 6:
                die1Img.setImageResource(R.drawable.die6);
                break;
        }

        switch (dice2){
            case 1:
                die2Img.setImageResource(R.drawable.die1);
                break;
            case 2:
                die2Img.setImageResource(R.drawable.die2);
                break;
            case 3:
                die2Img.setImageResource(R.drawable.die3);
                break;
            case 4:
                die2Img.setImageResource(R.drawable.die4);
                break;
            case 5:
                die2Img.setImageResource(R.drawable.die5);
                break;
            case 6:
                die2Img.setImageResource(R.drawable.die6);
                break;
        }




    }

    public void playGame(){
            rollDices();


                if ((result == 7 || result == 11) && tries == 0){

                    tries+=1;

                    currentlyScore.setText("You rolled: "+result);
                    totalScore.setText("Your point is: "+result);
                    displayRes.setText("You win!!!\nClick to Play again.");
                    rollDices.setEnabled(false);
                    playBtt.setEnabled(true);


                }

            else {

                    if ((result == 2 || result == 3 || result == 12) && tries == 0){

                        tries+=1;

                        currentlyScore.setText("You rolled: "+result);
                        totalScore.setText("Your point is: "+result);
                        displayRes.setText("House wins!!!");
                        rollDices.setEnabled(false);
                        playBtt.setEnabled(true);
                    }

                else{
                        if (result == 7){

                            tries+=1;

                            currentlyScore.setText("You rolled: "+point);
                            totalScore.setText("Your point is: "+result);
                            displayRes.setText("House wins!!!");
                            rollDices.setEnabled(false);
                            playBtt.setEnabled(true);

                        }

                    else {

                            if(tries == 0){
                                point = result;
                                tries+=1;
                                currentlyScore.setText("You rolled: "+result);
                                totalScore.setText("Your point is: "+result);
                                displayRes.setText("Roll Again!!!");

                            }

                            else{
                                if (point == result){
                                    tries+=1;

                                    currentlyScore.setText("You rolled: "+result);
                                    totalScore.setText("Your point is: "+result);
                                    displayRes.setText("You win!!!");
                                    rollDices.setEnabled(false);
                                    playBtt.setEnabled(true);

                                }
                            else{

                                    tries+=1;
                                    currentlyScore.setText("You rolled: "+result);
                                    totalScore.setText("Your point is: "+point);
                                    displayRes.setText("Roll Again!!!");


                                }

                            }



                        }



                    }
                }



        }



}


