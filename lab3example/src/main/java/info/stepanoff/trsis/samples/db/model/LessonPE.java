/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.db.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Entity
@Table(name = "LESSON")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonPE implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Конструктор объекта Занятие
     * @param discipline
     * @param lessonType
     * @param audience
     * @param address
     * @param start
     */
    public LessonPE(String discipline, String lessonType, String audience, String address, String start) {
        this.discipline = discipline;
        this.lessonType = lessonType;
        this.audience = audience;
        this.address = address;
        this.start = start;
    }

    @Id
    @Column(name = "LESSON_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "DISCIPLINE_NAME")
    private String discipline;
    @Column(name = "LESSON_TYPE")
    private String lessonType;
    @Column(name = "AUDIENCE_NUMBER")
    private String audience;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "START_TIME")
    private String start;

}
