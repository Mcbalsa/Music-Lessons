package com._f_geo.music_lessons.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com._f_geo.music_lessons.models.Application;

@Service
public interface IApplicationService {
    Application createNew(Application app);
    Application updateApplication(Application app);
    List<Application> getAllApplications();
    Boolean deleteById(String id);
}
