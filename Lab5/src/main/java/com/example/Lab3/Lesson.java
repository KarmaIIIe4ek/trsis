package com.example.Lab3;

import java.io.Serializable;

public class Lesson {
    private int id;
    private String discipline;
    private String lessonType;
    private String audience;
    private String address;
    private String start;
    public Lesson(int id,String discipline,String lessonType, String audience, String address, String start){
        this.id = id;
        this.discipline = discipline;
        this.lessonType = lessonType;
        this.audience = audience;
        this.address = address;
        this.start = start;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getDiscipline(){
        return discipline;
    }
    public void setDiscipline(String discipline){
        this.discipline = discipline;
    }
    public String getLessonType(){
        return lessonType;
    }
    public void setLessonType(String lessonType){
        this.lessonType = lessonType;
    }
    public String getAudience(){
        return audience;
    }
    public void setAudience(String audience){
        this.audience = audience;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getStart(){
        return start;
    }
    public void setStart(String start){
        this.start = start;
    }

}
