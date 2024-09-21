/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import info.stepanoff.trsis.samples.db.dao.LessonRepository;
import info.stepanoff.trsis.samples.db.model.LessonPE;
import info.stepanoff.trsis.samples.rest.model.LessonDTO;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository LessonRepository;

    private final ObjectMapper objectMapper;

    @Override
    public List<LessonDTO> listAll() {
        return LessonRepository.findAll().stream()
                .map(LessonPE -> objectMapper.convertValue(LessonPE, LessonDTO.class))
                .collect(Collectors.toList());

    }

    @Override
    public void delete(Integer id) {
        LessonRepository.deleteById(id);
    }

    @Override
    public LessonDTO add(String discipline, String lessonType, String audience, String address, String start) {
        return objectMapper.convertValue(LessonRepository.save(new LessonPE(discipline, lessonType, audience, address, start)), LessonDTO.class);
    }

    @Override
    public LessonDTO findByDiscipline(String discipline) {
        var LessonPE = LessonRepository.findByDiscipline(discipline);
        return LessonPE.map(Lesson -> objectMapper.convertValue(Lesson, LessonDTO.class)).orElse(null);
    }
}
