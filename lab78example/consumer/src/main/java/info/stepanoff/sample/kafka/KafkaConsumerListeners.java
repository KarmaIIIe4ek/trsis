/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info.stepanoff.sample.kafka;

import javax.transaction.Transactional;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 *
 * @author stepanov.pa
 */
@Service
public class KafkaConsumerListeners {

    @Autowired
    LessonRepository lessonRepository;

    @Transactional
    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.groupid}")
    public void debeziumListener1(ConsumerRecord<org.apache.avro.generic.GenericData.Record, org.apache.avro.generic.GenericData.Record> record) {

        if (record.value() == null) {
            return;
        }

        String op = ((org.apache.avro.util.Utf8) record.value().get("op")).toString();

        if (op.equals("d")) {
            Integer idToDelete = (Integer) record.key().get("lesson_id");
            try {
                lessonRepository.deleteById(idToDelete);
            } catch (org.springframework.dao.EmptyResultDataAccessException e) {
                //Команда удаления приходит из удаленного лога репликации
                //в том числе и в ответ на ответ на удаление в локальном. 
                //В этом случае удалить повторно запись уже нельзя
            }
            return;
        }

        org.apache.avro.generic.GenericData.Record value = (org.apache.avro.generic.GenericData.Record) record.value().get("after");
        Integer id = (Integer) value.get("lesson_id");
        String discipline = ((org.apache.avro.util.Utf8) value.get("lesson_discipline")).toString();
        String lessonType = ((org.apache.avro.util.Utf8) value.get("lesson_lessontype")).toString();
        String audience = ((org.apache.avro.util.Utf8) value.get("audience")).toString();
        String address = ((org.apache.avro.util.Utf8) value.get("lesson_address")).toString();
        String start = ((org.apache.avro.util.Utf8) value.get("lesson_start")).toString();

        Lesson lesson = lessonRepository.findById(id).orElse(null);

        if (lesson == null) {
            lessonRepository.save(new Lesson(id, discipline, lessonType, audience, address, start));
        } else {
            lesson.setId(id);
            lesson.setDiscipline(discipline);
            lesson.setLessonType(lessonType);
            lesson.setAudience(audience);
            lesson.setAddress(address);
            lesson.setStart(start);
            lessonRepository.save(lesson);
        }
    }

}
