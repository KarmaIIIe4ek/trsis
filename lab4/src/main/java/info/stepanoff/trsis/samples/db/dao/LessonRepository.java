/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.db.dao;

import org.springframework.data.repository.CrudRepository;
import

import java.util.List;
import java.util.Optional;

public interface LessonRepository extends CrudRepository<Lesson, Integer> {

    public Optional<Lesson> findByDiscipline(String discipline);

    public List<Lesson> findAll();
}
