package com.example.matrix_java_mentor_2.controller;

import com.example.matrix_java_mentor_2.model.SpeakerRequestDto;
import com.example.matrix_java_mentor_2.model.SpeakerResponseDto;
import com.example.matrix_java_mentor_2.model.TalkResponseDto;
import com.example.matrix_java_mentor_2.repository.TalkRepository;
import com.example.matrix_java_mentor_2.service.impl.SpeakerService;
import com.example.matrix_java_mentor_2.service.impl.TalkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/speakers")
@RequiredArgsConstructor
public class SpeakerController {
    private final SpeakerService speakerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SpeakerResponseDto create(
            @Valid @RequestBody SpeakerRequestDto dto) {

        return speakerService.create(dto);
    }

    @GetMapping
    public List<SpeakerResponseDto> findAll() {
        return speakerService.findAll();
    }

    @GetMapping("/{id}")
    public SpeakerResponseDto findById(@PathVariable Long id) {
        return speakerService.findById(id);
    }
    @GetMapping("/{id}/talks")
    public List<TalkResponseDto> findTalks(@PathVariable Long id) {
        return speakerService.findTalks(id);
    }
}
