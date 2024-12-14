package com.example.Lab3;

import com.example.Lab3.Lesson;

import java.util.List;

public interface Service{

    void create(int id, String discipline,String lessonType,String audience,String address,String start);

    List<Lesson> getAll();

    boolean delete(int id);
}