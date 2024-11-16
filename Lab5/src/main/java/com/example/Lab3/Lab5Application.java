package com.example.Lab3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lab5Application {

    public static void main(String[] args) {
        DBHelper.db.add(new Lesson(0, "ТРСИС", "ЛР", "Дистант", "-", "15:00"));
        DBHelper.db.add(new Lesson(1, "Основы программирования", "КР", "23-09", "Большая Морская 67", "16:40"));
        DBHelper.db.add(new Lesson(2, "Базы данных", "ЛР", "23-10", "Большая Морская 67", "18:30"));
        SpringApplication.run(Lab5Application.class, args);
    }
}
