
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

import com._f_geo.music_lessons.models.Lesson;
import com._f_geo.music_lessons.services.ILessonService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/Lesson")
@RequiredArgsConstructor
public class LessonController {
    @Autowired
    ILessonService lessonService;

    @PostMapping("New")
    @ResponseStatus(HttpStatus.CREATED)
    public Lesson createNew(@RequestBody Lesson lesson) {
        lessonService.isDoubleBook(lesson.getTeacherId(), lesson.getStudentId(), lesson.getDate());
        return lessonService.createNew(lesson);
    }

    @GetMapping("GetAll")
    @ResponseStatus(HttpStatus.OK)
    public List<Lesson> getAllStores() {
        return lessonService.getAllLessons();
    }
    
    @DeleteMapping("Delete")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteById(@RequestParam String id) {
        return lessonService.deleteById(id);
    }

    @PutMapping("Update")
    @ResponseStatus(HttpStatus.OK)
    public Lesson updateLesson(@RequestBody Lesson lesson) {
        return lessonService.updateLesson(lesson);
    }

    @GetMapping("GetAssignedTeacher")
    @ResponseStatus(HttpStatus.OK)
    public List<Lesson> getTeacherLessons(String userId) {
        return lessonService.getTeacherLessons(userId);
    }
}
