package com._f_geo.music_lessons.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document("Lesson")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Lesson {
    @Id
    private String id;

    private LocalDateTime date;
    private String status;
    private int price;
    private String notes;
    private String room;
    private CreditCard card;
    private String teacherId;
    private String studentId;
    private String studentName;
    private String teacherName;
}
