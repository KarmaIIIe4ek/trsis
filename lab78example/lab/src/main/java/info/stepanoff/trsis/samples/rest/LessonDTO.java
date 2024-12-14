package info.stepanoff.trsis.samples.rest;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class LessonDTO  implements Serializable {
    private String discipline;
    private String lessonType;
    private String audience;
    private String address;
    private String start;

}