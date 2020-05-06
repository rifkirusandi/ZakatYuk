package com.si410702.java.zakatyuk.model;

public class TopUp {

    private String topUpId, topUpName, topUpCode, topUpValue, topUpUserId, topUpDate, topUpTime;

    public TopUp() {

    }

    public TopUp(String topUpId, String topUpName, String topUpCode, String topUpValue, String topUpUserId, String topUpDate, String topUpTime) {
        this.topUpId = topUpId;
        this.topUpName = topUpName;
        this.topUpCode = topUpCode;
        this.topUpValue = topUpValue;
        this.topUpUserId = topUpUserId;
        this.topUpDate = topUpDate;
        this.topUpTime = topUpTime;
    }

    public String getTopUpId() {
        return topUpId;
    }

    public String getTopUpName() {
        return topUpName;
    }

    public String getTopUpCode() {
        return topUpCode;
    }

    public String getTopUpValue() {
        return topUpValue;
    }

    public String getTopUpUserId() {
        return topUpUserId;
    }

    public String getTopUpDate() {
        return topUpDate;
    }

    public String getTopUpTime() {
        return topUpTime;
    }
}
