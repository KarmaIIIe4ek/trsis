package com.example.Lab3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Pavel.Stepanov
 */
@Data
public class ConnectionHolder {
    @Setter
    @Getter
    Connection connection;
    PreparedStatement lessonStatement;
    PreparedStatement deleteLessonStatement;
    PreparedStatement addLessonStatement;

}

