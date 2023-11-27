package com._f_geo.music_lessons.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com._f_geo.music_lessons.models.Post;
import com._f_geo.music_lessons.services.IPostService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/Post")
@RequiredArgsConstructor
public class PostController {

    @Autowired
    IPostService postService;

    @PostMapping("New")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createNew(@RequestBody Post post) {
        return postService.createNew(post);
    }

    @PutMapping("Update")
    @ResponseStatus(HttpStatus.OK)
    public Post updatePost(@RequestBody Post post) {
        return postService.updatePost(post);
    }

    @DeleteMapping("DeleteById")
    @ResponseStatus(HttpStatus.OK)
    public Boolean deleteById(@RequestParam String id) {
        return postService.deletePostById(id);
    }

    @GetMapping("GetAll")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }
}
