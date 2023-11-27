package com._f_geo.music_lessons.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com._f_geo.music_lessons.models.Lesson;

@Service
public interface ILessonService {
    Lesson createNew(Lesson app);
    Lesson updateLesson(Lesson app);
    List<Lesson> getAllLessons();
    Boolean deleteById(String id);
    List<Lesson> getTeacherLessons(String userId);
    Boolean isDoubleBook(String teachId, String studentId, LocalDateTime DateTime);
}
