package com._f_geo.music_lessons.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._f_geo.music_lessons.models.Application;
import com._f_geo.music_lessons.repository.ApplicationRepository;
import com._f_geo.music_lessons.services.IApplicationService;

@Service
public class ApplicationServiceImpl implements IApplicationService {

    @Autowired
    ApplicationRepository appRepository;
    @Override
    public Application createNew(Application app) {
        return appRepository.save(
            Application.builder()
                .firstName(app.getFirstName())
                .lastName(app.getLastName())
                .dob(app.getDob())
                .phoneNum(app.getPhoneNum())
                .notes(app.getNotes())
                .dateSubmitted(LocalDateTime.now())
                .build()
        );
    }

    @Override
    public Application updateApplication(Application app) {
        Optional<Application> existingApp = appRepository.findById(app.getId());
        if(existingApp.isEmpty())
            throw new RuntimeException(String.format("No application found by id %", app.getId()));
        existingApp.get().setFirstName(app.getFirstName());
        existingApp.get().setLastName(app.getLastName());
        existingApp.get().setDob(app.getDob());
        existingApp.get().setPhoneNum(app.getPhoneNum());
        existingApp.get().setNotes(app.getNotes());
        return appRepository.save(existingApp.get());
    }

    @Override
    public List<Application> getAllApplications() {
        List<Application> apps = appRepository.findAll();
        return apps;
    }

    @Override
    public Boolean deleteById(String id) {
        appRepository.deleteById(id);
        if(appRepository.findById(id).isEmpty()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;

    }
    
}
