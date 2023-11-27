package com._f_geo.music_lessons.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com._f_geo.music_lessons.models.Post;

@Service
public interface IPostService {
    Post createNew(Post post);
    Post updatePost(Post post);
    List<Post> getAllPosts();
    Boolean deletePostById(String id);
}
