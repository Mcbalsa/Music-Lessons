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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com._f_geo.music_lessons.models.Application;
import com._f_geo.music_lessons.services.IApplicationService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/Application")
@RequiredArgsConstructor
public class ApplicationController {

    @Autowired
    IApplicationService appService;

    @PostMapping("New")
    @ResponseStatus(HttpStatus.CREATED)
    public Application createNew(@RequestBody Application app) {
        return appService.createNew(app);
    }

    @PutMapping("Update")
    @ResponseStatus(HttpStatus.OK)
    public Application updateApp(Application app) {
        return appService.updateApplication(app);
    }

    @GetMapping("GetAll")
    @ResponseStatus(HttpStatus.OK)
    public List<Application> getAllApps() {
        return appService.getAllApplications();
    }

    @DeleteMapping("Delete")
    @ResponseStatus(HttpStatus.OK)
    public Boolean deleteById(String id) {
        return appService.deleteById(id);
    }
    
}
