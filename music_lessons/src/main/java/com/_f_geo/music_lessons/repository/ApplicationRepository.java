package com._f_geo.music_lessons.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com._f_geo.music_lessons.models.Application;

public interface ApplicationRepository extends MongoRepository<Application, String> {
    
}
