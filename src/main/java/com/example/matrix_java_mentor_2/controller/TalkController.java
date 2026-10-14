package com.example.matrix_java_mentor_2.controller;

import com.example.matrix_java_mentor_2.model.TalkRequestDto;
import com.example.matrix_java_mentor_2.model.TalkResponseDto;
import com.example.matrix_java_mentor_2.service.impl.TalkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/talks")
@RequiredArgsConstructor
public class TalkController {

    private final TalkService talkService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TalkResponseDto create(@Valid @RequestBody TalkRequestDto dto) {
        return talkService.create(dto);
    }

    @GetMapping
    public List<TalkResponseDto> findAll() {
        return talkService.findAll();
    }

    @GetMapping("/{id}")
    public TalkResponseDto findById(@PathVariable Long id) {
        return talkService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {talkService.delete(id);}
    @PutMapping("/{id}")
    public TalkResponseDto update(@PathVariable Long id, @Valid @RequestBody TalkRequestDto dto) {
        return talkService.update(id, dto);
    }
}
