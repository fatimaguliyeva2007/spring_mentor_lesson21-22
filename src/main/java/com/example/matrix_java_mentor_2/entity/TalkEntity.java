package com.example.matrix_java_mentor_2.entity;

import com.example.matrix_java_mentor_2.enums.TalkStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;


@Entity
@Table(name = "talks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TalkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String room;
    private Integer durationMinutes;
    private LocalDateTime scheduledAt;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TalkStatus status = TalkStatus.SCHEDULED;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "speaker_id", nullable = false)
    private SpeakerEntity speaker;
}
