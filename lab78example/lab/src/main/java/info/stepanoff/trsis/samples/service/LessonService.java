/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */

package info.stepanoff.trsis.samples.service;

import info.stepanoff.trsis.samples.db.model.Lesson;

public interface LessonService {

    Iterable<Lesson> listAll();

    void delete(Integer id);
    
    Lesson add(String discipline, String lessonType, String audience, String address, String start);

    Lesson findByDiscipline(String discipline);
}
