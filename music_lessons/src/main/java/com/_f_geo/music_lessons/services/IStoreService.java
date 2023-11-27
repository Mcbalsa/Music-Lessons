package com._f_geo.music_lessons.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com._f_geo.music_lessons.models.Store;

@Service
public interface IStoreService {
    Store createNew(Store store);
    Store updateStore(Store store);
    List<Store> getAllStores();
    Boolean deleteById(String id);
}
