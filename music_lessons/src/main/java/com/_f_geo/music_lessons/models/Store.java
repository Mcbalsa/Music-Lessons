package com._f_geo.music_lessons.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("Store")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Store {
    @Id
    private String id;
    
    private Address address;
    private String phoneNum;
    private String storeNum;
    private String ownerId;
    private List<String> rooms;
    
}
