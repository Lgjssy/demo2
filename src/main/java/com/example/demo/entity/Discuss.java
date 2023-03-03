package com.example.demo.entity;

public class Discuss {
    private String article_id;
    private String user_id;
    private String content;
    private String date;
    private String userPhoto;

    public Discuss(String article_id, String user_id, String content, String date, String userPhoto) {
        this.article_id = article_id;
        this.user_id = user_id;
        this.content = content;
        this.date = date;
        this.userPhoto = userPhoto;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Discuss{" +
                "article_id='" + article_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                ", userPhoto='" + userPhoto + '\'' +
                '}';
    }
}
