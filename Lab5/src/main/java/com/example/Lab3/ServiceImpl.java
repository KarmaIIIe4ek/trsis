package com.example.Lab3;

import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceImpl implements com.example.Lab3.Service {


    @Override
    public void create(int id, String discipline,String lessonType,String audience,String address,String start) {

        try {
            DBHelper.addLesson(
                    id,
                    discipline,
                    lessonType,
                    audience,
                    address,
                    start
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Lesson> getAll() {
        try {
            return DBHelper.getAllLesson();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            DBHelper.deleteLesson(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
