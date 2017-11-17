package com.example.daniel.tictactoe;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button11;
    Button button12;
    Button button13;
    Button button21;
    Button button22;
    Button button23;
    Button button31;
    Button button32;
    Button button33;
    Button newGameBtt;

    TextView statusText;

    String player;

    String ticTacToe[][];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button11 = (Button) findViewById(R.id.button11);
        button12 = (Button) findViewById(R.id.button12);
        button13 = (Button) findViewById(R.id.button13);
        button21 = (Button) findViewById(R.id.button21);
        button22 = (Button) findViewById(R.id.button22);
        button23 = (Button) findViewById(R.id.button23);
        button31 = (Button) findViewById(R.id.button31);
        button32 = (Button) findViewById(R.id.button32);
        button33 = (Button) findViewById(R.id.button33);
        newGameBtt = (Button) findViewById(R.id.newGame);

        statusText = (TextView) findViewById(R.id.statusText);
        statusText.setText("Player X's turn");
        ticTacToe = new String[3][3];

        newGame();


    }
    @Override
    public void onSaveInstanceState(Bundle state){
        state.putString("button11", (String) button11.getText());
        state.putString("button12", (String) button12.getText());
        state.putString("button13", (String) button13.getText());
        state.putString("button21", (String) button21.getText());
        state.putString("button22", (String) button22.getText());
        state.putString("button23", (String) button23.getText());
        state.putString("button31", (String) button31.getText());
        state.putString("button32", (String) button32.getText());
        state.putString("button33", (String) button33.getText());
        state.putString("statusText", (String) statusText.getText());
        state.putString("player",player);
        state.putString("tictactoe11", ticTacToe[0][0]);
        state.putString("tictactoe12", ticTacToe[0][1]);
        state.putString("tictactoe13", ticTacToe[0][2]);
        state.putString("tictactoe21", ticTacToe[1][0]);
        state.putString("tictactoe22", ticTacToe[1][1]);
        state.putString("tictactoe23", ticTacToe[1][2]);
        state.putString("tictactoe31", ticTacToe[2][0]);
        state.putString("tictactoe32", ticTacToe[2][1]);
        state.putString("tictactoe33", ticTacToe[2][2]);
        super.onSaveInstanceState(state);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedState){
        super.onRestoreInstanceState(savedState);

        button11.setText(savedState.getString("button11"));
        button12.setText(savedState.getString("button12"));
        button13.setText(savedState.getString("button13"));
        button21.setText(savedState.getString("button21"));
        button22.setText(savedState.getString("button22"));
        button23.setText(savedState.getString("button23"));
        button31.setText(savedState.getString("button31"));
        button32.setText(savedState.getString("button32"));
        button33.setText(savedState.getString("button33"));
        statusText.setText(savedState.getString("statusText"));
        ticTacToe[0][0] = savedState.getString("tictactoe11");
        ticTacToe[0][1] = savedState.getString("tictactoe12");
        ticTacToe[0][2] = savedState.getString("tictactoe13");
        ticTacToe[1][0] = savedState.getString("tictactoe21");
        ticTacToe[1][1] = savedState.getString("tictactoe22");
        ticTacToe[1][2] = savedState.getString("tictactoe23");
        ticTacToe[2][0] = savedState.getString("tictactoe31");
        ticTacToe[2][1] = savedState.getString("tictactoe32");
        ticTacToe[2][2] = savedState.getString("tictactoe33");
        player = savedState.getString("player");

    }

    public void newGame(){
        button11.setText("");
        button12.setText("");
        button13.setText("");
        button21.setText("");
        button22.setText("");
        button23.setText("");
        button31.setText("");
        button32.setText("");
        button33.setText("");
        player = "";
        for (int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                ticTacToe[i][j]=""+i+j;

        unblockClick();
    }
    public void blockClick(){
        button11.setClickable(false);
        button12.setClickable(false);
        button13.setClickable(false);
        button21.setClickable(false);
        button22.setClickable(false);
        button23.setClickable(false);
        button31.setClickable(false);
        button32.setClickable(false);
        button33.setClickable(false);
    }

    public void unblockClick() {
        button11.setClickable(true);
        button12.setClickable(true);
        button13.setClickable(true);
        button21.setClickable(true);
        button22.setClickable(true);
        button23.setClickable(true);
        button31.setClickable(true);
        button32.setClickable(true);
        button33.setClickable(true);
    }

    public void checkVictory(){

        int checkTied = 0;

        //check rows
        for(int i=0 ;i<3;i++){
            if ((ticTacToe[i][0].equals(ticTacToe[i][1])) && (ticTacToe[i][0].equals(ticTacToe[i][2]))){
                statusText.setText("Player "+ player+" won!!!");
                blockClick();

            }

        }
        //check cols
        for(int i=0 ;i<3;i++) {

            if ((ticTacToe[0][i].equals(ticTacToe[1][i])) && (ticTacToe[0][i].equals(ticTacToe[2][i]))){
                statusText.setText("Player "+ player+" won!!!");
                blockClick();
            }

        }
        //check diagonals
        if ((ticTacToe[0][0].equals(ticTacToe[1][1])) && (ticTacToe[0][0].equals(ticTacToe[2][2])))
        {
            statusText.setText("Player "+ player+" won!!!");
            blockClick();

        }
        else{
            if ((ticTacToe[0][2].equals(ticTacToe[1][1])) && (ticTacToe[0][2].equals(ticTacToe[2][0]))){
                statusText.setText("Player "+ player+" won!!!");
                blockClick();
            }
        }

        //check tied game
        for(int i=0 ;i<3;i++)
            for(int j=0 ;j<3;j++) {
                if( (ticTacToe[i][j].equals("X")) || (ticTacToe[i][j].equals("O"))){
                    checkTied = 1;
                }
                else{
                    return;
                }
            }

        if (checkTied == 1){
            statusText.setText("Game tied!!!");
            blockClick();
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button11:
                if (button11.getText().equals("")){
                    if (player.equals("") || player.equals("O")) {
                        button11.setText("X");
                        statusText.setText("Player O's turn");
                        ticTacToe[0][0] = "X";
                        player = "X";
                    } else {
                        button11.setText("O");
                        statusText.setText("Player X's turn");
                        ticTacToe[0][0] = "O";
                        player = "O";
                    }

                }
                else{
                    statusText.setText("Please, try another spot!!!");
                }
                break;
            case R.id.button12:
                if (button12.getText().equals("")){
                    if (player.equals("") || player.equals("O")) {
                        button12.setText("X");
                        statusText.setText("Player O's turn");
                        ticTacToe[0][1] = "X";
                        player = "X";
                    } else {
                        button12.setText("O");
                        statusText.setText("Player X's turn");
                        ticTacToe[0][1] = "O";
                        player = "O";
                    }

                }
                else{
                    statusText.setText("Please, try another spot!!!");
                }
                break;
            case R.id.button13:
                if (button13.getText().equals("")){
                    if (player.equals("") || player.equals("O")) {
                        button13.setText("X");
                        statusText.setText("Player O's turn");
                        ticTacToe[0][2] = "X";
                        player = "X";
                    } else {
                        button13.setText("O");
                        statusText.setText("Player X's turn");
                        ticTacToe[0][2] = "O";
                        player = "O";
                    }

                }
                else{
                    statusText.setText("Please, try another spot!!!");
                }
                break;
            case R.id.button21:
                if (button21.getText().equals("")){
                    if (player.equals("") || player.equals("O")) {
                        button21.setText("X");
                        statusText.setText("Player O's turn");
                        ticTacToe[1][0] = "X";
                        player = "X";
                    } else {
                        button21.setText("O");
                        statusText.setText("Player X's turn");
                        ticTacToe[1][0] = "O";
                        player = "O";
                    }

                }
                else{
                    statusText.setText("Please, try another spot!!!");
                }
                break;
            case R.id.button22:
                if (button22.getText().equals("")){
                    if (player.equals("") || player.equals("O")) {
                        button22.setText("X");
                        statusText.setText("Player O's turn");
                        ticTacToe[1][1] = "X";
                        player = "X";
                    } else {
                        button22.setText("O");
                        statusText.setText("Player X's turn");
                        ticTacToe[1][1] = "O";
                        player = "O";
                    }

                }
                else{
                    statusText.setText("Please, try another spot!!!");
                }
                break;
            case R.id.button23:
                if (button23.getText().equals("")){
                    if (player.equals("") || player.equals("O")) {
                        button23.setText("X");
                        statusText.setText("Player O's turn");
                        ticTacToe[1][2] = "X";
                        player = "X";
                    } else {
                        button23.setText("O");
                        statusText.setText("Player X's turn");
                        ticTacToe[1][2] = "O";
                        player = "O";
                    }

                }
                else{
                    statusText.setText("Please, try another spot!!!");
                }
                break;
            case R.id.button31:
                if (button31.getText().equals("")){
                    if (player.equals("") || player.equals("O")) {
                        button31.setText("X");
                        statusText.setText("Player O's turn");
                        ticTacToe[2][0] = "X";
                        player = "X";
                    } else {
                        button31.setText("O");
                        statusText.setText("Player X's turn");
                        ticTacToe[2][0] = "O";
                        player = "O";
                    }

                }
                else{
                    statusText.setText("Please, try another spot!!!");
                }
                break;
            case R.id.button32:
                if (button32.getText().equals("")){
                    if (player.equals("") || player.equals("O")) {
                        button32.setText("X");
                        statusText.setText("Player O's turn");
                        ticTacToe[2][1] = "X";
                        player = "X";
                    } else {
                        button32.setText("O");
                        statusText.setText("Player X's turn");
                        ticTacToe[2][1] = "O";
                        player = "O";
                    }

                }
                else{
                    statusText.setText("Please, try another spot!!!");
                }
                break;
            case R.id.button33:
                if (button33.getText().equals("")){
                    if (player.equals("") || player.equals("O")) {
                        button33.setText("X");
                        statusText.setText("Player O's turn");
                        ticTacToe[2][2] = "X";
                        player = "X";
                    } else {
                        button33.setText("O");
                        statusText.setText("Player X's turn");
                        ticTacToe[2][2] = "O";
                        player = "O";
                    }

                }
                else{
                    statusText.setText("Please, try another spot!!!");
                }
                break;
            case R.id.newGame:{
                newGame();
                break;
            }
        }

        checkVictory();
    }

}
