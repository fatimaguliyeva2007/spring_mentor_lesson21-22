package com.example.matrix_java_mentor_2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpeakerResponseDto {
    private Long id;
    private String fullName;
    private String email;
    private String company;
}
//fullName, email, company