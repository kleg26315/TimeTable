package com.example.timetabletest.Model;

public class LectureData {

    private String lecture;
    private String strTime;
    private String endTime;
    private String dayofweek;
    private String code;
    private String professor;
    private String location;

    public String getLecture() {
        return lecture;
    }

    public String getStrTime() {
        return strTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getDayofweek() {
        return dayofweek;
    }

    public String getCode() {
        return code;
    }

    public String getProfessor() {
        return professor;
    }

    public String getLocation() {
        return location;
    }

    public LectureData(String lecture, String strTime, String endTime, String dayofweek, String code, String professor, String location){
        this.lecture = lecture;
        this.strTime = strTime;
        this.endTime = endTime;
        this.dayofweek = dayofweek;
        this.code = code;
        this.professor = professor;
        this.location = location;
    }

}