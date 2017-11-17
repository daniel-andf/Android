package com.amazonaws.models.nosql;

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
    @DynamoDBAttribute(attributeName = "question")
    public String getQuestion() {
        return _question;
    }

    public void setQuestion(final String _question) {
        this._question = _question;
    }

}
