package com.example.matrix_java_mentor_2.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TalkRequestDto {

    @NotBlank(message = "Title is required")
    @Size(min = 5, max = 150, message = "Title must be 5-150 characters")
    private String title;

    @NotBlank(message = "Room is required")
    private String room;

    @NotNull(message = "Duration is required")
    @Min(value = 15, message = "Duration must be at least 15 minutes")
    @Max(value = 90, message = "Duration must be at most 90 minutes")
    private Integer durationMinutes;

    @NotNull(message = "Speaker id is required")
    @Positive(message = "Speaker id must be positive")
    private Long speakerId;

    @NotNull(message = "Scheduled time is required")
    @Future(message = "Talk must be scheduled in the future")
    private LocalDateTime scheduledAt;
}
//title, room, durationMinutes, speakerId

