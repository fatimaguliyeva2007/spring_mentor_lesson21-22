package com.example.matrix_java_mentor_2.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpeakerRequestDto {
    @NotBlank
    private String fullName;
    @NotBlank
    @Email
    private String email;
    private String company;
}
//fullName, email, company