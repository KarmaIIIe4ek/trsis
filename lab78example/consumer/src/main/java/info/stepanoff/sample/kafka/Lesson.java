/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.sample.kafka;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Pavel
 */
@Entity
@Table(name = "LESSON")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lesson implements Serializable {

    private static final long serialVersionUID = 1L;

    public Lesson(String discipline, String lessonType, String audience, String address, String start) {
        this.discipline = discipline;
        this.lessonType = lessonType;
        this.audience = audience;
        this.address = address;
        this.start = start;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LESSON_ID", nullable = false, updatable = false)
    private int id;

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
