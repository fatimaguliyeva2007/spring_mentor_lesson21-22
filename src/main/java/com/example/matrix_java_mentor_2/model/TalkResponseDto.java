package com.example.matrix_java_mentor_2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TalkResponseDto {
    private Long id;
    private String title;
    private String room;
    private Integer durationMinutes;
    private Long speakerId;
    private String speakerName;
}
//id, title, room, durationMinutes, speakerId, speakerName
