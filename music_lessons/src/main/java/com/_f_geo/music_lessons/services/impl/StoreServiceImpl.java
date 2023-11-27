package com._f_geo.music_lessons.services.impl;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._f_geo.music_lessons.models.Store;
import com._f_geo.music_lessons.repository.StoreRepository;
import com._f_geo.music_lessons.services.IStoreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoreServiceImpl implements IStoreService{
    
    @Autowired
    StoreRepository storeRepository;

    @Override
    public Store createNew(Store store) {
        return storeRepository.save(
            Store.builder()
                .address(store.getAddress())
                .phoneNum(store.getPhoneNum())
                .ownerId(store.getOwnerId())
                .rooms(store.getRooms())
                .build()
        );
    }

    @Override
    public Store updateStore(Store store) {
        Optional<Store> existingStore = storeRepository.findById(store.getId());
        if(existingStore.isEmpty())
            throw new RuntimeException(String.format("No store found for id %s", store.getId()));
        existingStore.get().setAddress(store.getAddress());
        existingStore.get().setPhoneNum(store.getPhoneNum());
        existingStore.get().setStoreNum(store.getStoreNum());
        existingStore.get().setOwnerId(store.getOwnerId());
        existingStore.get().setRooms(store.getRooms());
        return storeRepository.save(existingStore.get());
    }

    @Override
    public List<Store> getAllStores() {
        List<Store> stores = storeRepository.findAll();
        return stores;
    }

    @Override
    public Boolean deleteById(String id) {
        storeRepository.deleteById(id);
        if (storeRepository.findById(id).isEmpty()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    

    
}
