package com.zjut.pojo;

/**
 * 题目类
 */
public class Title {
    private int itemId;
    private String content;
    private String answer;
    private int courseId;
    private String courseName;
    private String difficulty;
    private int userId;
    private String userName;
    private String time;
    private String picture;
    private int lockd;
    private int labelId;
    private String labelContent;
    private String type;

    public Title(int itemId, String content, String answer, int courseId, String difficulty, int userId, String time, String picture, int lockd, int labelId, String type) {
        this.itemId = itemId;
        this.content = content;
        this.answer = answer;
        this.courseId = courseId;
        this.difficulty = difficulty;
        this.userId = userId;
        this.time = time;
        this.picture = picture;
        this.lockd = lockd;
        this.labelId = labelId;
        this.type = type;
    }

    public Title(String content, String answer, int courseId, String difficulty, int userId, String time, String picture, int lockd, int labelId, String type) {
        this.content = content;
        this.answer = answer;
        this.courseId = courseId;
        this.difficulty = difficulty;
        this.userId = userId;
        this.time = time;
        this.picture = picture;
        this.lockd = lockd;
        this.labelId = labelId;
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

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLabelContent() {
        return labelContent;
    }

    public void setLabelContent(String labelContent) {
        this.labelContent = labelContent;
    }

    @Override
    public String toString() {
        return "Title{" +
                "itemId=" + itemId +
                ", content='" + content + '\'' +
                ", answer='" + answer + '\'' +
                ", courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", time='" + time + '\'' +
                ", picture='" + picture + '\'' +
                ", lockd=" + lockd +
                ", labelId=" + labelId +
                ", labelContent='" + labelContent + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
