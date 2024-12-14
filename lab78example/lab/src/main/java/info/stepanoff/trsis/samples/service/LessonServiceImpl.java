/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */

package info.stepanoff.trsis.samples.service;



import info.stepanoff.trsis.samples.db.model.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.stepanoff.trsis.samples.db.dao.LessonRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LessonServiceImpl implements LessonService {
    

    @Autowired
    private LessonRepository lessonRepository;

    @Override
    public Iterable<Lesson> listAll() {
        return lessonRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        lessonRepository.deleteById(id);
    }

    @Override
    public Lesson add(String discipline, String lessonType, String audience, String address, String start) {
        return lessonRepository.save(new Lesson(discipline, lessonType, audience, address, start));
    }
    @Override
    public Lesson findByDiscipline(String discipline) {
        return lessonRepository.findByDiscipline(discipline);
    }
}
