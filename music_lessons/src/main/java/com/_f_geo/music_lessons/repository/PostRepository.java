package com._f_geo.music_lessons.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com._f_geo.music_lessons.models.Post;

public interface PostRepository extends MongoRepository<Post, String> {
    
}
