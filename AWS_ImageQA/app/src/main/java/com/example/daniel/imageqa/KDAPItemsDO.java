package com.example.daniel.imageqa;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;
import java.util.Map;
import java.util.Set;

@DynamoDBTable(tableName = "imageqa-mobilehub-784076758-KDAP_Items")

public class KDAPItemsDO {
    private Double _iD;
    private String _answer;
    private String _imageUrl;
    private String _label;
    private String _option1;
    private String _option2;
    private String _option3;
    private String _option4;
    private String _question;

    @DynamoDBHashKey(attributeName = "ID")
    @DynamoDBAttribute(attributeName = "ID")
    public Double getID() {
        return _iD;
    }

    public void setID(final Double _iD) {
        this._iD = _iD;
    }
    @DynamoDBAttribute(attributeName = "answer")
    public String getAnswer() {
        return _answer;
    }

    public void setAnswer(final String _answer) {
        this._answer = _answer;
    }
    @DynamoDBAttribute(attributeName = "imageUrl")
    public String getImageUrl() {
        return _imageUrl;
    }

    public void setImageUrl(final String _imageUrl) {
        this._imageUrl = _imageUrl;
    }
    @DynamoDBAttribute(attributeName = "label")
    public String getLabel() {
        return _label;
    }

    public void setLabel(final String _label) {
        this._label = _label;
    }
    @DynamoDBAttribute(attributeName = "option1")
    public String getOption1() {
        return _option1;
    }

    public void setOption1(final String _option1) {
        this._option1 = _option1;
    }
    @DynamoDBAttribute(attributeName = "option2")
    public String getOption2() {
        return _option2;
    }

    public void setOption2(final String _option2) {
        this._option2 = _option2;
    }
    @DynamoDBAttribute(attributeName = "option3")
    public String getOption3() {
        return _option3;
    }

    public void setOption3(final String _option3) {
        this._option3 = _option3;
    }
    @DynamoDBAttribute(attributeName = "option4")
    public String getOption4() {
        return _option4;
    }

    public void setOption4(final String _option4) {
        this._option4 = _option4;
    }
    @DynamoDBAttribute(attributeName = "question")
    public String getQuestion() {
        return _question;
    }

    public void setQuestion(final String _question) {
        this._question = _question;
    }

}
