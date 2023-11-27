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

import com._f_geo.music_lessons.models.Store;
import com._f_geo.music_lessons.services.IStoreService;

import lombok.RequiredArgsConstructor;

    @RestController
    @CrossOrigin
    @RequestMapping("/api/v1/Store")
    @RequiredArgsConstructor
public class StoreController {
    @Autowired
    IStoreService storeService;

    @PostMapping("New")
    @ResponseStatus(HttpStatus.CREATED)
    public Store createNew(@RequestBody Store store) {
        return storeService.createNew(store);
    }

    @GetMapping("GetAll")
    @ResponseStatus(HttpStatus.OK)
    public List<Store> getAllStores() {
        return storeService.getAllStores();
    }
    
    @DeleteMapping("Delete")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteById(@RequestParam String id) {
        return storeService.deleteById(id);
    }

    @PutMapping("Update")
    @ResponseStatus(HttpStatus.OK)
    public Store updateStore(@RequestBody Store store) {
        return storeService.updateStore(store);
    }
}
