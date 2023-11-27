package com._f_geo.music_lessons.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("Application")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Application {
    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String phoneNum;
    private LocalDate dob;
    private String notes;
    private LocalDateTime dateSubmitted;
}
