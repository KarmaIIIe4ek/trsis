/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.rest.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Занятие")
public class LessonDTO {

    @Schema(description = "Идентификатор занятия")
    private Integer id;
    @Schema(description = "Название дисциплины")
    private String discipline;
    @Schema(description = "Тип занятия")
    private String lessonType;
    @Schema(description = "Номер аудитории")
    private String audience;
    @Schema(description = "Адрес")
    private String address;
    @Schema(description = "Время начала занятия")
    private String start;
}
