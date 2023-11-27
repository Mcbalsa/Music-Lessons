package com._f_geo.music_lessons.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com._f_geo.music_lessons.models.Lesson;


public interface LessonRepository extends MongoRepository<Lesson, String> {
    List<Lesson> findByTeacherId(String teacherId);
}
