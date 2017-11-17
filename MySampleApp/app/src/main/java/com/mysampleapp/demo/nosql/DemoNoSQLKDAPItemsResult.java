package com.mysampleapp.demo.nosql;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amazonaws.AmazonClientException;
import com.amazonaws.ClientConfiguration;

import com.amazonaws.mobile.auth.core.IdentityManager;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.models.nosql.KDAPItemsDO;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.mysampleapp.Application;

import java.util.Set;

public class DemoNoSQLKDAPItemsResult implements DemoNoSQLResult {
    private static final int KEY_TEXT_COLOR = 0xFF333333;
    private final KDAPItemsDO result;
    private DynamoDBMapper mapper;

    DemoNoSQLKDAPItemsResult(final KDAPItemsDO result) {
        this.result = result;
        AmazonDynamoDBClient dynamoDBClient =
                new AmazonDynamoDBClient(IdentityManager.getDefaultIdentityManager()
                        .getCredentialsProvider(), new ClientConfiguration());
        this.mapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(Application.awsConfiguration)
                .build();
    }
    @Override
    public void updateItem() {
        final String originalValue = result.getAnswer();
        result.setAnswer(DemoSampleDataGenerator.getRandomSampleString("answer"));
        try {
            mapper.save(result);
        } catch (final AmazonClientException ex) {
            // Restore original data if save fails, and re-throw.
            result.setAnswer(originalValue);
            throw ex;
        }
    }

    @Override
    public void deleteItem() {
        mapper.delete(result);
    }

    private void setKeyTextViewStyle(final TextView textView) {
        textView.setTextColor(KEY_TEXT_COLOR);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(dp(5), dp(2), dp(5), 0);
        textView.setLayoutParams(layoutParams);
    }

    /**
     * @param dp number of design pixels.
     * @return number of pixels corresponding to the desired design pixels.
     */
    private int dp(int dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
    private void setValueTextViewStyle(final TextView textView) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(dp(15), 0, dp(15), dp(2));
        textView.setLayoutParams(layoutParams);
    }

    private void setKeyAndValueTextViewStyles(final TextView keyTextView, final TextView valueTextView) {
        setKeyTextViewStyle(keyTextView);
        setValueTextViewStyle(valueTextView);
    }

    private static String bytesToHexString(byte[] bytes) {
        final StringBuilder builder = new StringBuilder();
        builder.append(String.format("%02X", bytes[0]));
        for(int index = 1; index < bytes.length; index++) {
            builder.append(String.format(" %02X", bytes[index]));
        }
        return builder.toString();
    }

    private static String byteSetsToHexStrings(Set<byte[]> bytesSet) {
        final StringBuilder builder = new StringBuilder();
        int index = 0;
        for (byte[] bytes : bytesSet) {
            builder.append(String.format("%d: ", ++index));
            builder.append(bytesToHexString(bytes));
            if (index < bytesSet.size()) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    @Override
    public View getView(final Context context, final View convertView, int position) {
        final LinearLayout layout;
        final TextView resultNumberTextView;
        final TextView iDKeyTextView;
        final TextView iDValueTextView;
        final TextView answerKeyTextView;
        final TextView answerValueTextView;
        final TextView imageUrlKeyTextView;
        final TextView imageUrlValueTextView;
        final TextView labelKeyTextView;
        final TextView labelValueTextView;
        final TextView questionKeyTextView;
        final TextView questionValueTextView;
        if (convertView == null) {
            layout = new LinearLayout(context);
            layout.setOrientation(LinearLayout.VERTICAL);
            resultNumberTextView = new TextView(context);
            resultNumberTextView.setGravity(Gravity.CENTER_HORIZONTAL);
            layout.addView(resultNumberTextView);


            iDKeyTextView = new TextView(context);
            iDValueTextView = new TextView(context);
            setKeyAndValueTextViewStyles(iDKeyTextView, iDValueTextView);
            layout.addView(iDKeyTextView);
            layout.addView(iDValueTextView);

            answerKeyTextView = new TextView(context);
            answerValueTextView = new TextView(context);
            setKeyAndValueTextViewStyles(answerKeyTextView, answerValueTextView);
            layout.addView(answerKeyTextView);
            layout.addView(answerValueTextView);

            imageUrlKeyTextView = new TextView(context);
            imageUrlValueTextView = new TextView(context);
            setKeyAndValueTextViewStyles(imageUrlKeyTextView, imageUrlValueTextView);
            layout.addView(imageUrlKeyTextView);
            layout.addView(imageUrlValueTextView);

            labelKeyTextView = new TextView(context);
            labelValueTextView = new TextView(context);
            setKeyAndValueTextViewStyles(labelKeyTextView, labelValueTextView);
            layout.addView(labelKeyTextView);
            layout.addView(labelValueTextView);

            questionKeyTextView = new TextView(context);
            questionValueTextView = new TextView(context);
            setKeyAndValueTextViewStyles(questionKeyTextView, questionValueTextView);
            layout.addView(questionKeyTextView);
            layout.addView(questionValueTextView);
        } else {
            layout = (LinearLayout) convertView;
            resultNumberTextView = (TextView) layout.getChildAt(0);

            iDKeyTextView = (TextView) layout.getChildAt(1);
            iDValueTextView = (TextView) layout.getChildAt(2);

            answerKeyTextView = (TextView) layout.getChildAt(3);
            answerValueTextView = (TextView) layout.getChildAt(4);

            imageUrlKeyTextView = (TextView) layout.getChildAt(5);
            imageUrlValueTextView = (TextView) layout.getChildAt(6);

            labelKeyTextView = (TextView) layout.getChildAt(7);
            labelValueTextView = (TextView) layout.getChildAt(8);

            questionKeyTextView = (TextView) layout.getChildAt(9);
            questionValueTextView = (TextView) layout.getChildAt(10);
        }

        resultNumberTextView.setText(String.format("#%d", + position+1));
        iDKeyTextView.setText("ID");
        iDValueTextView.setText("" + result.getID().longValue());
        answerKeyTextView.setText("answer");
        answerValueTextView.setText(result.getAnswer());
        imageUrlKeyTextView.setText("imageUrl");
        imageUrlValueTextView.setText(result.getImageUrl());
        labelKeyTextView.setText("label");
        labelValueTextView.setText(result.getLabel());
        questionKeyTextView.setText("question");
        questionValueTextView.setText(result.getQuestion());
        return layout;
    }
}
