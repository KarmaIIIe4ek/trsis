/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */

package info.stepanoff.trsis.samples.service;

import info.stepanoff.trsis.samples.rest.model.LessonDTO;

import javax.xml.transform.sax.SAXResult;
import java.util.List;

public interface LessonService {

    List<LessonDTO> listAll();

    void delete(Integer id);
    
    LessonDTO add(String discipline, String lessonType, String audience, String address, String start);
    
    LessonDTO findByDiscipline(String discipline);

}
