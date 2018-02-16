package com.example.daniel.pokerapp;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Card[] deckP1 = new Card[5];
    Card[] deckP2 = new Card[5];
    DeckOfCards myDeckOfCards;
    ImageView card1P1ImageView;
    ImageView card2P1ImageView;
    ImageView card3P1ImageView;
    ImageView card4P1ImageView;
    ImageView card5P1ImageView;
    ImageView card1P2ImageView;
    ImageView card2P2ImageView;
    ImageView card3P2ImageView;
    ImageView card4P2ImageView;
    ImageView card5P2ImageView;

    TextView p1CardsTextView;
    TextView p2CardsTextView;
    TextView p1HandValueTextView;
    TextView p2HandValueTextView;
    TextView resultValueTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        card1P1ImageView = findViewById(R.id.card1P1ImageView);
        card2P1ImageView = findViewById(R.id.card2P1ImageView);
        card3P1ImageView = findViewById(R.id.card3P1ImageView);
        card4P1ImageView = findViewById(R.id.card4P1ImageView);
        card5P1ImageView = findViewById(R.id.card5P1ImageView);
        card1P2ImageView = findViewById(R.id.card1P2ImageView);
        card2P2ImageView = findViewById(R.id.card2P2ImageView);
        card3P2ImageView = findViewById(R.id.card3P2ImageView);
        card4P2ImageView = findViewById(R.id.card4P2ImageView);
        card5P2ImageView = findViewById(R.id.card5P2ImageView);

        p1CardsTextView=findViewById(R.id.p1CardsTextView);
        p2CardsTextView=findViewById(R.id.p2CardsTextView);
        p1HandValueTextView = findViewById(R.id.p1HandValueTextView);
        p2HandValueTextView = findViewById(R.id.p2HandValueTextView);
        resultValueTextView = findViewById(R.id.resultValueTextView);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.dealButton:
                dealCardPlayers();
                break;
            case R.id.compareButton:
                decideWinner();
                break;
        }

    }

    public void dealCardPlayers(){

        String filename="";
        String cardHandText="";

        myDeckOfCards = new DeckOfCards();
        myDeckOfCards.shuffle();

        p1CardsTextView.setText("");
        p2CardsTextView.setText("");
        p1HandValueTextView.setText("");
        p2HandValueTextView.setText("");
        resultValueTextView.setText("");

        int resID =0;

        for(int i = 0; i<5; i++){
            deckP1[i] = myDeckOfCards.dealCard();
        }

        filename = deckP1[0].toString().toLowerCase().replaceAll(" ","");
        cardHandText = cardHandText + deckP1[0].toString() +"\n";

        resID = getResources().getIdentifier(filename, "drawable",  getPackageName());
        card1P1ImageView.setImageResource(resID);

        filename = deckP1[1].toString().toLowerCase().replaceAll(" ","");
        cardHandText = cardHandText + deckP1[1].toString() +"\n";

        resID = getResources().getIdentifier(filename, "drawable",  getPackageName());
        card2P1ImageView.setImageResource(resID);

        filename = deckP1[2].toString().toLowerCase().replaceAll(" ","");
        cardHandText = cardHandText + deckP1[2].toString() +"\n";

        resID = getResources().getIdentifier(filename, "drawable",  getPackageName());
        card3P1ImageView.setImageResource(resID);

        filename = deckP1[3].toString().toLowerCase().replaceAll(" ","");
        cardHandText = cardHandText + deckP1[3].toString() +"\n";

        resID = getResources().getIdentifier(filename, "drawable",  getPackageName());
        card4P1ImageView.setImageResource(resID);

        filename = deckP1[4].toString().toLowerCase().replaceAll(" ","");
        cardHandText = cardHandText + deckP1[4].toString() +"\n";

        resID = getResources().getIdentifier(filename, "drawable",  getPackageName());
        card5P1ImageView.setImageResource(resID);

        p1CardsTextView.setText(cardHandText);

        for(int i = 0; i<5; i++){
            deckP2[i] = myDeckOfCards.dealCard();
        }

        cardHandText = "";
        filename = deckP2[0].toString().toLowerCase().replaceAll(" ","");
        cardHandText = cardHandText + deckP2[0].toString() +"\n";

        resID = getResources().getIdentifier(filename, "drawable",  getPackageName());
        card1P2ImageView.setImageResource(resID);

        filename = deckP2[1].toString().toLowerCase().replaceAll(" ","");
        cardHandText = cardHandText + deckP2[1].toString() +"\n";

        resID = getResources().getIdentifier(filename, "drawable",  getPackageName());
        card2P2ImageView.setImageResource(resID);

        filename = deckP2[2].toString().toLowerCase().replaceAll(" ","");
        cardHandText = cardHandText + deckP2[2].toString() +"\n";

        resID = getResources().getIdentifier(filename, "drawable",  getPackageName());
        card3P2ImageView.setImageResource(resID);

        filename = deckP2[3].toString().toLowerCase().replaceAll(" ","");
        cardHandText = cardHandText + deckP2[3].toString() +"\n";

        resID = getResources().getIdentifier(filename, "drawable",  getPackageName());
        card4P2ImageView.setImageResource(resID);

        filename = deckP2[4].toString().toLowerCase().replaceAll(" ","");
        cardHandText = cardHandText + deckP2[4].toString() +"\n";

        resID = getResources().getIdentifier(filename, "drawable",  getPackageName());
        card5P2ImageView.setImageResource(resID);

        p2CardsTextView.setText(cardHandText);


    }


    public void decideWinner(){

        String[] score = {"none","A Pair of","2 pairs","3 of","A Straight","A Flush","A Full House","4 of","A Straight Flush","A Royal Flush"};
        int p1=0;
        int p2=0;
        String resultP1="";
        String resultP2="";

        resultP1 = myDeckOfCards.analyzeDeck(deckP1);
        resultP2 = myDeckOfCards.analyzeDeck(deckP2);

        for(int i=0;i<score.length;i++){
            if(resultP1.contains(score[i])){
                p1=i;
            }

        }

        p1HandValueTextView.setText(resultP1);

        for(int i=0;i<score.length;i++){
            if(resultP2.contains(score[i])){
                p2=i;
            }

        }

        p2HandValueTextView.setText(resultP2);
        if (p1>p2)
            resultValueTextView.setText("Player 1's hand is better!");
        else if(p2>p1)
            resultValueTextView.setText("Player 2's  hand is better");
        else
            resultValueTextView.setText("tied");
    }
}
