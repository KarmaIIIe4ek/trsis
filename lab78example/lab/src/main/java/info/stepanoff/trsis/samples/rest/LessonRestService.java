/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.rest;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import info.stepanoff.trsis.samples.service.LessonService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.security.Principal;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/public/rest/lessons")
@Api(value = "LessonsAPI", description = "API for Lessons")
public class LessonRestService {

    @Autowired
    private LessonService lessonService;

    @RequestMapping(value = "", method = RequestMethod.GET)

    @ApiOperation(value = "View a list of all available lessons", response = Iterable.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<Object> browse() {
        return ResponseEntity.ok(lessonService.listAll());
    }

    @ApiOperation(value = "Remove a lesson by ID", response = Iterable.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully removed"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id, Principal principal) {

        if (principal == null) {
            throw new ForbiddenException();
        }

        lessonService.delete(id);
    }

    @RequestMapping(value = "/mockdelete/{id}", method = RequestMethod.GET)
    public void mockdelete(@PathVariable("id") Integer id, Principal principal) {
        if (principal == null) {
            throw new ForbiddenException();
        }

        lessonService.delete(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Object> add(@RequestBody LessonDTO requestBody, Principal principal) {
        if (principal == null) {
            throw new ForbiddenException();
        }
        String discipline = requestBody.getDiscipline();
        String lessonType = requestBody.getLessonType();
        String audience = requestBody.getAudience();
        String address = requestBody.getAddress();
        String start = requestBody.getStart();

        return ResponseEntity.ok(lessonService.add(discipline, lessonType, audience, address, start));
    }

    @ApiOperation(value = "Find a lesson by discipline", response = Iterable.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully found"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "/{discipline}", method = RequestMethod.GET)
    public ResponseEntity<Object> findByDiscipline(@PathVariable("discipline") String discipline) {
        return ResponseEntity.ok(lessonService.findByDiscipline(discipline));
    }

}
