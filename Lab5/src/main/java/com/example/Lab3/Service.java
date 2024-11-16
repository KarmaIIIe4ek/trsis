package com.example.Lab3;

import com.example.Lab3.Lesson;

import java.util.List;

public interface Service{

    void create(String discipline,String lessonType,String audience,String address,String start);

    List<Lesson> getAll();

    List<Lesson> getByDiscipline(String discipline);

    boolean delete(int id);
}