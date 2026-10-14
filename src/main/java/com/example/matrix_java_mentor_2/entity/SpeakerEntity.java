package com.example.matrix_java_mentor_2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "speakers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SpeakerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    @Column(unique = true)
    private String email;
    private String company;

    @OneToMany(mappedBy = "speaker")
    private List<TalkEntity> talkEntities = new ArrayList<>();
}

//id, fullName, email, company
