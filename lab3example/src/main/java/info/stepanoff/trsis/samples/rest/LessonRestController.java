/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.rest;

import info.stepanoff.trsis.samples.rest.model.LessonDTO;
import info.stepanoff.trsis.samples.rest.model.LessonDataWithoutID;
import info.stepanoff.trsis.samples.service.LessonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/public/rest/lessons", produces = "application/json")
@RequiredArgsConstructor
public class LessonRestController {

    private final LessonService LessonService;

    @RequestMapping(value = "", method = RequestMethod.GET)

    @Operation(summary = "Получить перечень занятий",
            description = "Получить перечень занятий - расширенное описание",
            responses = {
                @ApiResponse(responseCode = "200",
                        description = "Успешное выполнение"),
                @ApiResponse(responseCode = "401",
                        description = "Требуется аутентификация"),
                @ApiResponse(responseCode = "403",
                        description = "Аутентификация предоставлена, но у пользователя нет доступа"),
                @ApiResponse(responseCode = "404",
                        description = "Ресурс не найден")
            })
    public ResponseEntity<List<LessonDTO>> browse() {
        return ResponseEntity.ok(LessonService.listAll());
    }

    @Operation(summary = "Удаление записи о занятии",
            description = "Удаление записи о занятии - расширенное описание",
            responses = {
                @ApiResponse(responseCode = "200",
                        description = "Успешное выполнение"),
                @ApiResponse(responseCode = "401",
                        description = "Требуется аутентификация"),
                @ApiResponse(responseCode = "403",
                        description = "Аутентификация предоставлена, но у пользователя нет доступа"),
                @ApiResponse(responseCode = "404",
                        description = "Ресурс не найден")
            })
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id")
            @Parameter(description = "Идентификатор занятия") Integer id) {
        LessonService.delete(id);
    }

    @Operation(summary = "Создать новую запись о занятии",
            description = "Создать новую запись о занятии - расширенное описание",
            responses = {
                @ApiResponse(responseCode = "200",
                        description = "Успешное выполнение"),
                @ApiResponse(responseCode = "401",
                        description = "Требуется аутентификация"),
                @ApiResponse(responseCode = "403",
                        description = "Аутентификация предоставлена, но у пользователя нет доступа"),
                @ApiResponse(responseCode = "404",
                        description = "Ресурс не найден")
            })
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<LessonDTO> handleLessonRequest(
            @Parameter(description = "JSON с данными о занятии", required = true)
            @RequestBody LessonDataWithoutID requestBody) {

        String discipline = requestBody.getDiscipline();
        String lessonType = requestBody.getLessonType();
        String audience = requestBody.getAudience();
        String address = requestBody.getAddress();
        String start = requestBody.getStart();

        return ResponseEntity.ok(LessonService.add(discipline, lessonType, audience, address, start));
    }

    @Operation(summary = "Поиск занятия по дисциплине",
            description = "Поиск занятия по дисциплине - расширенное описание",
            responses = {
                @ApiResponse(responseCode = "200",
                        description = "Успешное выполнение"),
                @ApiResponse(responseCode = "401",
                        description = "Требуется аутентификация"),
                @ApiResponse(responseCode = "403",
                        description = "Аутентификация предоставлена, но у пользователя нет доступа"),
                @ApiResponse(responseCode = "404",
                        description = "Ресурс не найден")
            })
    @RequestMapping(value = "/discipline/{discipline}", method = RequestMethod.GET)
    public ResponseEntity<LessonDTO> findByNumber(@PathVariable("discipline")
            @Parameter(description = "Название дисциплины") String discipline) {
        return ResponseEntity.ok(LessonService.findByDiscipline(discipline));
    }
}
