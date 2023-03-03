package com.example.demo.entity;

public class User {
    private String user_id;
    private String user_name;
    private String password;
    private String position;
    private String phone_number;
    private String photo_file;
    private String about;

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", position='" + position + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", photo_file='" + photo_file + '\'' +
                ", about='" + about + '\'' +
                '}';
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPhoto_file() {
        return photo_file;
    }

    public void setPhoto_file(String photo_file) {
        this.photo_file = photo_file;
    }



    public User(String user_id, String user_name, String password, String position, String phone_number, String photo_file, String about) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.password = password;
        this.position = position;
        this.phone_number = phone_number;
        this.photo_file = photo_file;
        this.about = about;
    }
}
