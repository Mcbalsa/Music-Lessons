package com._f_geo.music_lessons.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._f_geo.music_lessons.models.Post;
import com._f_geo.music_lessons.repository.PostRepository;
import com._f_geo.music_lessons.services.IPostService;

@Service
public class PostServiceImpl implements IPostService {

    @Autowired
    PostRepository postRepository;

    @Override
    public Post createNew(Post post) {
        return postRepository.save(
                Post.builder()
                        .creationDate(LocalDateTime.now())
                        .header(post.getHeader())
                        .body(post.getBody())
                        .authorId(post.getAuthorId())
                        .authorName(post.getAuthorName())
                        .build());
    }

    @Override
    public Post updatePost(Post post) {
        Optional<Post> existingPost = postRepository.findById(post.getId());
        if(existingPost.isEmpty())
            throw new RuntimeException(String.format("No post found by id %", post.getId()));
        existingPost.get().setHeader(post.getHeader());
        existingPost.get().setBody(post.getBody());
        existingPost.get().setCreationDate(post.getCreationDate());
        existingPost.get().setAuthorId(post.getAuthorId());
        existingPost.get().setAuthorName(post.getAuthorName());
        return postRepository.save(existingPost.get());
    }

    @Override
    public List<Post> getAllPosts() {
        List<Post> posts = postRepository.findAll();

        return posts;
    }

    @Override
    public Boolean deletePostById(String id) {
        postRepository.deleteById(id);
        if (postRepository.findById(id).isEmpty()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

}
