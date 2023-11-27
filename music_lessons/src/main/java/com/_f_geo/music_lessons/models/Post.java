package com._f_geo.music_lessons.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("Posts")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Post {
    @Id
    private String id;

    private LocalDateTime creationDate;
    private String header;
    private String body;
    private String authorId;
    private String authorName;
}
