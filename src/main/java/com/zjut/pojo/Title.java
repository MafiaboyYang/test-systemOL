package com.zjut.pojo;

/**
 * 题目类
 */
public class Title {
    private int itemId;
    private String content;
    private String answer;
    private String courseName;
    private String difficulty;
    private String userName;
    private String time;
    private String picture;
    private int lockd;
    private String labelName;
    private String type;

    public Title(int itemId, String content, String answer, String courseName, String difficulty, String userName, String time, String picture, int lockd, String labelName, String type) {
        this.itemId = itemId;
        this.content = content;
        this.answer = answer;
        this.courseName = courseName;
        this.difficulty = difficulty;
        this.userName = userName;
        this.time = time;
        this.picture = picture;
        this.lockd = lockd;
        this.labelName = labelName;
        this.type = type;
    }

    public Title(String content, String answer, String courseName, String difficulty, String userName, String time, String picture, int lockd, String labelName, String type) {
        this.content = content;
        this.answer = answer;
        this.courseName = courseName;
        this.difficulty = difficulty;
        this.userName = userName;
        this.time = time;
        this.picture = picture;
        this.lockd = lockd;
        this.labelName = labelName;
        this.type = type;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }



    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getLockd() {
        return lockd;
    }

    public void setLockd(int lockd) {
        this.lockd = lockd;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
