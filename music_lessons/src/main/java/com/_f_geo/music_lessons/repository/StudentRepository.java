package com._f_geo.music_lessons.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com._f_geo.music_lessons.models.Student;

public interface StudentRepository extends MongoRepository<Student, String>{
    
}
