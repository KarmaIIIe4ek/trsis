
package com.example.Lab3.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "LESSON")
@Data
public class LessonPE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LESSON_ID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "LESSON_DISCIPLINE", nullable = false)
    private String discipline;

    @Column(name="LESSON_LESSONTYPE", nullable = false)
    private String lessonType;

    @Column(name="AUDIENCE", nullable = true)
    private String audience;

    @Column(name="LESSON_ADDRESS", nullable = true)
    private String address;

    @Column(name="LESSON_START", nullable = false)
    private String start;

}