package com._f_geo.music_lessons.services.impl;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._f_geo.music_lessons.models.Lesson;
import com._f_geo.music_lessons.repository.LessonRepository;
import com._f_geo.music_lessons.repository.UserRepository;
import com._f_geo.music_lessons.services.ILessonService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class LessonServiceImpl implements ILessonService{
    
    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Lesson createNew(Lesson lesson) {
        return lessonRepository.save(
            Lesson.builder()
                .date(lesson.getDate())
                .status(lesson.getStatus())
                .price(lesson.getPrice())
                .notes(lesson.getNotes())
                .room(lesson.getRoom())
                .card(lesson.getCard())
                .teacherId(lesson.getTeacherId())
                .studentId(lesson.getStudentId())
                .studentName(lesson.getStudentName())
                .teacherName(lesson.getTeacherName())
                .build()
        );
    }

    @Override
    public Lesson updateLesson(Lesson lesson) {
        Optional<Lesson> existingLesson = lessonRepository.findById(lesson.getId());
        if(existingLesson.isEmpty())
            throw new RuntimeException(String.format("No store found for id %s", lesson.getId()));
        existingLesson.get().setDate(lesson.getDate());
        existingLesson.get().setStatus(lesson.getStatus());
        existingLesson.get().setPrice(lesson.getPrice());
        existingLesson.get().setNotes(lesson.getNotes());
        existingLesson.get().setCard(lesson.getCard());
        existingLesson.get().setStudentId(lesson.getStudentId());
        existingLesson.get().setTeacherId(lesson.getTeacherId());
        existingLesson.get().setStudentName(lesson.getStudentName());
        existingLesson.get().setTeacherName(lesson.getTeacherName());
        return lessonRepository.save(existingLesson.get());
    }

    @Override
    public List<Lesson> getAllLessons() {
        List<Lesson> lessons = lessonRepository.findAll();
        return lessons;
    }

    @Override
    public Boolean deleteById(String id) {
        lessonRepository.deleteById(id);
        if (lessonRepository.findById(id).isEmpty()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public List<Lesson> getTeacherLessons(String userId) {
        List<Lesson> lessons = lessonRepository.findByTeacherId(userId);

        return lessons;
    }
    
    @Override
    public Boolean isDoubleBook(String teachId, String studentId, LocalDateTime dateTime) {
        List<Lesson> lessons = lessonRepository.findAll();
        for (Lesson lesson : lessons) {
            if(lesson.getStudentId().equals(studentId) && 
                lesson.getTeacherId().equals(teachId) &&
                lesson.getDate().equals(dateTime))
                throw new RuntimeException(String.format("Double Booked"));
        }
        return false;
    }

    
}
