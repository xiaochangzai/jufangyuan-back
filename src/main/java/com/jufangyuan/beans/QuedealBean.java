package com.jufangyuan.beans;

public class QuedealBean extends UserBean{
    private int vrId;
    private int giveId;
    private String userId;
    private String dealTime;
    private String answers;
    private float score;

    public int getVrId() {
        return vrId;
    }

    public void setVrId(int vrId) {
        this.vrId = vrId;
    }

    public int getGiveId() {
        return giveId;
    }

    public void setGiveId(int giveId) {
        this.giveId = giveId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDealTime() {
        return dealTime;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
