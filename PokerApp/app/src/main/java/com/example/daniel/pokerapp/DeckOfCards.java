package com.example.daniel.pokerapp;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Comparator;

public class DeckOfCards
{
   private Card[] deck; // array of Card objects
   private int currentCard; // index of next Card to be dealt (0-51)
   private static final int NUMBER_OF_CARDS = 52; // constant # of Cards
   // random number generator
   private static final SecureRandom randomNumbers = new SecureRandom();
   
   private String[] faces = { "Deuce", "Three", "Four", "Five", "Six",
         "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King","Ace"};
   private String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};

    // constructor fills deck of Cards
   public DeckOfCards()
   {
     
      deck = new Card[NUMBER_OF_CARDS]; // create array of Card objects
      currentCard = 0; // first Card dealt will be deck[0]

      // populate deck with Card objects
      for (int count = 0; count < deck.length; count++) 
         deck[count] = 
            new Card(faces[count % 13], suits[count / 13]);
   } 

   // shuffle deck of Cards with one-pass algorithm
   public void shuffle()
   {
      // next call to method dealCard should start at deck[0] again
      currentCard = 0; 

      // for each Card, pick another random Card (0-51) and swap them
      for (int first = 0; first < deck.length; first++) 
      {
         // select a random number between 0 and 51 
         int second =  randomNumbers.nextInt(NUMBER_OF_CARDS);
         
         // swap current Card with randomly selected Card
         Card temp = deck[first];        
         deck[first] = deck[second];   
         deck[second] = temp;            
      } 
   } 

   // deal one Card
   public Card dealCard()
   {
      // determine whether Cards remain to be dealt
      if (currentCard < deck.length)
         return deck[currentCard++]; // return current Card in array
      else        
         return null; // return null to indicate that all Cards were dealt
   } 
   
   public String analyzeDeck(Card[] deckP){
       
       String[] deckString;
       String card;
       String[][] deckPlayer= new String[5][2];
       String[] straightFullHouse=new String [5];
       String temp;
       String returnAnalysis="";
     
       
       int face,pair;
       int kind=0;
       int faceCheck=0; //if the hand has a three or four of a kind, the code will not let to show a pair of the same face.
       
       
       //Comment this for-loop for testing

       for(int i=0;i<5;i++){
           card=deckP[i].toString();//convert the object Card into String
           deckString = card.split(" of ");// split the variable card to get the face and suit of the each card
           deckPlayer[i][0] = deckString[0];
           deckPlayer[i][1] = deckString[1];
           
           straightFullHouse[i] = deckString[0];
        }
       //Comment this for-loop for testing


       // Testing Royal Flush
       /*straightFullHouse[0] = deckPlayer[0][0] = "Ace";
       deckPlayer[0][1] = "Hearts";
       straightFullHouse[1] =deckPlayer[1][0] = "King";
       deckPlayer[1][1] = "Hearts";
       straightFullHouse[2] =deckPlayer[2][0] = "Queen";
       deckPlayer[2][1] = "Hearts";
       straightFullHouse[3] =deckPlayer[3][0] = "Jack";
       deckPlayer[3][1] = "Hearts";
       straightFullHouse[4] = deckPlayer[4][0] = "Ten";
       deckPlayer[4][1] = "Hearts";*/

       // Testing Straight Flush
       /*straightFullHouse[0] = deckPlayer[0][0] = "King";
       deckPlayer[0][1] = "Hearts";
       straightFullHouse[1] =deckPlayer[1][0] = "Queen";
       deckPlayer[1][1] = "Hearts";
       straightFullHouse[2] =deckPlayer[2][0] = "Jack";
       deckPlayer[2][1] = "Hearts";
       straightFullHouse[3] =deckPlayer[3][0] = "Ten";
       deckPlayer[3][1] = "Hearts";
       straightFullHouse[4] = deckPlayer[4][0] = "Nine";
       deckPlayer[4][1] = "Hearts";*/

       // 4 of kind
       /*straightFullHouse[0] = deckPlayer[0][0] = "King";
       deckPlayer[0][1] = "Hearts";
       straightFullHouse[1] =deckPlayer[1][0] = "King";
       deckPlayer[1][1] = "Hearts";
       straightFullHouse[2] =deckPlayer[2][0] = "King";
       deckPlayer[2][1] = "Hearts";
       straightFullHouse[3] =deckPlayer[3][0] = "King";
       deckPlayer[3][1] = "Hearts";
       straightFullHouse[4] = deckPlayer[4][0] = "Nine";
       deckPlayer[4][1] = "Spades";*/

       // full house
       /*straightFullHouse[0] = deckPlayer[0][0] = "King";
       deckPlayer[0][1] = "Hearts";
       straightFullHouse[1] =deckPlayer[1][0] = "King";
       deckPlayer[1][1] = "Hearts";
       straightFullHouse[2] =deckPlayer[2][0] = "King";
       deckPlayer[2][1] = "Hearts";
       straightFullHouse[3] =deckPlayer[3][0] = "Nine";
       deckPlayer[3][1] = "Hearts";
       straightFullHouse[4] = deckPlayer[4][0] = "Nine";
       deckPlayer[4][1] = "Spades";*/

       // flush
       /*straightFullHouse[0] = deckPlayer[0][0] = "King";
       deckPlayer[0][1] = "Hearts";
       straightFullHouse[1] =deckPlayer[1][0] = "Queen";
       deckPlayer[1][1] = "Hearts";
       straightFullHouse[2] =deckPlayer[2][0] = "Nine";
       deckPlayer[2][1] = "Hearts";
       straightFullHouse[3] =deckPlayer[3][0] = "Seven";
       deckPlayer[3][1] = "Hearts";
       straightFullHouse[4] = deckPlayer[4][0] = "Two";
       deckPlayer[4][1] = "Hearts";*/

       // straight
       /*straightFullHouse[0] = deckPlayer[0][0] = "King";
       deckPlayer[0][1] = "Hearts";
       straightFullHouse[1] =deckPlayer[1][0] = "Queen";
       deckPlayer[1][1] = "Spades";
       straightFullHouse[2] =deckPlayer[2][0] = "Jack";
       deckPlayer[2][1] = "Diamonds";
       straightFullHouse[3] =deckPlayer[3][0] = "Ten";
       deckPlayer[3][1] = "Clubs";
       straightFullHouse[4] = deckPlayer[4][0] = "Nine";
       deckPlayer[4][1] = "Spades";*/

       // 3 of kind
       /*straightFullHouse[0] = deckPlayer[0][0] = "King";
       deckPlayer[0][1] = "Hearts";
       straightFullHouse[1] =deckPlayer[1][0] = "King";
       deckPlayer[1][1] = "Spades";
       straightFullHouse[2] =deckPlayer[2][0] = "King";
       deckPlayer[2][1] = "Diamonds";
       straightFullHouse[3] =deckPlayer[3][0] = "Ten";
       deckPlayer[3][1] = "Clubs";
       straightFullHouse[4] = deckPlayer[4][0] = "Nine";
       deckPlayer[4][1] = "Spades";*/

       // 2 pair
       /*straightFullHouse[0] = deckPlayer[0][0] = "King";
       deckPlayer[0][1] = "Hearts";
       straightFullHouse[1] =deckPlayer[1][0] = "King";
       deckPlayer[1][1] = "Spades";
       straightFullHouse[2] =deckPlayer[2][0] = "Queen";
       deckPlayer[2][1] = "Diamonds";
       straightFullHouse[3] =deckPlayer[3][0] = "Queen";
       deckPlayer[3][1] = "Clubs";
       straightFullHouse[4] = deckPlayer[4][0] = "Nine";
       deckPlayer[4][1] = "Spades";*/

       // one pair
       /*straightFullHouse[0] = deckPlayer[0][0] = "King";
       deckPlayer[0][1] = "Hearts";
       straightFullHouse[1] =deckPlayer[1][0] = "King";
       deckPlayer[1][1] = "Spades";
       straightFullHouse[2] =deckPlayer[2][0] = "Queen";
       deckPlayer[2][1] = "Diamonds";
       straightFullHouse[3] =deckPlayer[3][0] = "Jack";
       deckPlayer[3][1] = "Clubs";
       straightFullHouse[4] = deckPlayer[4][0] = "Nine";
       deckPlayer[4][1] = "Spades";*/

       //no pair

       /*straightFullHouse[0] = deckPlayer[0][0] = "King";
       deckPlayer[0][1] = "Hearts";
       straightFullHouse[1] =deckPlayer[1][0] = "Queen";
       deckPlayer[1][1] = "Spades";
       straightFullHouse[2] =deckPlayer[2][0] = "Nine";
       deckPlayer[2][1] = "Diamonds";
       straightFullHouse[3] =deckPlayer[3][0] = "Four";
       deckPlayer[3][1] = "Clubs";
       straightFullHouse[4] = deckPlayer[4][0] = "One";
       deckPlayer[4][1] = "Spades";*/

       //sort the cards ascending to check if there is a straight
       for(int i=0; i<=4;i++)
           for(int j=0; j<=4;j++)
               for(int k=0; k<13; k++){
                    if(faces[k].equals(straightFullHouse[j])){
                        temp = straightFullHouse[i];
                        straightFullHouse[i] = straightFullHouse[j];
                        straightFullHouse[j] = temp;
                    
                    }
               
               }
       
        // check the following cards after sorting and check if there is a straight
        for(int k=0; k<13; k++)
            if(faces[k].equals(straightFullHouse[0]))
                    if((faces[k+1].equals(straightFullHouse[1])) && (faces[k+2].equals(straightFullHouse[2])) && (faces[k+3].equals(straightFullHouse[3])) && (faces[k+4].equals(straightFullHouse[4])))

                        returnAnalysis = returnAnalysis + " A Straight";
         

        for(int i=0;i<4;i++){
           kind=pair=face=0;
                     
           for(int j=i+1;j<5;j++){
               if(deckPlayer[i][0].equals(deckPlayer[j][0])){
                   face++;
                   
                   if(face==1)
                       pair++;
                   else
                       pair=0;
               }
               
               if(deckPlayer[i][1].equals(deckPlayer[j][1]))
                   kind++;
           }
           
           if ((face >= 2) && (faceCheck==0)){
                returnAnalysis=(face+1) + " of "+deckPlayer[i][0];
                faceCheck=1;
           }
           else
                if (pair >= 1)
                    if(pair==1)

                        if(returnAnalysis.equals(""))
                            returnAnalysis="A Pair of "+ deckPlayer[i][0];
                        else if(faceCheck==0)
                                returnAnalysis = "2 pairs";
                        else if(!returnAnalysis.contains(deckPlayer[i][0]))
                                 returnAnalysis = returnAnalysis +"\n"+ "A Pair of "+ deckPlayer[i][0];
           
           if (kind == 4)

               returnAnalysis = returnAnalysis + " A Flush of " + deckPlayer[i][1];

       }

       //check if there is a full house hand
       if (((straightFullHouse[0].equals(straightFullHouse[1])) && (straightFullHouse[2].equals(straightFullHouse[3])) && (straightFullHouse[2].equals(straightFullHouse[4])))
               || ((straightFullHouse[0].equals(straightFullHouse[1])) && (straightFullHouse[0].equals(straightFullHouse[2])) && (straightFullHouse[3].equals(straightFullHouse[4]))))

           returnAnalysis = "A Full House";

       if(returnAnalysis=="")
            returnAnalysis="none";

       if ((returnAnalysis.indexOf("Flush") !=-1) && ((returnAnalysis.indexOf("Straight") !=-1)))
            if(deckPlayer[0][0]=="Ace")
                returnAnalysis = "A Royal Flush";
            else
                returnAnalysis="A Straight Flush";

       return returnAnalysis;
   }
   

} // end class DeckOfCards

