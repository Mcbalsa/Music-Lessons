package com._f_geo.music_lessons.models;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//student
@Document("Student")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Student {
    @Id
    private String id;


    private String firstName;
    private String lastName;
    private String guardName;
    private LocalDate DOB;
    private List<String> instruments;
    private List<String> teachers;
    private String phoneNum;
    private String status;
    private LocalDate dateLeft;
    private Address address;
    private CreditCard creditCard;
}
