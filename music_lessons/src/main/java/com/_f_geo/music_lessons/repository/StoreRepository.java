package com._f_geo.music_lessons.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com._f_geo.music_lessons.models.Store;

public interface StoreRepository extends MongoRepository<Store, String>{
    
}
