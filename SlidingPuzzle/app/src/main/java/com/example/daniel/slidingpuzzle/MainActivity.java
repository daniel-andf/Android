package com.example.daniel.slidingpuzzle;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static GestureDetectGridView mGridView;

    private static final int COLUMNS = 4;
    private static final int DIMENSIONS = COLUMNS * COLUMNS;

    private static int mColumnWidth, mColumnHeight;

    public static final String up = "up";
    public static final String down = "down";
    public static final String left = "left";
    public static final String right = "right";

    private static String[] tileList;

    private static TextView movesText;
    private static TextView status;
    private static int moves=0;

    private static Button newGame;
    private static Button solvePuzzle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        scramble();

        setDimensions();

        movesText = (TextView) findViewById(R.id.moves);
        movesText.setText("Moves so far: " + moves);
        status = (TextView) findViewById(R.id.status);

        newGame = (Button) findViewById(R.id.newGame);
        solvePuzzle = (Button) findViewById(R.id.solvePuzzle);

        newGame.setClickable(false);
        newGame.setTextColor(Color.rgb(192,192,192));
        solvePuzzle.setClickable(false);
        solvePuzzle.setTextColor(Color.rgb(192,192,192));

    }

    private void init() {
        mGridView = (GestureDetectGridView) findViewById(R.id.grid);
        mGridView.setNumColumns(COLUMNS);

        tileList = new String[DIMENSIONS];
        for (int i = 0; i < DIMENSIONS; i++) {
            tileList[i] = String.valueOf(i);
        }
    }

    private void scramble() {
        int index;
        String temp;
        Random random = new Random();

        for (int i = tileList.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = tileList[index];
            tileList[index] = tileList[i];
            tileList[i] = temp;
        }
    }

    private void setDimensions() {
        ViewTreeObserver vto = mGridView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                mGridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int displayWidth = mGridView.getMeasuredWidth();
                int displayHeight = mGridView.getMeasuredHeight();

                int statusbarHeight = getStatusBarHeight(getApplicationContext());
                int requiredHeight = displayHeight - statusbarHeight;

                mColumnWidth = displayWidth / COLUMNS;
                mColumnHeight = requiredHeight / COLUMNS;

                display(getApplicationContext());
            }
        });
    }

    private int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");

        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }

        return result;
    }

    private static void display(Context context) {
        ArrayList<Button> buttons = new ArrayList<>();
        Button button;

        for (int i = 0; i < tileList.length; i++) {
            button = new Button(context);

            if (tileList[i].equals("0"))
                button.setBackgroundResource(R.drawable.img1);
            else if (tileList[i].equals("1"))
                button.setBackgroundResource(R.drawable.img2);
            else if (tileList[i].equals("2"))
                button.setBackgroundResource(R.drawable.img3);
            else if (tileList[i].equals("3"))
                button.setBackgroundResource(R.drawable.img4);
            else if (tileList[i].equals("4"))
                button.setBackgroundResource(R.drawable.img5);
            else if (tileList[i].equals("5"))
                button.setBackgroundResource(R.drawable.img6);
            else if (tileList[i].equals("6"))
                button.setBackgroundResource(R.drawable.img7);
            else if (tileList[i].equals("7"))
                button.setBackgroundResource(R.drawable.img8);
            else if (tileList[i].equals("8"))
                button.setBackgroundResource(R.drawable.img9);
            else if (tileList[i].equals("9"))
                button.setBackgroundResource(R.drawable.img10);
            else if (tileList[i].equals("10"))
                button.setBackgroundResource(R.drawable.img11);
            else if (tileList[i].equals("11"))
                button.setBackgroundResource(R.drawable.img12);
            else if (tileList[i].equals("12"))
                button.setBackgroundResource(R.drawable.img13);
            else if (tileList[i].equals("13"))
                button.setBackgroundResource(R.drawable.img14);
            else if (tileList[i].equals("14"))
                button.setBackgroundResource(R.drawable.img15);
           /* else if (tileList[i].equals("15"))
                button.setBackgroundResource(R.drawable.img16);*/


            buttons.add(button);
        }

        mGridView.setAdapter(new CustomAdapter(buttons, mColumnWidth, mColumnHeight));
    }

    private static void swap(Context context, int currentPosition, int swap) {
        String newPosition = tileList[currentPosition + swap];
        tileList[currentPosition + swap] = tileList[currentPosition];
        tileList[currentPosition] = newPosition;
        display(context);

        if (isSolved()) Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
    }

    public static void moveTiles(Context context, String direction, int position) {

        // Upper-left-corner tile
        if (position == 0) {

            if (direction.equals(right)) swap(context, position, 1);
            else if (direction.equals(down)) swap(context, position, COLUMNS);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Upper-center tiles
        } else if (position > 0 && position < COLUMNS - 1) {
            if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(down)) swap(context, position, COLUMNS);
            else if (direction.equals(right)) swap(context, position, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Upper-right-corner tile
        } else if (position == COLUMNS - 1) {
            if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(down)) swap(context, position, COLUMNS);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Left-side tiles
        } else if (position > COLUMNS - 1 && position < DIMENSIONS - COLUMNS &&
                position % COLUMNS == 0) {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(right)) swap(context, position, 1);
            else if (direction.equals(down)) swap(context, position, COLUMNS);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Right-side AND bottom-right-corner tiles
        } else if (position == COLUMNS * 2 - 1 || position == COLUMNS * 3 - 1) {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(down)) {

                // Tolerates only the right-side tiles to swap downwards as opposed to the bottom-
                // right-corner tile.
                if (position <= DIMENSIONS - COLUMNS - 1) swap(context, position,
                        COLUMNS);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
            } else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Bottom-left corner tile
        } else if (position == DIMENSIONS - COLUMNS) {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(right)) swap(context, position, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Bottom-center tiles
        } else if (position < DIMENSIONS - 1 && position > DIMENSIONS - COLUMNS) {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(right)) swap(context, position, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Center tiles
        } else {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(right)) swap(context, position, 1);
            else swap(context, position, COLUMNS);
        }

        moves++;
        movesText.setText("Moves so far: " + moves);

        if (moves >=8){
            solvePuzzle.setClickable(true);
            solvePuzzle.setTextColor(Color.rgb(0,0,0));
        }
    }

    private static boolean isSolved() {
        boolean solved = false;

        for (int i = 0; i < tileList.length; i++) {
            if (tileList[i].equals(String.valueOf(i))) {
                solved = true;
                status.setText("You solved the puzzle in "+moves+" moves!");
            } else {
                solved = false;
                status.setText("");
                break;
            }
        }

        return solved;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.newGame:
                init();
                scramble();
                display(getApplicationContext());
                solvePuzzle.setClickable(false);
                solvePuzzle.setTextColor(Color.rgb(192,192,192));
                moves=0;
                movesText.setText("Moves so far: " + moves);
                break;

            case R.id.solvePuzzle:
                init();
                display(getApplicationContext());
                newGame.setClickable(true);
                newGame.setTextColor(Color.rgb(0,0,0));
                solvePuzzle.setClickable(false);
                solvePuzzle.setTextColor(Color.rgb(192,192,192));
                moves=0;
                movesText.setText("Moves so far: " + moves);
                break;
        }
    }
}
