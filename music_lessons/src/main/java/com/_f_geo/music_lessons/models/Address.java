package com._f_geo.music_lessons.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Address {
    String street;
    String city;
    String state;
    String areaCode;
}
