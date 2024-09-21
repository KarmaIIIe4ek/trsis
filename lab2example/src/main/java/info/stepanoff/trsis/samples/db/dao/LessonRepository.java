/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.db.dao;

import info.stepanoff.trsis.samples.db.model.LessonPE;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface LessonRepository extends CrudRepository<LessonPE, Integer> {

    public Optional<LessonPE> findByDiscipline(String discipline);

    public List<LessonPE> findAll();
}
