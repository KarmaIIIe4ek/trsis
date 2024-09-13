package info.stepanoff.trsis.lab1.data;

public class Lesson {

    private String startTime; // Время начала занятия (например, "10:00")
    private String subjectName; // Название дисциплины
    private String lessonType; // Тип занятия (лекция, практическая работа, лабораторная)
    private String classroom; // Аудитория (например, "1234")
    private String buildingAddress; // Адрес здания


    // Конструктор
    public Lesson(String startTime, String subjectName, String lessonType, String classroom, String buildingAddress) {
        this.startTime = startTime;
        this.subjectName = subjectName;
        this.lessonType = lessonType;
        this.classroom = classroom;
        this.buildingAddress = buildingAddress;
    }

    // Геттеры
    public String getStartTime() {
        return startTime;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getLessonType() {
        return lessonType;
    }

    public String getClassroom() {
        return classroom;
    }

    public String getBuildingAddress() {
        return buildingAddress;
    }
}
