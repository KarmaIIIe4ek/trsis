package com.example.Lab3;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceImpl implements com.example.Lab3.Service {


    @Override
    public void create(String discipline,String lessonType,String audience,String address,String start) {

        DBHelper.db.add(new Lesson(DBHelper.db.get(DBHelper.db.size()-1).getId()+1,
                discipline,
                lessonType,
                audience,
                address,
                start
        ));

    }

    @Override
    public List<Lesson> getAll() {
        return DBHelper.db;
    }

    @Override
    public List<Lesson> getByDiscipline(String discipline) {
        List<Lesson> les = new ArrayList<>();
        for(int i=0;i<DBHelper.db.size();++i){
            if(DBHelper.db.get(i).getDiscipline().contentEquals(discipline)){
                les.add(DBHelper.db.get(i));
            }
        }
        return les;
    }

    @Override
    public boolean delete(int id) {
        for(int i=0;i<DBHelper.db.size();++i){
            if(DBHelper.db.get(i).getId() == id){
                DBHelper.db.remove(i);
                return true;
            }
        }
        return false;
    }
}
