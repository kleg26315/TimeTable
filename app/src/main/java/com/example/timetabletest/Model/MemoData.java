package com.example.timetabletest.Model;

public class MemoData {
    private String title;
    private String user_key;
    private String lecture_code;
    private String type;
    private String description;
    private String date;


    public String getUser_key() {
        return user_key;
    }

    public String getLecture_code() {
        return lecture_code;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public MemoData(String title, String user_key, String lecture_code, String type, String description, String date){
        this.title = title;
        this.user_key = user_key;
        this.lecture_code = lecture_code;
        this.type = type;
        this.description = description;
        this.date = date;

    }
}
