package com._f_geo.music_lessons.models;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("User")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {
    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNum;
    private LocalDate dob;
    private String role;
    private List<String> genres;
    private List<String> instruments;
    private List<String> students;
    private String status;
    private String bio;
    private String photoUrl;
    private LocalDate dateleft;
}