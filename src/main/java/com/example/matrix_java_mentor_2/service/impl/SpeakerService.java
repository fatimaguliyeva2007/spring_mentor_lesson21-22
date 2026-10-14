package com.example.matrix_java_mentor_2.service.impl;

import com.example.matrix_java_mentor_2.entity.SpeakerEntity;
import com.example.matrix_java_mentor_2.exception.DuplicateResourceException;
import com.example.matrix_java_mentor_2.exception.ResourceNotFoundException;
import com.example.matrix_java_mentor_2.mapper.SpeakerMapper;
import com.example.matrix_java_mentor_2.mapper.TalkMapper;
import com.example.matrix_java_mentor_2.model.SpeakerRequestDto;
import com.example.matrix_java_mentor_2.model.SpeakerResponseDto;
import com.example.matrix_java_mentor_2.model.TalkResponseDto;
import com.example.matrix_java_mentor_2.repository.SpeakerRepository;
import com.example.matrix_java_mentor_2.repository.TalkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SpeakerService {
    private final SpeakerRepository speakerRepository;
    private final SpeakerMapper speakerMapper;
    private final TalkMapper talkMapper;
    private final TalkRepository talkRepository;

    public SpeakerResponseDto create(SpeakerRequestDto dto) {
        if (speakerRepository.existsByEmail(dto.getEmail())) {
            log.warn("Speaker with duplicate email was requested");
            throw new DuplicateResourceException(
                    "Speaker with this email already exists"
            );
        }

        SpeakerEntity speaker = speakerMapper.toEntity(dto);
        SpeakerEntity savedSpeaker = speakerRepository.save(speaker);
        log.info("Speaker created with id {}", savedSpeaker.getId());

        return speakerMapper.toDto(savedSpeaker);
    }

    public List<SpeakerResponseDto> findAll() {
        log.debug("Fetching all speakers");
        return speakerMapper.toDtoList(speakerRepository.findAll());
    }

    public SpeakerResponseDto findById(Long id) {
        log.debug("Fetching speaker {}", id);
        SpeakerEntity speaker = speakerRepository.findById(id).orElseThrow(() ->
                        new ResourceNotFoundException("Speaker with id " + id + " not found"));
        return speakerMapper.toDto(speaker);
    }

    public List<TalkResponseDto> findTalks(Long speakerId) {
        log.debug("Fetching talks for speaker {}", speakerId);
        speakerRepository.findById(speakerId).orElseThrow(() ->
                        new ResourceNotFoundException("Speaker with id " + speakerId + " not found"));

        return talkMapper.toDtoList(talkRepository.findBySpeakerId(speakerId));
    }
}
