package com.jufangyuan.beans;

public class QuegiverBean extends UserBean{
    private int vrId;
    private String answers;
    private String createTime;
    private String giverId;
    private float myscore;

    @Override
    public int getVrId() {
        return vrId;
    }

    @Override
    public void setVrId(int vrId) {
        this.vrId = vrId;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    @Override
    public String getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getGiverId() {
        return giverId;
    }

    public void setGiverId(String giverId) {
        this.giverId = giverId;
    }

    public float getMyscore() {
        return myscore;
    }

    public void setMyscore(float myscore) {
        this.myscore = myscore;
    }
}
