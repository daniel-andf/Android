package com.example.daniel.rectanglecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

import static com.example.daniel.rectanglecalculator.R.id.widthTextEditView;

public class MainActivity extends AppCompatActivity {

    private TextView areaTextView;
    private TextView perimeterTextView;
    private double width=0;
    private double height=0;

    private static final NumberFormat numberFormat =
            NumberFormat.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        areaTextView = (TextView) findViewById(R.id.areaTextView);
        perimeterTextView = (TextView) findViewById(R.id.perimeterTextView);

        EditText widthTextEditView =
                (EditText) findViewById(R.id.widthTextEditView);
        widthTextEditView.addTextChangedListener(widthTextEditViewtWatcher);

        EditText heightTextEditView =
                (EditText) findViewById(R.id.heightTextEditView);
        heightTextEditView.addTextChangedListener(heightTextEditViewtWatcher);
    }

    public void calculate(){

        double area = width * height;
        double perimeter = 2*(width+height);

        areaTextView.setText(numberFormat.format(area));
        perimeterTextView.setText(numberFormat.format(perimeter));

    }

    private final TextWatcher widthTextEditViewtWatcher = new TextWatcher(){
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            try {
                width = Double.parseDouble(s.toString());
                calculate();
            }

            catch (NumberFormatException e) {
                width = 0.0;
            }



        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private final TextWatcher heightTextEditViewtWatcher = new TextWatcher(){
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            try {
                height = Double.parseDouble(s.toString());
                calculate();
            }
            catch (NumberFormatException e) {
                height = 0.0;
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}