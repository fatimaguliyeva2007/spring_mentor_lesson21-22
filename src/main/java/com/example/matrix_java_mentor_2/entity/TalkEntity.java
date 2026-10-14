package com.example.matrix_java_mentor_2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;


@Entity
@Table(name = "talks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TalkEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String room;
    private Integer durationMinutes;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "speaker_id", nullable = false)
    private SpeakerEntity speaker;
}

