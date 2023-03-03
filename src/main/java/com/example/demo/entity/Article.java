package com.example.demo.entity;

public class Article {
    private String article_id;
    private String article_introduction;
    private String writer_id;
    private String topic;
    private String article_title;
    private String state;
    private String type;
    private String date;

    @Override
    public String toString() {
        return "Article{" +
                "article_id='" + article_id + '\'' +
                ", article_introduction='" + article_introduction + '\'' +
                ", writer_id='" + writer_id + '\'' +
                ", topic='" + topic + '\'' +
                ", article_title='" + article_title + '\'' +
                ", state='" + state + '\'' +
                ", type='" + type + '\'' +
                ", date='" + date + '\'' +
                ", article_cover='" + article_cover + '\'' +
                '}';
    }

    public String getArticle_cover() {
        return article_cover;
    }

    public void setArticle_cover(String article_cover) {
        this.article_cover = article_cover;
    }

    public Article(String article_introduction, String writer_id, String topic, String article_title, String state, String type, String date, String article_cover) {

        this.article_introduction = article_introduction;
        this.writer_id = writer_id;
        this.topic = topic;
        this.article_title = article_title;
        this.state = state;
        this.type = type;
        this.date = date;
        this.article_cover = article_cover;
    }

    private String article_cover;


    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    public String getArticle_introduction() {
        return article_introduction;
    }

    public void setArticle_introduction(String article_introduction) {
        this.article_introduction = article_introduction;
    }

    public String getWriter_id() {
        return writer_id;
    }

    public void setWriter_id(String writer_id) {
        this.writer_id = writer_id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
