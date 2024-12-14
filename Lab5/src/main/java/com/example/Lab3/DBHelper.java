package com.example.Lab3;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import java.sql.Connection;
import java.util.ArrayList;

@Slf4j
public class DBHelper {
    static public List<Lesson> db = new ArrayList<Lesson>();

    // docker run --name=postgres -e POSTGRES_PASSWORD='postgres' -p 5433:5432 -d postgres
    static final String DATABASE_URL = "jdbc:postgresql://localhost:5433/postgres?user=postgres&password=postgres";
    static final String DATABASE_CREATE_URL = DATABASE_URL + "";
    static Connection conn;

    static {
        try {
            conn = DriverManager.getConnection(DATABASE_URL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static final int NUMBER_OF_CONNECTIONS = 30;
    static volatile int currentConnection = 0;

    static final String LESSON = "LESSON";
    static final String LESSON_ID = "LESSON_ID";
    static final String LESSON_DISCIPLINE = "LESSON_DISCIPLINE";
    static final String LESSON_LESSONTYPE = "LESSON_LESSONTYPE";
    static final String AUDIENCE = "AUDIENCE";
    static final String LESSON_ADDRESS = "LESSON_ADDRESS";
    static final String LESSON_START = "LESSON_START";

    static final String SELECT_LESSON_SQL = "SELECT " + LESSON_ID + ",  " + LESSON_DISCIPLINE + ",  " + LESSON_LESSONTYPE + ",  " + AUDIENCE + ",  " + LESSON_ADDRESS + ",  " + LESSON_START +" FROM " + LESSON;
    static final String DELETE_LESSON_SQL = "DELETE FROM " + LESSON + " WHERE " + LESSON_ID + " = ?";
    static final String ADD_LESSON_SQL = "INSERT INTO " + LESSON + " (" + LESSON_ID + ", " + LESSON_DISCIPLINE + ", " + LESSON_LESSONTYPE + ", " + AUDIENCE + ", " + LESSON_ADDRESS + ", " + LESSON_START + ") VALUES (?, ?,?,?,?,?)";

    public DBHelper() throws SQLException {
    }

    public static List<Lesson> getAllLesson() throws SQLException {

        List<Lesson> result = new LinkedList<>();
        while (true) {

            Statement st = conn.createStatement();

            if (st.isClosed()) {
                continue;
            }
            try (ResultSet rs = st.executeQuery(SELECT_LESSON_SQL)) {
                while (rs.next()) {
                    int id = rs.getInt(LESSON_ID);
                    String discipline = rs.getString(LESSON_DISCIPLINE);
                    String lessonType = rs.getString(LESSON_LESSONTYPE);
                    String audience = rs.getString(AUDIENCE);
                    String address = rs.getString(LESSON_ADDRESS);
                    String start = rs.getString(LESSON_START);
                    Lesson lesson = new Lesson(id, discipline, lessonType, audience, address, start);

                    result.add(lesson);
                }
            }
            return result;
        }
    }

    public static void deleteLesson(Integer id) throws SQLException {

        while (true) {

            PreparedStatement  st = conn.prepareStatement(DELETE_LESSON_SQL);
            st.setInt(1, id);
            if (st.isClosed()) {
                continue;
            }
            int rowsDeleted = st.executeUpdate();
            log.debug("lesson with id= " + id + " has been deleted");
            return;
        }


    }

    public static Lesson addLesson(int id_lesson, String discipline, String lessonType, String audience, String address, String start) throws SQLException {

        while (true) {

            PreparedStatement  st = conn.prepareStatement(ADD_LESSON_SQL);
            st.setInt(1, id_lesson);
            st.setString(2, discipline);
            st.setString(3, lessonType);
            st.setString(4, audience);
            st.setString(5, address);
            st.setString(6, start);
            if (st.isClosed()) {
                continue;
            }
            int created = st.executeUpdate();
            log.debug("lesson with id= " + id_lesson + " has been created");
            return new Lesson(id_lesson, discipline, lessonType, audience, address, start);
        }
    }

}
