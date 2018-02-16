package com.example.daniel.imageqa;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.RadioGroup;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.mobile.auth.core.IdentityManager;
import com.amazonaws.mobile.auth.core.StartupAuthErrorDetails;
import com.amazonaws.mobile.auth.core.StartupAuthResult;
import com.amazonaws.mobile.auth.core.StartupAuthResultHandler;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBScanExpression;
import com.amazonaws.mobileconnectors.lex.interactionkit.InteractionClient;
import com.amazonaws.mobileconnectors.lex.interactionkit.Response;
import com.amazonaws.mobileconnectors.lex.interactionkit.config.InteractionConfig;
import com.amazonaws.mobileconnectors.lex.interactionkit.continuations.LexServiceContinuation;
import com.amazonaws.mobileconnectors.lex.interactionkit.listeners.AudioPlaybackListener;
import com.amazonaws.mobileconnectors.lex.interactionkit.listeners.InteractionListener;
import com.amazonaws.mobileconnectors.lex.interactionkit.ui.InteractiveVoiceView;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Map;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements StartupAuthResultHandler, View.OnClickListener {

    DynamoDBMapper dynamoDBMapper;
    String userId;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    List<KDAPItemsDO> tableItem;
    TextView questionText;
    TextView answerText;
    RadioGroup radioGroup;
    RadioButton opt1;
    RadioButton opt2;
    RadioButton opt3;
    RadioButton opt4;
    ImageView imageQuestion;
    ArrayList<Integer> questionsDone;
    Random numRam;
    int tableQuestion;
    int questionNum;
    int answersCorrect;
    int countQuestions = 1;

    private AWSCredentialsProvider credentialsProvider;
    private InteractionClient lexInteractionClient;
    private InteractionConfig defaulInteractionConfig;
    private final String BOT_NAME = "KDAPGame";
    private final String BOT_ALIAS = "beta";
    InteractiveVoiceView voiceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionText = findViewById(R.id.questionText);
        opt1= findViewById(R.id.opt1);
        opt2= findViewById(R.id.opt2);
        opt3= findViewById(R.id.opt3);
        opt4= findViewById(R.id.opt4);
        imageQuestion = findViewById(R.id.imageQuestion);
        answerText = findViewById(R.id.answerText);
        radioGroup = findViewById(R.id.radioGroup);

        final IdentityManager identityManager = IdentityManager.getDefaultIdentityManager();

        //identityManager.doStartupAuth(this, this);

        this.userId = identityManager.getCachedUserID();
        this.credentialsProvider = identityManager.getCredentialsProvider();
        this.defaulInteractionConfig = new InteractionConfig(BOT_NAME, BOT_ALIAS, this.userId);
        AmazonDynamoDBClient dynamoDBClient =
                new AmazonDynamoDBClient(identityManager.getCredentialsProvider(), new ClientConfiguration());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AppInit.awsConfiguration)
                .build();

        readTable();

        numRam = new Random();
        questionNum = 1;
        answersCorrect=0;
        questionsDone = new ArrayList<Integer>();

        initBot();
    }

    public void fillQuestion() {
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                int maxIndex = tableItem.size();
                int checkQuestion=0;

                tableQuestion = numRam.nextInt(maxIndex);
                if (questionsDone.size() == 0)
                    questionsDone.add(tableQuestion);
                else{
                    while(checkQuestion == 0) {

                        checkQuestion=1;
                        for (int i = 0; i < questionsDone.size(); i++) {
                            if (tableQuestion == questionsDone.get(i)){
                                tableQuestion = numRam.nextInt(maxIndex);
                                checkQuestion = 0;
                            }
                        }


                    }
                    questionsDone.add(tableQuestion);
                }

                lexInteractionClient.textInForAudioOut(tableItem.get(tableQuestion).getQuestion(), null);

                opt1.setText(tableItem.get(tableQuestion).getOption1());
                opt2.setText(tableItem.get(tableQuestion).getOption2());
                opt3.setText(tableItem.get(tableQuestion).getOption3());
                opt4.setText(tableItem.get(tableQuestion).getOption4());

                Picasso.with(getApplicationContext())
                        .load(tableItem.get(tableQuestion).getImageUrl())
                        .into(imageQuestion);
                questionNum++;
            }
        });
    }

    public void readTable() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
                tableItem = dynamoDBMapper.scan(KDAPItemsDO.class,scanExpression);
                fillQuestion();
                // Item read

            }
        }).start();
    }



    public void checkAnswer(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                String answer = tableItem.get(tableQuestion).getAnswer();
                int radioChecked = 0;
                if (opt1.isChecked()){
                    if (answer.equals("option1")) {
                        answerText.setTextColor(Color.rgb(0,255,0));
                        answerText.setText("Correct!!!");
                        answersCorrect++;

                    }
                    else {
                        answerText.setTextColor(Color.rgb(255,0,0));
                        answerText.setText("Incorrect!!!");


                    }
                }else if (opt2.isChecked()){
                    if (answer.equals("option2")) {
                        answerText.setTextColor(Color.rgb(0,255,0));
                        answerText.setText("Correct!!!");
                        answersCorrect++;

                    }
                    else {
                        answerText.setTextColor(Color.rgb(255,0,0));
                        answerText.setText("Incorrect!!!");


                    }
                }else if (opt3.isChecked()){
                    if (answer.equals("option3")) {
                        answerText.setTextColor(Color.rgb(0,255,0));
                        answerText.setText("Correct!!!");
                        answersCorrect++;

                    }
                    else {
                        answerText.setTextColor(Color.rgb(255,0,0));
                        answerText.setText("Incorrect!!!");


                    }
                }else if (opt4.isChecked()){
                    if (answer.equals("option4")) {
                        answerText.setTextColor(Color.rgb(0,255,0));
                        answerText.setText("Correct!!!");
                        answersCorrect++;

                    }
                    else {
                        answerText.setTextColor(Color.rgb(255,0,0));
                        answerText.setText("Incorrect!!!");


                    }
                }else{
                    answerText.setTextColor(Color.rgb(255,0,0));
                    answerText.setText("Please, choose one option!!!");
                    radioChecked=1;
                }


                if ((radioChecked==0) && (countQuestions<tableItem.size())){

                    fillQuestion();
                    radioGroup.clearCheck();
                }
                else{
                    if (countQuestions == 10){
                        showAlert();
                    }

                }


            }
        });

    }

    private void setAnswerFromResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String opt1Txt = opt1.getText().toString();
                String opt2Txt = opt2.getText().toString();
                String opt3Txt = opt3.getText().toString();
                String opt4Txt = opt4.getText().toString();
                if (opt1.getText().toString().toLowerCase().contains(response.toLowerCase())) {
                    opt1.setChecked(true);
                } else if (opt2.getText().toString().toLowerCase().contains(response.toLowerCase())) {
                    opt2.setChecked(true);
                } else if (opt3.getText().toString().toLowerCase().contains(response.toLowerCase())) {
                    opt3.setChecked(true);
                } else if (opt4.getText().toString().toLowerCase().contains(response.toLowerCase())) {
                    opt4.setChecked(true);
                }

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.answerBtt:
                lexInteractionClient.cancel();
                checkAnswer();
                countQuestions+=1;

                break;
        }

    }

    @Override
    public void onComplete(StartupAuthResult authResult) {
        if (authResult.isUserAnonymous()) {
            Log.d(LOG_TAG, "Continuing with unauthenticated (guest) identity.");
        } else {
            final StartupAuthErrorDetails errors = authResult.getErrorDetails();
            Log.e(LOG_TAG, "No Identity could be obtained. Continuing with no identity.",
                    errors.getUnauthenticatedErrorException());
        }
    }

    public void showAlert(){
        final Activity self = this;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder myAlert = new AlertDialog.Builder(self);
                ImageView imageView1 = new ImageView(self);
                imageView1.setImageResource(R.drawable.onestar);
                ImageView imageView2 = new ImageView(self);
                imageView2.setImageResource(R.drawable.twostars);
                ImageView imageView3 = new ImageView(self);
                imageView3.setImageResource(R.drawable.threestars);
                double result = (Double.parseDouble(String.valueOf(answersCorrect))/Double.parseDouble(String.valueOf(tableItem.size())))*100;
                if (result<=40){
                    myAlert.setView(imageView1);
                }
                else if (result>40 && result <=70){
                    myAlert.setView(imageView2);
                }
                else if (result>70) {
                    myAlert.setView(imageView3);
                }
                myAlert.setMessage("Your Score is: " + answersCorrect)
                        .setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Quit Game", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                                System.exit(0);
                            }
                        })
                        .setTitle("ImageQuiz")
                        .setIcon(R.drawable.logo)
                        .create();
                myAlert.show();
            }
        });

    }


    public void initBot() {
        this.voiceView =
                (InteractiveVoiceView) findViewById(R.id.voiceInterface);

        voiceView.setInteractiveVoiceListener(
                new InteractiveVoiceView.InteractiveVoiceListener() {

                    @Override
                    public void dialogReadyForFulfillment(Map<String, String> slots, String intent) {
                        Log.d(LOG_TAG, String.format(Locale.CANADA,
                                "Dialog ready for fulfillment:\n\tIntent: %s\n\tSlots: %s",
                                intent,
                                slots.toString()));
                        final String response = slots.get("breed");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setAnswerFromResponse(response);
                            }
                        });
                    }

                    @Override
                    public void onResponse(Response response) {
//                        Log.d(LOG_TAG, "Bot response: " + response.getTextResponse());
                    }

                    @Override
                    public void onError(String responseText, Exception e) {
                        Log.e(LOG_TAG, "Error: " + responseText, e);
                    }

                });

        voiceView.getViewAdapter().setCredentialProvider(this.credentialsProvider);
        voiceView.getViewAdapter()
                .setInteractionConfig(
                        defaulInteractionConfig);
        voiceView.getViewAdapter()
                .setAwsRegion(getApplicationContext()
                        .getString(R.string.aws_region));

        try {
            lexInteractionClient = new InteractionClient(getApplicationContext(), credentialsProvider, Regions.US_EAST_1,
                    this.defaulInteractionConfig);

            lexInteractionClient.setInteractionListener(new InteractionListener() {
                @Override
                public void onReadyForFulfillment(Response response) {
                }

                @Override
                public void promptUserToRespond(Response response, LexServiceContinuation continuation) {
                    questionText.setText(response.getTextResponse());
                    Log.d(LOG_TAG, "Bot prompt: " + response.getTextResponse());
                }

                @Override
                public void onInteractionError(Response response, Exception e) {
                    Log.d(LOG_TAG, "Bot error: " + e.getMessage());
                }
            });
        } catch (Exception e) {
            Log.d(LOG_TAG, e.getMessage());
        }
    }

}
