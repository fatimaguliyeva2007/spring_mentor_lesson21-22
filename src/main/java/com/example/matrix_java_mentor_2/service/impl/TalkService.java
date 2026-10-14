package com.example.matrix_java_mentor_2.service.impl;

import com.example.matrix_java_mentor_2.entity.SpeakerEntity;
import com.example.matrix_java_mentor_2.entity.TalkEntity;
import com.example.matrix_java_mentor_2.enums.TalkStatus;
import com.example.matrix_java_mentor_2.exception.BusinessRuleException;
import com.example.matrix_java_mentor_2.exception.ResourceNotFoundException;
import com.example.matrix_java_mentor_2.mapper.TalkMapper;
import com.example.matrix_java_mentor_2.model.TalkRequestDto;
import com.example.matrix_java_mentor_2.model.TalkResponseDto;
import com.example.matrix_java_mentor_2.repository.SpeakerRepository;
import com.example.matrix_java_mentor_2.repository.TalkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class TalkService {

    private final TalkRepository talkRepository;
    private final SpeakerRepository speakerRepository;
    private final TalkMapper talkMapper;

    private static final int MIN_DURATION = 15;
    private static final int MAX_DURATION = 90;
    private static final int MAX_TALKS_PER_SPEAKER = 3;

    @Transactional
    public TalkResponseDto create(TalkRequestDto requestDto) {
        SpeakerEntity speaker = speakerRepository
                .findById(requestDto.getSpeakerId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Speaker not found with id: "
                                + requestDto.getSpeakerId()));

        validateDuration(requestDto.getDurationMinutes());
        long talkCount = talkRepository.countBySpeakerId(speaker.getId());

        if (talkCount >= MAX_TALKS_PER_SPEAKER) {
            throw new BusinessRuleException("A speaker cannot have more than 3 talks");
        }

        TalkEntity talk = talkMapper.toEntity(requestDto);
        talk.setSpeaker(speaker);
        talk.setStatus(TalkStatus.SCHEDULED);
        TalkEntity savedTalk = talkRepository.save(talk);
        log.info("Talk created successfully with id: {}", savedTalk.getId());
        return talkMapper.toDto(savedTalk);
    }

    @Transactional(readOnly = true)
    public List<TalkResponseDto> findAll() {
        log.debug("Fetching all talks");
        return talkMapper.toDtoList(talkRepository.findAll());
    }

    @Transactional(readOnly = true)
    public TalkResponseDto findById(Long id) {
        log.debug("Fetching talk with id: {}", id);

        TalkEntity talk = talkRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Talk not found with id: " + id));
        return talkMapper.toDto(talk);
    }

    @Transactional(readOnly = true)
    public List<TalkResponseDto> findBySpeakerId(Long speakerId) {
        if (!speakerRepository.existsById(speakerId)) {
            throw new ResourceNotFoundException(
                    "Speaker not found with id: " + speakerId
            );
        }

        log.debug("Fetching talks for speaker with id: {}", speakerId);
        return talkMapper.toDtoList(talkRepository.findBySpeakerId(speakerId));
    }

    @Transactional
    public void delete(Long id) {

        TalkEntity talk = talkRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Talk not found with id: " + id));

        talkRepository.delete(talk);
        log.info("Talk deleted successfully with id: {}", id);
    }

    @Transactional
    public TalkResponseDto update(Long id, TalkRequestDto requestDto) {
        TalkEntity existingTalk = talkRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Talk not found with id: " + id));

        SpeakerEntity newSpeaker = speakerRepository
                .findById(requestDto.getSpeakerId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Speaker not found with id: "
                                + requestDto.getSpeakerId()
                ));

        validateDuration(requestDto.getDurationMinutes());
        Long oldSpeakerId = existingTalk.getSpeaker().getId();
        Long newSpeakerId = newSpeaker.getId();

        if (!oldSpeakerId.equals(newSpeakerId)) {
            long talkCount = talkRepository.countBySpeakerId(newSpeakerId);

            if (talkCount >= MAX_TALKS_PER_SPEAKER) {
                throw new BusinessRuleException("A speaker cannot have more than 3 talks");
            }
        }

        talkMapper.updateEntity(requestDto, existingTalk);
        existingTalk.setSpeaker(newSpeaker);
        TalkEntity updatedTalk = talkRepository.save(existingTalk);
        log.info("Talk updated successfully with id: {}", id);
        return talkMapper.toDto(updatedTalk);
    }

    private void validateDuration(Integer durationMinutes) {
        if (durationMinutes == null || durationMinutes < MIN_DURATION || durationMinutes > MAX_DURATION) {
            throw new BusinessRuleException("Talk duration must be between 15 and 90 minutes");
        }
    }
}