package com.example.matrix_java_mentor_2.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TalkRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String room;
    @NotNull
    private Integer durationMinutes;
    @NotNull
    private Long speakerId;
}
//title, room, durationMinutes, speakerId

