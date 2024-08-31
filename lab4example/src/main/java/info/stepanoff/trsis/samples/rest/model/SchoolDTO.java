/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.rest.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 *
 * @author Pavel Stepanov
 */
@Data
@Schema(description = "���������")
public class SchoolDTO {

    @Schema(description = "������������� ����������")
    private Integer id;
    @Schema(description = "����� ����������")
    private Integer number;
    @Schema(description = "�������� ����������")
    private String name;
}
