package com.jufangyuan.beans;

public class QuestionsBean {
    private int vrId;
    private String title;
    private String answers;
    private String createTime;
    int[] notArr;
    public int getVrId() {
        return vrId;
    }

    public void setVrId(int vrId) {
        this.vrId = vrId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int[] getNotArr() {
        return notArr;
    }

    public void setNotArr(int[] notArr) {
        this.notArr = notArr;
    }
}
